package io.berndruecker.demo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootApp {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootApp.class, args);

        // For playing around it might be nice to just start a process right away
        // context.getBean(ExampleRestEndpoint.class).startProcess(null);
    }
}
