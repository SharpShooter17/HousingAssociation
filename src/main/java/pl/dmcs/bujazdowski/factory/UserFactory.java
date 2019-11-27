package pl.dmcs.bujazdowski.factory;

import pl.dmcs.bujazdowski.controller.model.UserModel;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.service.AuthenticationService;

public class UserFactory {

    private final AuthenticationService authenticationService;

    public UserFactory(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public User createNewUser(UserModel userModel) {
        User user = new User();
        authenticationService.mapUser(userModel, user);
        user.disable();
        user.generateToken();
        return user;
    }

}
