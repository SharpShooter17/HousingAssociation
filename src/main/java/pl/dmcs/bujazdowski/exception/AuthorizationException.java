package pl.dmcs.bujazdowski.exception;


import pl.dmcs.bujazdowski.domain.User;

public class AuthorizationException extends ApplicationException {

    public AuthorizationException(User user) {
        super("User " + user.getEmail() + " is not authorized to get this resources");
    }
}
