package core.service.api.authStringGenerators;

import core.exceptions.ElementDoesntMatchException;
import core.models.api.HttpRequest;
import core.models.api.Response;
import core.service.api.HttpRequestService;
import core.service.api.JsonClient;

public class OAuth2StringGenerator implements AuthStringGenerator
{
    private HttpRequestService httpRequestService;
    private HttpRequest httpRequest;
    private JsonClient jsonClient;

    public OAuth2StringGenerator(HttpRequestService httpRequestService, HttpRequest httpRequest, JsonClient jsonClient) {
        setHttpRequestService(httpRequestService).setJsonClient(jsonClient).setHttpRequest(httpRequest);
    }

    @Override
    public String generate()
    {
        Response response = null;
        try{
            response = httpRequestService.query(
                    getHttpRequest()
                            .setPathUrl("token")
                            .setMethod("POST")
                            .setBody("grant_type=client_credentials")
            );
        }catch (ElementDoesntMatchException e){
            System.out.println(e.getMessage());
            return "";
        }

        if (!response.getStatusCode().equals(200)){
            System.out.println(response.getStatusCode() + response.getStatusText());
            return "";
        }

        return "Bearer " + getJsonClient().getJsonNode(response.getResponseBody()).get("token").asText();
    }

    private HttpRequestService getHttpRequestService() {
        return httpRequestService;
    }

    private OAuth2StringGenerator setHttpRequestService(HttpRequestService httpRequestService) {
        this.httpRequestService = httpRequestService;
        return this;
    }

    private HttpRequest getHttpRequest() {
        return httpRequest;
    }

    private OAuth2StringGenerator setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        return this;
    }

    private JsonClient getJsonClient() {
        return jsonClient;
    }

    private OAuth2StringGenerator setJsonClient(JsonClient jsonClient) {
        this.jsonClient = jsonClient;
        return this;
    }
}
