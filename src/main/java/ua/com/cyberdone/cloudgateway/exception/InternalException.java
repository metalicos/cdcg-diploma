package ua.com.cyberdone.cloudgateway.exception;

public class InternalException extends RuntimeException {

    public InternalException(Exception e) {
        super(e);
    }
}
