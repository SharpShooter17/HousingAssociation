package pl.dmcs.bujazdowski.controller.page.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.controller.model.ActivationModel;
import pl.dmcs.bujazdowski.service.AuthenticationService;
import pl.dmcs.bujazdowski.service.ReCaptchaService;

import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestScoped
@RequestMapping(value = "/page/user/activate")
public class ActivationController {

    private final AuthenticationService authenticationService;
    private final ReCaptchaService reCaptchaService;

    @Autowired
    public ActivationController(AuthenticationService authenticationService,
                                ReCaptchaService reCaptchaService) {
        this.authenticationService = authenticationService;
        this.reCaptchaService = reCaptchaService;
    }

    @RequestMapping(value = "/{token}/{userId}")
    public ModelAndView activate(@PathVariable("token") String token, @PathVariable("userId") Long userId) {
        ModelAndView model = new ModelAndView("/page/user/activate");
        model.addObject("model", new ActivationModel(userId, token, ""));
        return model;
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String activateAccount(@ModelAttribute("model") ActivationModel model,
                                  HttpServletRequest request) {
        if (reCaptchaService.verify(request.getParameter("g-recaptcha-response"))) {
            authenticationService.activateAccount(
                    model.getUserId(),
                    model.getToken(),
                    model.getPassword()
            );
        } else {
            return "redirect:/page/user/activate/" + model.getToken() + "/" + model.getUserId();
        }

        return "redirect:/login";
    }

}
