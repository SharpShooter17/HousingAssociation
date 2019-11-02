package pl.dmcs.bujazdowski.exception;

public class TokenExpiredException extends ApplicationException {

    public TokenExpiredException() {
        super("Token expired");
    }
}
