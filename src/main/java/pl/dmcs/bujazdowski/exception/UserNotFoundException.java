package pl.dmcs.bujazdowski.exception;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(String email) {
        super("Not found user with email: " + email);
    }

    public UserNotFoundException(Long id) {
        super("Not found user with id: " + id);
    }
}
