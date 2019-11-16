package pl.dmcs.bujazdowski.controller.page.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.controller.model.OccupantModel;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.User;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestScoped
@RequestMapping(value = "/page/block/details/{blockId}/apartment/details/{apartmentId}/occupant")
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
        ModelAndView modelAndView = new ModelAndView("/page/block/apartment/occupant/edit");
        Apartment apartment = service.findApartment(apartmentId);
        Set<String> availableOccupantEmails = service.findAllOccupants().stream()
                .map(User::getEmail)
                .collect(Collectors.toSet());
        Set<String> occupants = apartment.getOccupants().stream()
                .map(User::getEmail)
                .collect(Collectors.toSet());

        OccupantModel model = new OccupantModel();
        model.setOccupantEmails(occupants);

        modelAndView.addObject("blockId", blockId);
        modelAndView.addObject("apartmentId", apartmentId);
        modelAndView.addObject("availableOccupants", availableOccupantEmails);
        modelAndView.addObject("occupantModel", model);
        return modelAndView;
    }


    @RequestMapping(value = editPath, method = RequestMethod.POST)
    public String editAction(@PathVariable("blockId") Long blockId,
                             @PathVariable("apartmentId") Long apartmentId,
                             @ModelAttribute("occupantModel") OccupantModel occupantModel) {
        service.updateOccupants(apartmentId, occupantModel.getOccupantEmails());
        return "redirect:/page/block/details/" + blockId + "/apartment/details/" + apartmentId;
    }

}
