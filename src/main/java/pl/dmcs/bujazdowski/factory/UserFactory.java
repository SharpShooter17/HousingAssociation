package pl.dmcs.bujazdowski.factory;

import org.springframework.stereotype.Component;
import pl.dmcs.bujazdowski.domain.User;

@Component
public class UserFactory {

    public void createNewUser(User user) {
        user.disable();
        user.generateToken();
    }

}
