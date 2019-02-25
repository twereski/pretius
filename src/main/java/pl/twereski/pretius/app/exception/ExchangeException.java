package pl.twereski.pretius.app.exception;

public class ExchangeException extends RuntimeException {

    public ExchangeException(String message) {
        super(message);
    }

    public ExchangeException(String message, Throwable e) {
        super(message, e);
    }
}
