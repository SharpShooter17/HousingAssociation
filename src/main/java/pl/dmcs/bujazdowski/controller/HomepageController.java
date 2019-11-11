package pl.dmcs.bujazdowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
public class HomepageController {

    private final HousingAssociationService service;

    @Autowired
    public HomepageController(HousingAssociationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/homepage")
    public String homepage() {
        return "/homepage";
    }

}
