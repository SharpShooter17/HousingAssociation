package pl.dmcs.bujazdowski.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.dao.UserRepository;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.service.AuthenticationService;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
@RequestMapping(value = "/admin")
public class UserController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    public UserController(AuthenticationService authenticationService,
                             UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users")
    public String showAppUsers(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userRepository.findAll());
        return "/admin/users";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addAppUser(@ModelAttribute("user") User user) {
        authenticationService.registration(user);
        return "redirect:/admin/users";
    }

}
