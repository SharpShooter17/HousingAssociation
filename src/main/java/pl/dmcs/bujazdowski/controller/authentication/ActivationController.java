package pl.dmcs.bujazdowski.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.service.AuthenticationService;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
public class ActivationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public ActivationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/activate/{token}/{userId}")
    public ModelAndView activate(@PathVariable("token") String token, @PathVariable("userId") Long userId) {
        ModelAndView model = new ModelAndView("activate");
        model.addObject("model", new ActivationModel(userId, token, ""));
        return model;
    }

    @RequestMapping(value = "/activate-account", method = RequestMethod.POST)
    public String activateAccount(@ModelAttribute("model") ActivationModel model) {
        authenticationService.activateAccount(
                model.getUserId(),
                model.getToken(),
                model.getPassword()
        );

        return "redirect:login";
    }

}
