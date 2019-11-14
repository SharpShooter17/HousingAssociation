package pl.dmcs.bujazdowski.controller.page.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
    private final String detailsPath = "/details";
    private final String blockIdVariable = "blockId";

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
    public String addAction(@PathVariable(blockIdVariable) Long blockId,
                            @ModelAttribute("apartment") Apartment apartment) {
        service.addApartment(blockId, apartment);
        return "redirect:/page/block/details/" + blockId;
    }

    @RequestMapping(value = detailsPath + "/{apartmentId}")
    public ModelAndView detailsPage(@PathVariable(blockIdVariable) Long blockId,
                                    @PathVariable("apartmentId") Long apartmentId) {
        ModelAndView modelAndView = new ModelAndView("/page/block/apartment" + detailsPath);
        modelAndView.addObject("apartment", service.findApartment(apartmentId));
        modelAndView.addObject("blockId", blockId);
        return modelAndView;
    }
}
