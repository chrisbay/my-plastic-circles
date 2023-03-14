package net.chrisbay.myplasticcircles.provider.exception;

public class ValidationException extends RuntimeException {

    public ValidationException() {}

    public ValidationException(String message) {
        super(message);
    }
}
