package pl.dmcs.bujazdowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.repository.UserRepository;
import pl.dmcs.bujazdowski.service.AuthenticationService;

import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@RequestScoped
public class AppUserController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    public AppUserController(AuthenticationService authenticationService,
                             UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("users", new ArrayList<>(userRepository.findAllUsers()));
        return "appUser";
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@ModelAttribute("user") User user) {
        authenticationService.registration(user);
        return "redirect:appUsers";
    }

}
