package io.berndruecker.demo.springboot.starter;

import org.openapitools.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.AuthorizationApi;
import org.openapitools.client.api.HistoricActivityInstanceApi;
import org.openapitools.client.api.HistoricBatchApi;
import org.openapitools.client.api.HistoricDecisionDefinitionApi;
import org.openapitools.client.api.HistoricDecisionInstanceApi;
import org.openapitools.client.api.HistoricDecisionRequirementsDefinitionApi;
import org.openapitools.client.api.HistoricDetailApi;
import org.openapitools.client.api.HistoricExternalTaskLogApi;
import org.openapitools.client.api.HistoricIdentityLinkLogApi;
import org.openapitools.client.api.HistoricIncidentApi;
import org.openapitools.client.api.HistoricJobLogApi;
import org.openapitools.client.api.HistoricProcessDefinitionApi;
import org.openapitools.client.api.HistoricProcessInstanceApi;
import org.openapitools.client.api.HistoricTaskInstanceApi;
import org.openapitools.client.api.HistoricUserOperationLogApi;
import org.openapitools.client.api.HistoricVariableInstanceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CamundaHistoryApi {

    @Autowired
    private ApiClient apiClient;

    @Bean
    public HistoricActivityInstanceApi historicActivityInstanceApi () {
        return new HistoricActivityInstanceApi(apiClient);
    }

    @Bean
    public HistoricBatchApi historicBatchApi() {
        return new HistoricBatchApi(apiClient);
    }

    @Bean
    public HistoricDecisionDefinitionApi historicDecisionDefinitionApi() {
        return new HistoricDecisionDefinitionApi(apiClient);
    }

    @Bean
    public HistoricDecisionInstanceApi historicDecisionInstanceApi() {
        return new HistoricDecisionInstanceApi(apiClient);
    }

    @Bean
    public HistoricDecisionRequirementsDefinitionApi historicDecisionRequirementsDefinitionApi() {
        return new HistoricDecisionRequirementsDefinitionApi(apiClient);
    }

    @Bean
    public HistoricDetailApi historicDetailApi() {
        return new HistoricDetailApi(apiClient);
    }

    @Bean
    public HistoricExternalTaskLogApi historicExternalTaskLogApi() {
        return new HistoricExternalTaskLogApi(apiClient);
    }

    @Bean
    public HistoricIdentityLinkLogApi historicIdentityLinkLogApi() {
        return new HistoricIdentityLinkLogApi(apiClient);
    }

    @Bean
    public HistoricIncidentApi historicIncidentApi() {
        return new HistoricIncidentApi(apiClient);
    }

    @Bean
    public HistoricJobLogApi historicJobLogApi() {
        return new HistoricJobLogApi(apiClient);
    }

    @Bean
    public HistoricProcessDefinitionApi historicProcessDefinitionApi() {
        return new HistoricProcessDefinitionApi(apiClient);
    }

    @Bean
    public HistoricProcessInstanceApi historicProcessInstanceApi() {
        return new HistoricProcessInstanceApi(apiClient);
    }

    @Bean
    public HistoricTaskInstanceApi historicTaskInstanceApi() {
        return new HistoricTaskInstanceApi(apiClient);
    }

    @Bean
    public HistoricUserOperationLogApi historicTUserOperationLogApi() {
        return new HistoricUserOperationLogApi(apiClient);
    }

    @Bean
    public HistoricVariableInstanceApi historicVariableInstanceApi() {
        return new HistoricVariableInstanceApi(apiClient);
    }

}
