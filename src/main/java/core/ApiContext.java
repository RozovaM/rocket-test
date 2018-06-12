package core;

import core.modules.library.models.Verbose;
import core.modules.rest.builders.DefaultRequestBuilder;
import core.modules.library.models.Config;
import core.modules.rest.models.HttpRequest;
import core.modules.rest.services.CustomizedEndpointService;
import core.modules.rest.services.EndpointService;
import core.modules.rest.services.HttpRequestService;
import core.modules.rest.services.JsonClient;
import core.modules.rest.services.authStringGenerators.AuthStringGenerator;
import core.modules.rest.services.authStringGenerators.BasicAuthStringGenerator;
import core.modules.rest.services.authStringGenerators.OAuth2StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.prefs.Preferences;

@Configuration
@ComponentScan(basePackages = {"core"})
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
    public EndpointService restApiBasicAuth(){
        return new EndpointService(httpRequestService, defaultRestApiHttpRequest, jsonClient);
    }

    @Bean
    public Verbose verbose() {
        return new Verbose(config().getPreference().node("General").getBoolean("debug", false));
    }

    @Bean
    public EndpointService restApiOAuth2(){
        return new EndpointService(httpRequestService, defaultRestApiOAuth2HttpRequest(), jsonClient);
    }

    @Bean
    public CustomizedEndpointService restApi(){
        return new CustomizedEndpointService(httpRequestService, defaultRestApiHttpRequest, jsonClient, config);
    }

    @Bean
    public AuthStringGenerator basicAuthStringGenerator()
    {
        Preferences config = config().getPreference().node("RestApiBasicAuth");
        return new BasicAuthStringGenerator(config);
    }

    @Bean
    public AuthStringGenerator basicAuthForOAuth2StringGenerator()
    {
        Preferences config = config().getPreference().node("OAuth2");
        return new BasicAuthStringGenerator(config);
    }

    @Bean
    public AuthStringGenerator oAuth2StringGenerator()
    {
        return new OAuth2StringGenerator(
                httpRequestService(),
                restApiHttpRequestForHttpAuthorization(),
                new JsonClient(),
                config.getPreference().node("OAuth2").get("uri", "")
        );
    }

    @Bean
    @Scope("prototype")
    public HttpHeaders defaultRestApiHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Accept", "*/*");
        httpHeaders.add("Authorization", basicAuthStringGenerator().generate());
        return httpHeaders;
    }


    @Bean
    @Scope("prototype")
    public HttpRequest defaultRestApiHttpRequest()
    {
        return (new DefaultRequestBuilder()).build(
                config().getPreference().node("Api").get("baseUrl", ""),
                defaultRestApiHttpHeaders()
        );
    }

    @Bean
    @Scope("prototype")
    public HttpRequest defaultRestApiOAuth2HttpRequest()
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Accept", "*/*");
        httpHeaders.add("Authorization", oAuth2StringGenerator().generate());

        return (new DefaultRequestBuilder()).build(
                config().getPreference().node("Api").get("baseUrl", ""),
                httpHeaders
        );
    }

    @Bean
    @Scope("prototype")
    public HttpRequest restApiHttpRequestForHttpAuthorization()
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        httpHeaders.add("Accept", "*/*");
        httpHeaders.add("Authorization", basicAuthForOAuth2StringGenerator().generate());

        return (new DefaultRequestBuilder()).build(
                config().getPreference().node("Api").get("baseUrl", ""),
                httpHeaders
        );
    }

    @Bean
    public HttpRequestService httpRequestService()
    {
        return new HttpRequestService(verbose());
    }

    //-------------------End HttpHeaders------------------------

    @Bean
    public Config config() {
        return new Config("config.ini");
    }

    @Bean
    public Preferences generalConfig() {
        return config().getPreference().node("General");
    }

    @Bean
    public DriverManagerDataSource driverManagerDataSource () {
        Preferences config = generalConfig();
        return new DriverManagerDataSource(config.get("dbUrl", ""), config.get("dbUser", ""), config.get("dbPassword", ""));
    }
}
