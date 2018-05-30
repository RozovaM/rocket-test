package core;

import core.models.Config;
import api.rest.ApiRestConfig;
import api.rest.library.models.HttpRequest;
import api.rest.library.services.EndpointService;
import api.rest.library.services.CustomizedEndpointService;
import api.rest.library.services.HttpRequestService;
import api.rest.library.services.JsonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"library.models"})
@Import(ApiRestConfig.class)
@Lazy
public class ApiContext
{
    @Autowired
    HttpRequestService httpRequestService;
    @Autowired
    @Qualifier("defaultRestApiHttpRequest")
    HttpRequest defaultRestApiHttpRequest;
    @Autowired
    JsonClient jsonClient;
    @Autowired
    Config config;

    @Bean
    EndpointService restApiBasicAuth(){
        return new EndpointService(httpRequestService, defaultRestApiHttpRequest, jsonClient);
    }

    @Bean
    CustomizedEndpointService restApi(){
        return new CustomizedEndpointService(httpRequestService, defaultRestApiHttpRequest, jsonClient, config);
    }
}
