package core.modules.http;

import core.builders.DefaultRequestBuilder;
import core.exceptions.ElementDoesntMatchException;
import core.models.api.HttpRequest;
import core.models.api.Response;
import core.service.api.EndpointService;
import core.service.api.HttpRequestService;
import core.service.api.JsonClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpStatusCodeException;

public class HttpDriver {

    private HttpRequestService httpRequestService;
    private HttpRequest httpRequest;
    private Response httpResponse;
    private JsonClient jsonClient;

    public HttpDriver(HttpRequestService httpRequestService, HttpRequest httpRequest, JsonClient jsonClient)
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

    public HttpDriver sendGET(String url)
    {
        this.httpResponse = query("GET", url, null);
        return this;
    }

    public HttpDriver sendPOST(String url, String json)
    {
        this.httpResponse = query("POST", url, json);
        return this;
    }

    public HttpDriver sendPATCH(String url, String json)
    {
        this.httpResponse = query("PATCH", url, json);
        return this;
    }

    public HttpDriver sendPUT(String url, String json)
    {
        this.httpResponse = query("PUT", url, json);
        return this;
    }

    public HttpDriver sendDELETE(String url)
    {
        this.httpResponse = query("DELETE", url, null);
        return this;
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

    protected HttpDriver setHttpRequestService(HttpRequestService httpRequestService)
    {
        this.httpRequestService = httpRequestService;
        return this;
    }

    protected HttpDriver setHttpRequest(HttpRequest httpRequest)
    {
        this.httpRequest = httpRequest;
        return this;
    }

    protected HttpDriver setJsonClient(JsonClient jsonClient)
    {
        this.jsonClient = jsonClient;
        return this;
    }

    public HttpAssert getAssert(){
        return new HttpAssert(getHttpResponse(), getJsonClient());
    }

    private Response getHttpResponse(){
        return httpResponse;
    }
}
