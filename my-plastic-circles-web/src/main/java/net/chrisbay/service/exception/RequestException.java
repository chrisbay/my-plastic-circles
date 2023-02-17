package net.chrisbay.service.exception;

public class RequestException extends RuntimeException {

    private String responseCode;
    private String message;

    public RequestException() {}

    public RequestException(String responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
