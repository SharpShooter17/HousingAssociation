package pl.dmcs.bujazdowski.controller.pages.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Set;

@Controller
@RequestScoped
@RequestMapping(value = "/page/block/details/{blockId}/apartment/details/{apartmentId}/occupants")
public class OccupantsController {

    private final String editPath = "/edit";

    private final HousingAssociationService service;

    @Autowired
    public OccupantsController(HousingAssociationService service) {
        this.service = service;
    }

    @RequestMapping(value = editPath)
    public ModelAndView editPage(@PathVariable("blockId") Long blockId,
                                 @PathVariable("apartmentId") Long apartmentId) {
        ModelAndView modelAndView = new ModelAndView("/page/block/apartment/occupants/edit");
        Apartment apartment = service.findApartment(apartmentId);
        Set<User> availableOccupants = service.findAllOccupants();
        modelAndView.addObject("occupants", apartment.getOccupants());
        modelAndView.addObject("availableOccupants", availableOccupants);
        return modelAndView;
    }


    @RequestMapping(value = editPath, method = RequestMethod.POST)
    public String editAction(@PathVariable("blockId") Long blockId,
                             @PathVariable("apartmentId") Long apartmentId,
                             @ModelAttribute("occupants") Set<User> occupants) {
        service.updateOccupants(apartmentId, occupants);
        return "redirect:/page/block/details/" + blockId + "/apartment/details/" + apartmentId;
    }

}
