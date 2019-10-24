package pl.dmcs.bujazdowski.exception;

import pl.dmcs.bujazdowski.domain.User;

public class UserAlreadyExists extends ValidationException {

    public UserAlreadyExists(User user) {
        super("The user: " + user.getFirstName() + " " + user.getLastName() + " already exists in the system!");
    }

}
