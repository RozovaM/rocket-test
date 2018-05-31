package core.builders;

import core.models.api.HttpRequest;
import org.springframework.http.HttpHeaders;

public class DefaultRequestBuilder
{
    public HttpRequest build(String server, HttpHeaders httpHeaders){
        return (new HttpRequest()).setServer(server).setHeaders(httpHeaders);
    }
}
