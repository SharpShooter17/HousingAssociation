package pl.dmcs.bujazdowski.exception;

import org.springframework.security.access.AccessDeniedException;
import pl.dmcs.bujazdowski.domain.User;

public class AuthorizationException extends AccessDeniedException {

    public AuthorizationException(User user) {
        super("User " + user.getEmail() + " is not authorized to get this resources");
    }
}
