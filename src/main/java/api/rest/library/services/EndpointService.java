package api.rest.library.services;

import api.rest.library.builders.DefaultRequestBuilder;
import api.rest.library.exceptions.ElementDoesntMatchException;
import api.rest.library.models.HttpRequest;
import api.rest.library.models.Response;
import api.rest.library.models.Assertion;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpStatusCodeException;

public class EndpointService
{
    private HttpRequestService httpRequestService;

    private HttpRequest httpRequest;
    private JsonClient jsonClient;

    public EndpointService(HttpRequestService httpRequestService, HttpRequest httpRequest, JsonClient jsonClient)
    {
        setHttpRequestService(httpRequestService).setHttpRequest(httpRequest).setJsonClient(jsonClient);
    }

    public EndpointService addHeader(String name, String value)
    {
        Object[] auth = getHttpRequest().getHeaders().get("Authorization").toArray();
        HttpHeaders httpHeaders = new HttpHeaders();

        if (!name.equals("Content-Type")){
            httpHeaders.add("Content-Type", "application/json");
        }

        httpHeaders.set("Authorization", auth[0].toString());
        httpHeaders.add(name, value);
        HttpRequest httpRequest = (new DefaultRequestBuilder()).build(getHttpRequest().getServer(), httpHeaders);
        return new EndpointService(getHttpRequestService(), httpRequest, getJsonClient());
    }

    public Assertion sendGET(String url)
    {
        return new Assertion(query("GET", url, null), getJsonClient());
    }

    public Assertion sendPOST(String url, String json)
    {
        return new Assertion(query("POST", url, json), getJsonClient());
    }

    public Assertion sendPATCH(String url, String json)
    {
        return new Assertion(query("PATCH", url, json), getJsonClient());
    }

    public Assertion sendPUT(String url, String json)
    {
        return new Assertion(query("PUT", url, json), getJsonClient());
    }

    public Assertion sendDELETE(String url)
    {
        return new Assertion(query("DELETE", url, null), getJsonClient());
    }

    private Response query(String method, String url, String json){

        try {
            return getHttpRequestService().query(
                    getHttpRequest()
                            .setMethod(method)
                            .setPathUrl(url)
                            .setBody(json)
            );
        } catch (ElementDoesntMatchException e){
        } catch (HttpStatusCodeException e) {
            return new Response(
                    e.getRawStatusCode(),
                    e.getStatusText(),
                    e.getResponseBodyAsString(),
                    e.getResponseHeaders()
            );
        }
        return new Response(0, "Unexpected exception. EndpointService.query()", "", null);
    }

    protected HttpRequestService getHttpRequestService()
    {
        return httpRequestService;
    }

    protected HttpRequest getHttpRequest()
    {
        return httpRequest;
    }

    protected JsonClient getJsonClient()
    {
        return jsonClient;
    }

    protected EndpointService setHttpRequestService(HttpRequestService httpRequestService)
    {
        this.httpRequestService = httpRequestService;
        return this;
    }

    protected EndpointService setHttpRequest(HttpRequest httpRequest)
    {
        this.httpRequest = httpRequest;
        return this;
    }

    protected EndpointService setJsonClient(JsonClient jsonClient)
    {
        this.jsonClient = jsonClient;
        return this;
    }
}