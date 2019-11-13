package pl.dmcs.bujazdowski.controller.pages.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.Block;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Comparator;

@Controller
@RequestScoped
@RequestMapping(value = "/page/block/details/{blockId}/apartment/")
public class ApartmentController {

    private final String addPath = "/add";

    private final HousingAssociationService service;

    @Autowired
    public ApartmentController(HousingAssociationService service) {
        this.service = service;
    }

    @RequestMapping(value = addPath)
    public String addPage(@PathVariable("blockId") Long blockId, Model model) {
        Block block = service.findBlock(blockId);
        Integer nextNumber = block.getApartments().stream()
                .max(Comparator.comparing(Apartment::getNumber))
                .map(Apartment::getNumber)
                .orElse(0) + 1;

        model.addAttribute("blockId", blockId);
        model.addAttribute("apartment", new Apartment(nextNumber));
        return "/page/block/apartment/add";
    }

    @RequestMapping(value = addPath, method = RequestMethod.POST)
    public String addAction(@PathVariable("blockId") Long blockId,
                            @ModelAttribute("apartment") Apartment apartment) {
        service.addApartment(blockId, apartment);
        return "redirect:/page/block/details/" + blockId;
    }
}
