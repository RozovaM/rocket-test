package core.exceptions;

public final class JsonClientException extends RuntimeException {
    public JsonClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
