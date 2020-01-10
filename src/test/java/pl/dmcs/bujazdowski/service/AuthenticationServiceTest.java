package pl.dmcs.bujazdowski.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.dmcs.bujazdowski.configuration.ConfigTest;
import pl.dmcs.bujazdowski.controller.model.UserModel;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.User;

@SpringJUnitConfig(ConfigTest.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
class AuthenticationServiceTest {

    private final AuthenticationService service;
    private final UserModel user = new UserModel("Bartosz", "Ujazdowski", "b.ujazdowski@gmail.com", "123-123-123", new RoleType[]{RoleType.USER});

    @Autowired
    AuthenticationServiceTest(AuthenticationService service) {
        this.service = service;
    }

    @Test
    void registerUser() {
        service.registration(user);
        User notActiveUser = service.findUser(this.user.getEmail());
        assert !notActiveUser.isEnabled();

        service.activateAccount(notActiveUser.getId(), notActiveUser.token(), "password");
        User activeUser = service.findUser(this.user.getEmail());
        assert activeUser.isEnabled();
    }
}