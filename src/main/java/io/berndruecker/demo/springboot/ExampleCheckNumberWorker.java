package io.berndruecker.demo.springboot;

import java.util.Collections;
import java.util.logging.Logger;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExternalTaskSubscription("check-number")
public class ExampleCheckNumberWorker implements ExternalTaskHandler {

    private final static Logger LOGGER = Logger.getLogger(ExampleCheckNumberWorker.class.getName());

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        // Get a process variable
        String someProcessVariable = (String) externalTask.getVariable("someProcessVariable");

        boolean isNumber = false;
        try {
            Long.parseLong(someProcessVariable);
            isNumber = true;
        } catch (NumberFormatException e) {
            LOGGER.info(someProcessVariable + " is not numeric");
        }

        LOGGER.info("Returning validate=" + isNumber);

        // Complete the task
        externalTaskService.complete(externalTask, Collections.singletonMap("isNumber", isNumber));
    }

}
