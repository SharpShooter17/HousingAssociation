package pl.dmcs.bujazdowski.controller.page.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.controller.model.NewUserModel;
import pl.dmcs.bujazdowski.dao.UserRepository;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.service.AuthenticationService;

import javax.faces.bean.RequestScoped;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestScoped
@RequestMapping(value = "/page/user")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    private final String basePath = "/page/user";
    private final String listPath = "/list";
    private final String registerPath = "/register";

    @Autowired
    public UserController(AuthenticationService authenticationService,
                          UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = listPath)
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return basePath + listPath;
    }

    @RequestMapping(value = registerPath, method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("newUser", new NewUserModel());
        model.addAttribute("availableRoles", Arrays.asList(RoleType.values()));
        return basePath + registerPath;
    }

    @RequestMapping(value = registerPath, method = RequestMethod.POST)
    public String registerAction(@ModelAttribute("newUser") NewUserModel user) {
        authenticationService.findRoles(Arrays.stream(user.getRoles()).collect(Collectors.toSet()))
                .forEach(role -> user.getUser().addRole(role));
        authenticationService.registration(user.getUser());
        return "redirect:" + basePath + listPath;
    }

}
