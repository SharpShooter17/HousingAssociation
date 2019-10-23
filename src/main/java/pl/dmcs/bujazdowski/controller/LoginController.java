package pl.dmcs.bujazdowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.domain.UserCredentials;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
public class LoginController {

    private UserCredentials userCredentials = new UserCredentials();

    @RequestMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("credentials", userCredentials);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processUserCredentials(@ModelAttribute("credentials") UserCredentials credentials) {
        return "redirect:appUsers";
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }
}
