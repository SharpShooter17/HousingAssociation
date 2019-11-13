package pl.dmcs.bujazdowski.controller.admin;

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
@RequestMapping(value = "/admin")
public class AdminUserController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @Autowired
    public AdminUserController(AuthenticationService authenticationService,
                               UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users")
    public String showAppUsers(Model model) {
        model.addAttribute("newUser", new NewUserModel());
        model.addAttribute("availableRoles", Arrays.asList(RoleType.values()));
        model.addAttribute("users", userRepository.findAll());
        return "/admin/users";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addAppUser(@ModelAttribute("newUser") NewUserModel user) {
        authenticationService.findRoles(Arrays.stream(user.getRoles()).collect(Collectors.toSet()))
                .forEach(role -> user.getUser().addRole(role));
        authenticationService.registration(user.getUser());
        return "redirect:/admin/users";
    }

}
