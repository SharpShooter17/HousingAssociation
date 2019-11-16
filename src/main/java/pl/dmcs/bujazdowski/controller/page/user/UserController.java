package pl.dmcs.bujazdowski.controller.page.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.controller.model.UserModel;
import pl.dmcs.bujazdowski.domain.Role;
import pl.dmcs.bujazdowski.domain.RoleType;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.service.AuthenticationService;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Arrays;
import java.util.stream.Collectors;

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
    public UserController(AuthenticationService authenticationService, HousingAssociationService service) {
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
        authenticationService.findRoles(Arrays.stream(user.getRoles()).collect(Collectors.toSet()))
                .forEach(role -> user.getUser().addRole(role));
        authenticationService.registration(user.getUser());
        return "redirect:" + basePath + listPath;
    }

    @RequestMapping(value = editPath + "/{userId}", method = RequestMethod.GET)
    public String editPage(@PathVariable("userId") Long userId,
                           Model model) {
        User user = service.findUser(userId);
        UserModel userModel = new UserModel();
        userModel.setUser(user);
        userModel.setRoles(user.getRoles().stream().map(Role::getName).distinct().toArray(RoleType[]::new));
        model.addAttribute("userModel", userModel);
        model.addAttribute("availableRoles", Arrays.asList(RoleType.values()));
        return basePath + editPath;
    }

    @RequestMapping(value = editPath, method = RequestMethod.POST)
    public String editAction(@ModelAttribute("userModel") UserModel user) {
        authenticationService.findRoles(Arrays.stream(user.getRoles()).collect(Collectors.toSet()))
                .forEach(role -> user.getUser().addRole(role));
        authenticationService.edition(user.getUser());
        return "redirect:" + basePath + listPath;
    }

    @RequestMapping(value = removePath + "/{userId}", method = RequestMethod.POST)
    public String editAction(@PathVariable("userId") Long userId) {
        authenticationService.remove(userId);
        return "redirect:" + basePath + listPath;
    }

}
