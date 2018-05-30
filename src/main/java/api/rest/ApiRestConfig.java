package api.rest;

import core.models.Config;
import api.rest.library.builders.DefaultRequestBuilder;
import api.rest.library.models.HttpRequest;
import api.rest.library.services.*;
import api.rest.library.services.authStringGenerators.AuthStringGenerator;
import api.rest.library.services.authStringGenerators.BasicAuthStringGenerator;
import api.rest.library.services.authStringGenerators.OAuth2StringGenerator;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.prefs.Preferences;

@Configuration
@ComponentScan(basePackages = {"api.rest"})
@Lazy
public class ApiRestConfig
{
    //------------------- HttpHeaders -------------------------

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
        return new OAuth2StringGenerator(httpRequestService(), restApiHttpRequestForHttpAuthorization(), new JsonClient());
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
                config().getPreference().node("Servers").get("oAuth2", ""),
                httpHeaders
        );
    }

    @Bean
    public HttpRequestService httpRequestService()
    {
        return new HttpRequestService(
                config().getPreference().node("General").getBoolean("debug", false)
        );
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
/*
    @Bean
    public ResourceRestMapper resourceRestMapper(){
        return (
                new ResourceRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("ResourceRestMapper"))
        );
    }

    @Bean
    public WorkScheduleRestMapper workScheduleRestMapper(){
        return (
                new WorkScheduleRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("WorkScheduleRestMapper"))
        );
    }

    @Bean
    public ResourceWorkSkillRestMapper resourceWorkSkillRestMapper(){
        return (
                new ResourceWorkSkillRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("ResourceWorkSkillRestMapper"))
        );
    }

    @Bean
    public WorkSkillRestMapper workSkillRestMapper(){
        return (
                new WorkSkillRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("WorkSkillRestMapper"))
        );
    }

    @Bean
    public ActivityRestMapper activityRestMapper(){
        return (
                new ActivityRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("ActivityRestMapper"))
        );
    }

    @Bean
    public RequiredInventoryRestMapper requiredInventoryRestMapper(){
        return (
                new RequiredInventoryRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("RequiredInventoryRestMapper"))
        );
    }

    @Bean
    public ResourcePreferencesRestMapper resourcePreferencesRestMapper(){
        return (
                new ResourcePreferencesRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("ResourcePreferencesRestMapper"))
        );
    }

    @Bean
    public LocationRestMapper locationRestMapper(){
        return (
                new LocationRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("LocationRestMapper"))
        );
    }

    @Bean
    public PlanRestMapper planRestMapper(){
        return (
                new PlanRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("PlanRestMapper"))
        );
    }

    @Bean
    public ActivityLinkRestMapper activityLinkRestMapper(){
        return (
                new ActivityLinkRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("ActivityLinkRestMapper"))
        );
    }

    @Bean
    public UserRestMapper userRestMapper(){
        return (
                new UserRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("UserRestMapper"))
        );
    }

    @Bean
    public InventoryRestMapper inventoryRestMapper(){
        return (
                new InventoryRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("InventoryRestMapper"))
        );
    }

    @Bean
    public FilePropertyRestMapper filePropertyRestMapper(){
        return (
                new FilePropertyRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("FilePropertyRestMapper"))
        );
    }

    @Bean
    public GeolocationRestMapper geolocationRestMapper(){
        return (
                new GeolocationRestMapper(
                        httpRequestService(),
                        (HashMap<String, String>) routeMap().getRouteMap().get("GeolocationRestMapper"))
        );
    }

    @Bean
    public EventSubscriptionRestMapper eventSubscriptionRestMapper() {
        return new EventSubscriptionRestMapper(
                httpRequestService(),
                (HashMap<String, String>) routeMap().getRouteMap().get("EventSubscriptionRestMapper")
        );
    }

    @Bean
    public EventRestMapper eventRestMapper() {
        return new EventRestMapper(
                httpRequestService(),
                (HashMap<String, String>) routeMap().getRouteMap().get("EventRestMapper")
        );
    }

    @Bean
    public PropertyRestMapper propertyRestMapper() {
        return new PropertyRestMapper(
                httpRequestService(),
                (HashMap<String, String>) routeMap().getRouteMap().get("PropertyRestMapper")
        );
    }

    @Bean
    public OrganizationRestMapper organizationRestMapper() {
        return new OrganizationRestMapper(
                httpRequestService(),
                (HashMap<String, String>) routeMap().getRouteMap().get("OrganizationRestMapper")
        );
    }

    @Bean
    public ApplicationJDBCTemplate applicationJDBCTemplate() {
        return new ApplicationJDBCTemplate(driverManagerDataSource(), new JdbcTemplate(driverManagerDataSource()) );
    }
    */
}
