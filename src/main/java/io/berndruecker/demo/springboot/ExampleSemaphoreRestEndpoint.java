package io.berndruecker.demo.springboot;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.topic.TopicSubscription;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.dto.*;
import org.camunda.community.rest.client.invoker.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@RestController
public class ExampleSemaphoreRestEndpoint {

    @Autowired
    private ProcessDefinitionApi processDefinitionApi;

    @Autowired
    private ExternalTaskClient externalTaskClient;

    public static Map<String, Semaphore> semaphors = new HashMap<>();
    public static Map<String, TopicSubscription> semaphorSubscriptions = new HashMap<>();
    public static Map<String, Map> semaphorResults = new HashMap<>();

    @PutMapping("/startWithResponse")
    public ResponseEntity<String> startProcessWithResponse(ServerWebExchange exchange) throws ApiException, InterruptedException {
        // TODO: Get from REST request / URL parameter
        String someProcessVariable = "test";

        // prepare variables to pass on to process
        Map<String, VariableValueDto> variables = new HashMap<>();
        variables.put(
                ProcessConstants.VAR_NAME_SOME_VARIABLE,
                new VariableValueDto().value(someProcessVariable).type("string"));

        // create a unique ID to wait for completion later and add it to the process
        String uuid = UUID.randomUUID().toString();
        variables.put(ProcessConstants.VAR_NAME_SEMAPHORE_CHECK,
                new VariableValueDto().value(uuid).type("string"));

        // start process instance
        ProcessInstanceWithVariablesDto processInstance = processDefinitionApi.startProcessInstanceByKey(
                ProcessConstants.PROCESS_KEY_SEMAPHORE_EXTENSION,
                new StartProcessInstanceDto().variables(variables));

        // now let's wait for the process to reach
        try {
            Semaphore semaphore = newSemaphore(uuid);
            boolean finished = semaphore.tryAcquire(500, TimeUnit.MILLISECONDS);

            if (!finished) {
                // we don't know what happened - it took too long. Let's respond by HTTP 202
                return ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body("Started process instance with id: " + processInstance.getId());
            }
            // yeah, we have our results :-)
            boolean isNumber = (boolean) semaphorResults.get(uuid).get(ProcessConstants.VAR_NAME_IS_NUMBER);
            // And just return something for the sake of the example
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Started process instance with id: " + processInstance.getId() + ". Is number? " + isNumber);
        } finally {
            cleanupSemaphore(uuid);
        }
    }

    private Semaphore newSemaphore(String uuid) {
        Semaphore semaphore = new Semaphore(0);
        semaphors.put(uuid, semaphore);

        TopicSubscription topicSubscription = externalTaskClient.subscribe("SEMAPHORE_" + uuid).handler(((externalTask, externalTaskService) -> {
            // remember results
            semaphorResults.put(uuid, externalTask.getAllVariables());
            // and release semaphore
            semaphors.get(uuid).release();
        })).open();
        semaphorSubscriptions.put(uuid, topicSubscription);

        return semaphore;
    }
    private void cleanupSemaphore(String uuid) {
        semaphors.remove(uuid);
        semaphorSubscriptions.get(uuid).close();
        semaphorSubscriptions.remove(uuid);
        semaphorResults.remove(uuid);
    }

}
