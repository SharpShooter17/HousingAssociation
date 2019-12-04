package pl.dmcs.bujazdowski.controller.page.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.security.OnlyAdministrator;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.List;

@RestController
@RequestScoped
@RequestMapping(value = "/rest/user")
public class UserRestController {

    private final HousingAssociationService service;

    @Autowired
    public UserRestController(HousingAssociationService service) {
        this.service = service;
    }

    @OnlyAdministrator
    @RequestMapping(value = "/json", produces = "application/json")
    public List<User> jsonUsers() {
        return service.findAllUsers();
    }


    @OnlyAdministrator
    @RequestMapping(value = "/xml", produces = "application/xml")
    public List<User> xmlUsers() {
        return service.findAllUsers();
    }

}
