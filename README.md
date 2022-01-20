Example showing how to connect to a remote Camunda Run from Spring boot for external taks and OpenAPI REST calls

# What's this?

In this app you can see:
* OpenAPI-based REST CLient is automatically generated: https://github.com/berndruecker/camunda-platform-remote-spring-boot-example/blob/main/pom.xml#L104
* And made available via Spring: https://github.com/berndruecker/camunda-platform-remote-spring-boot-example/blob/m[…]berndruecker/demo/springboot/starter/CamundaOpenApiStarter.java & https://github.com/berndruecker/camunda-platform-remote-spring-boot-example/blob/m[…]in/java/io/berndruecker/demo/springboot/starter/CamundaApi.java
* Process files in the classpath are automatically deployed on startup: https://github.com/berndruecker/camunda-platform-remote-spring-boot-example/blob/m[…]ecker/demo/springboot/starter/CamundaProcessAutodeployment.java
* Example that starts a new process instance upon a REST request: https://github.com/berndruecker/camunda-platform-remote-spring-boot-example/blob/m[…]n/java/io/berndruecker/demo/springboot/ExampleRestEndpoint.java (of couse you can use different Spring mechanisms to provide a REST endpoint if you like)

I further added an advanced example

* where the REST call can return some data from the process, in this case if the number was valid. I used a specific ServiceTask to mark the moment in the process where I want to hand back control to the REST-Java-endpoint + you have to deal with timeouts, as the process execution itself is asynchronously: https://github.com/berndruecker/camunda-platform-remote-spring-boot-example/blob/m[…]/berndruecker/demo/springboot/ExampleSemaphoreRestEndpoint.java. 

# How to run?

* Run Camunda (e.g. via docker)
* Run this Spring Boot app via your favorite IDE
* Access one of the REST endpoints below

Start a process instance without waiting for the result
```
curl -i -X PUT http://localhost:8090/start 
```

Start a process instance waiting for the result (the extension mentioned below)
```
curl -i -X PUT http://localhost:8090/startWithResponse
```

Get all active process instances (as an example how to use the history query API):
```
curl -i -X GET http://localhost:8090/info
```
