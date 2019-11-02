package pl.dmcs.bujazdowski.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dmcs.bujazdowski.dao.RoleRepository;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.User;

@Component
public class UserFactory {

    private final RoleRepository roleRepository;

    @Autowired
    public UserFactory(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createNewUser(User user) {
        user.disable();
        user.generateToken();
        user.addRole(roleRepository.findByName(RoleType.USER));
    }

}
