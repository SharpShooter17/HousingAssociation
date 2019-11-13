package pl.dmcs.bujazdowski.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dmcs.bujazdowski.dao.RoleRepository;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.User;

@Component
public class UserFactory {

    public void createNewUser(User user) {
        user.disable();
        user.generateToken();
    }

}
