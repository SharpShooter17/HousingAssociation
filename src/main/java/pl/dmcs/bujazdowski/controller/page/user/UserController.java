package pl.dmcs.bujazdowski.controller.page.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.controller.model.UserModel;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.service.AuthenticationService;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Arrays;

@Controller
@RequestScoped
@RequestMapping(value = "/page/user")
public class UserController {

    private final AuthenticationService authenticationService;
    private final HousingAssociationService service;

    private final String basePath = "/page/user";
    private final String listPath = "/list";
    private final String registerPath = "/register";
    private final String editPath = "/edit";
    private final String removePath = "/remove";

    @Autowired
    public UserController(AuthenticationService authenticationService,
                          HousingAssociationService service) {
        this.authenticationService = authenticationService;
        this.service = service;
    }

    @RequestMapping(value = listPath)
    public String list(Model model) {
        model.addAttribute("users", service.findAllUsers());
        return basePath + listPath;
    }

    @RequestMapping(value = registerPath, method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("availableRoles", Arrays.asList(RoleType.values()));
        return basePath + registerPath;
    }

    @RequestMapping(value = registerPath, method = RequestMethod.POST)
    public String registerAction(@ModelAttribute("user") UserModel user) {
        authenticationService.registration(user);
        return "redirect:" + basePath + listPath;
    }

    @RequestMapping(value = editPath + "/{userId}", method = RequestMethod.GET)
    public String editPage(@PathVariable("userId") Long userId,
                           Model model) {
        User user = service.findUser(userId);
        UserModel userModel = new UserModel();
        authenticationService.mapUser(user, userModel);

        model.addAttribute("userModel", userModel);
        model.addAttribute("userId", userId);
        model.addAttribute("availableRoles", Arrays.asList(RoleType.values()));
        return basePath + editPath;
    }

    @RequestMapping(value = editPath + "/{userId}", method = RequestMethod.POST)
    public String editAction(@ModelAttribute("userModel") UserModel user,
                             @PathVariable("userId") Long userId) {
        authenticationService.edition(userId, user);
        return "redirect:" + basePath + listPath;
    }

    @RequestMapping(value = removePath + "/{userId}", method = RequestMethod.POST)
    public String editAction(@PathVariable("userId") Long userId) {
        authenticationService.remove(userId);
        return "redirect:" + basePath + listPath;
    }

}
