package pl.dmcs.bujazdowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.domain.UserCredentials;
import pl.dmcs.bujazdowski.service.AuthenticationService;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
public class LoginController {

    private final AuthenticationService authenticationService;
    private UserCredentials userCredentials = new UserCredentials();

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("credentials", userCredentials);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processUserCredentials(@ModelAttribute("credentials") UserCredentials credentials) {
        if (authenticationService.authenticateUser(credentials)) {
            return "redirect:appUsers";
        } else {
            credentials.setPassword("");
            return "login";
        }
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }
}
