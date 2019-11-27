package pl.dmcs.bujazdowski.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.Bill;
import pl.dmcs.bujazdowski.service.AuthenticationService;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestScoped
@RequestMapping("/page")
public class HomepageController {

    private final HousingAssociationService service;
    private final AuthenticationService authenticationService;

    @Autowired
    public HomepageController(HousingAssociationService service,
                              AuthenticationService authenticationService) {
        this.service = service;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView homepage() {
        Set<Apartment> apartments = service.userApartments();
        Set<Bill> bills = apartments.stream()
                .flatMap(apartment -> apartment.getBills().stream())
                .collect(Collectors.toSet());

        ModelAndView modelAndView = new ModelAndView("/page/home");
        modelAndView.addObject("userId", authenticationService.currentUser().getId());
        modelAndView.addObject("apartments", apartments);
        modelAndView.addObject("bills", bills);
        return modelAndView;
    }

}
