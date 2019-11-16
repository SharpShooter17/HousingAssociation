package pl.dmcs.bujazdowski.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(Throwable throwable) {
        super(throwable);
    }

    public ApplicationException(String message) {
        super(message);
    }
}
