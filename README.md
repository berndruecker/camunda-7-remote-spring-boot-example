[![Camunda Platform 7](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%207-26d07c)](https://img.shields.io/badge/Compatible%20with-Camunda%20Platform%207-26d07c)

Example showing how to connect to a remote Camunda Run from Spring boot for external taks and OpenAPI REST calls.

# What's this?

Example using the [Camunda Engine REST Client Java](https://github.com/camunda-community-hub/camunda-engine-rest-client-java) community extension to leverage a remote Camunda engine, most probably [Camunda Run](https://docs.camunda.org/manual/latest/user-guide/camunda-bpm-run/).

This example:

* Provides [a BPMN process](./blob/main/src/main/resources/playground.bpmn) that is auto-deployed during startup
* Starts a new process instance upon [a REST request](./blob/main/src/main/java/io/berndruecker/demo/springboot/ExampleRestEndpoint.java) (of course, you can use different mechanisms to provide a REST endpoint if you like, it is just an example.
* The same class also provides a REST endpoint to query actively running process instances.
* Adds [an external task worker](./blob/main/src/main/java/io/berndruecker/demo/springboot/ExampleCheckNumberWorker.java) to implement a BPMN service task
* Shows an [advanced example of an REST endpoint](./blob/main/src/main/java/io/berndruecker/demo/springboot/ExampleSemaphoreRestEndpoint.java), where the REST call synchronously returns some data from the process. In the example, if the provided number is valid. To implement this, a specific ServiceTask is used to mark the moment in the process where the control is handed back to the Java code of the REST endpoint. This also has to deal with timeouts, as the process execution itself is asynchronous and can take longer. 

# How to run?

* Start [Camunda Run](https://docs.camunda.org/manual/latest/user-guide/camunda-bpm-run/#starting-camunda-platform-run-using-docker), for example 
  * [Download, unzip and start the distribution](https://docs.camunda.org/manual/latest/user-guide/camunda-bpm-run/#starting-with-camunda-platform-run)
  * [Use Docker image](https://docs.camunda.org/manual/latest/user-guide/camunda-bpm-run/#starting-camunda-platform-run-using-docker)
* Download and tun this Spring Boot app via your favorite IDE
* Access one of the REST endpoints below

Start a process instance without waiting for the result
```
curl -i -X PUT http://localhost:8090/start 
```

Get all active process instances (as an example how to use the history query API):
```
curl -i -X GET http://localhost:8090/info
```

Start a process instance waiting for the result (the extension mentioned below)
```
curl -i -X PUT http://localhost:8090/startWithResponse
```

