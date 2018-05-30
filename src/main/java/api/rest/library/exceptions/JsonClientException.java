package api.rest.library.exceptions;

public final class JsonClientException extends RuntimeException {
    public JsonClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
