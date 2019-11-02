package pl.dmcs.bujazdowski.exception;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(Long id) {
        super("Not found user with id: " + id);
    }
}
