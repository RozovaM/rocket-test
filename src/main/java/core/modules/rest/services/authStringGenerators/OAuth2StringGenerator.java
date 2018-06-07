package core.modules.rest.services.authStringGenerators;

import core.modules.rest.exceptions.ElementDoesntMatchException;
import core.modules.rest.models.HttpRequest;
import core.modules.rest.models.Response;
import core.modules.rest.services.HttpRequestService;
import core.modules.rest.services.JsonClient;

public class OAuth2StringGenerator implements AuthStringGenerator
{
    private HttpRequestService httpRequestService;
    private HttpRequest httpRequest;
    private JsonClient jsonClient;
    private String uri;

    public OAuth2StringGenerator(HttpRequestService httpRequestService, HttpRequest httpRequest, JsonClient jsonClient, String uri) {
        this.httpRequestService = httpRequestService;
        this.httpRequest = httpRequest;
        this.jsonClient = jsonClient;
        this.uri = uri;
    }

    @Override
    public String generate() {
        Response response = null;
        try {
            response = httpRequestService.query(
                    httpRequest
                            .setPathUrl(uri)
                            .setMethod("POST")
                            .setBody("")
            );
        } catch (ElementDoesntMatchException e) {
            System.out.println(e.getMessage());
            return "";
        }

        if (!response.getStatusCode().equals(200)) {
            System.out.println(response.getStatusCode() + response.getStatusText());
            return "";
        }

        return "Bearer " + jsonClient.getJsonNode(response.getResponseBody()).get("token").asText();
    }
}
