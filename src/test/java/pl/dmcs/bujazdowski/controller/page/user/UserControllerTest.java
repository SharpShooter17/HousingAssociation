package pl.dmcs.bujazdowski.controller.page.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.dmcs.bujazdowski.configuration.ConfigTest;

@SpringJUnitConfig(ConfigTest.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
@WebAppConfiguration
class UserControllerTest {

    private final UserController userController;

    @Autowired
    UserControllerTest(UserController userController) {
        this.userController = userController;
    }

    @Test
    void test() {
        assert userController != null;
    }

}