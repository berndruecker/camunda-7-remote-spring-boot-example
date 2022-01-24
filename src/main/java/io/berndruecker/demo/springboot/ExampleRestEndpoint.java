package io.berndruecker.demo.springboot;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.topic.TopicSubscription;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.dto.*;
import org.camunda.community.rest.client.invoker.ApiException;
import org.camunda.community.rest.client.springboot.CamundaHistoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@RestController
public class ExampleRestEndpoint {

    @Autowired
    private ProcessDefinitionApi processDefinitionApi;

    @Autowired
    private CamundaHistoryApi camundaHistoryApi;

    @PutMapping("/start")
    public ResponseEntity<String> startProcess(ServerWebExchange exchange) throws ApiException {
        // TODO: Get from REST request / URL parameter
        String someProcessVariable = "test";

        // prepare variables to pass on to process
        Map<String, VariableValueDto> variables = new HashMap<>();
        variables.put(
                ProcessConstants.VAR_NAME_SOME_VARIABLE,
                new VariableValueDto().value(someProcessVariable).type("string"));

        // start process instance
        ProcessInstanceWithVariablesDto processInstance = processDefinitionApi.startProcessInstanceByKey(
                ProcessConstants.PROCESS_KEY,
                new StartProcessInstanceDto().variables(variables));

        // And just return something for the sake of the example
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Started process instance with id: " + processInstance.getId());
    }

    @GetMapping("/info")
    public ResponseEntity<List<HistoricProcessInstanceDto>> getActiveProcessInstances() throws ApiException {
        List<HistoricProcessInstanceDto> processInstances = camundaHistoryApi.historicProcessInstanceApi().queryHistoricProcessInstances(
                null,
                null,
                new HistoricProcessInstanceQueryDto().active(true));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(processInstances);

    }

}
