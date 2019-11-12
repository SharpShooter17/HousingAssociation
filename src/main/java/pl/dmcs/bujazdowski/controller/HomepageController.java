package pl.dmcs.bujazdowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.Bill;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestScoped
@RequestMapping("/homepage")
public class HomepageController {

    private final HousingAssociationService service;

    @Autowired
    public HomepageController(HousingAssociationService service) {
        this.service = service;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ModelAndView homepage() {
        Set<Apartment> apartments = service.userApartments();
        Set<Bill> bills = apartments.stream()
                .flatMap(apartment -> apartment.getBills().stream())
                .collect(Collectors.toSet());

        ModelAndView modelAndView = new ModelAndView("homepage");
        modelAndView.addObject("apartments", apartments);
        modelAndView.addObject("bills", bills);
        return modelAndView;
    }

}
