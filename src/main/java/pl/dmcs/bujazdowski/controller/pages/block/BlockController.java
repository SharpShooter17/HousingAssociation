package pl.dmcs.bujazdowski.controller.pages.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.domain.Address;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.Block;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Comparator;

@Controller
@RequestScoped
@RequestMapping(value = "/page/block")
public class BlockController {

    private final String basePath = "/page/block";
    private final String listPath = "/list";
    private final String addPath = "/add";
    private final String detailsPath = "/details";

    private final HousingAssociationService service;

    @Autowired
    public BlockController(HousingAssociationService service) {
        this.service = service;
    }

    @RequestMapping(value = listPath)
    public String blocks(Model model) {
        model.addAttribute("blocks", service.findAllBlocks());
        return basePath + listPath;
    }

    @RequestMapping(value = addPath)
    public String addPage(Model model) {
        model.addAttribute("address", new Address());
        return basePath + addPath;
    }

    @RequestMapping(value = addPath, method = RequestMethod.POST)
    public String addAction(@ModelAttribute("address") Address address) {
        service.addBlock(address);
        return "redirect:" + basePath + listPath;
    }

    @RequestMapping(value = detailsPath + "/{blockId}")
    public String block(@PathVariable("blockId") Long blockId, Model model) {
        Block block = service.findBlock(blockId);
        Integer nextNumber = block.getApartments().stream()
                .max(Comparator.comparing(Apartment::getNumber))
                .map(Apartment::getNumber)
                .orElse(0) + 1;

        model.addAttribute("block", block);
        model.addAttribute("apartment", new Apartment(nextNumber));
        return basePath + detailsPath;
    }

    @RequestMapping(value = detailsPath + "/{blockId}/addApartment", method = RequestMethod.POST)
    public String addApartment(@PathVariable("blockId") Long blockId,
                               @ModelAttribute("apartment") Apartment apartment) {
        service.addApartment(blockId, apartment);
        return "redirect:" + basePath + detailsPath + apartment.getBlock().getId();
    }

}
