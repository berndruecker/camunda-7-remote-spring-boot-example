package io.berndruecker.demo.springboot.starter;

import org.openapitools.client.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaOpenApiStarter {

    @Value( "${camunda.bpm.client.base-url:null}" )
    private String basePath;

    @Bean
    public ApiClient createApiClient() {
        ApiClient client = new ApiClient();
        if (basePath!=null) {
            client.setBasePath(basePath);
        }
        return client;
    }

}
