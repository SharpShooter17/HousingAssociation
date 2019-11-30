package pl.dmcs.bujazdowski.controller.page.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.controller.model.UserModel;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.service.AuthenticationService;
import pl.dmcs.bujazdowski.service.HousingAssociationService;
import pl.dmcs.bujazdowski.validator.UserValidator;

import javax.faces.bean.RequestScoped;
import java.util.Arrays;

@Controller
@RequestScoped
@RequestMapping(value = "/page/user")
public class UserController {

    private final UserValidator userValidator = new UserValidator();
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
    public String registerAction(@ModelAttribute("user") UserModel user,
                                 BindingResult bindingResult,
                                 Model model) {
        userValidator.validate(user, bindingResult);
        if (!bindingResult.hasErrors()) {
            authenticationService.registration(user);
            return "redirect:" + basePath + listPath;
        }
        prepareUserModel(model, user, null);
        return basePath + registerPath;
    }

    @RequestMapping(value = editPath + "/{userId}", method = RequestMethod.GET)
    public String editPage(@PathVariable("userId") Long userId,
                           Model model) {
        authenticationService.validateIfCurrentUserOrAdministrator(userId);
        User user = service.findUser(userId);
        UserModel userModel = new UserModel();
        authenticationService.mapUser(user, userModel);

        prepareUserModel(model, userModel, userId);
        return basePath + editPath;
    }

    private void prepareUserModel(Model model, UserModel userModel, Long userId) {
        model.addAttribute("userModel", userModel);
        model.addAttribute("userId", userId);
        model.addAttribute("availableRoles", Arrays.asList(RoleType.values()));
    }

    @RequestMapping(value = editPath + "/{userId}", method = RequestMethod.POST)
    public String editAction(@ModelAttribute("userModel") UserModel user,
                             @PathVariable("userId") Long userId,
                             BindingResult bindingResult,
                             Model model) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.getErrorCount() == 0) {
            authenticationService.edition(userId, user);
            if (authenticationService.currentUser().getRoles().stream().anyMatch(role -> role.getName().equals(RoleType.MODERATOR))) {
                return "redirect:" + basePath + listPath;
            } else {
                return "redirect:/page/home";
            }
        }

        prepareUserModel(model, user, userId);
        return "/page/user/edit";
    }

    @RequestMapping(value = removePath + "/{userId}", method = RequestMethod.POST)
    public String editAction(@PathVariable("userId") Long userId) {
        authenticationService.remove(userId);
        return "redirect:" + basePath + listPath;
    }

}
