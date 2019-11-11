package pl.dmcs.bujazdowski.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.dao.ApartmentRepository;
import pl.dmcs.bujazdowski.dao.BlockRepository;
import pl.dmcs.bujazdowski.domain.Address;
import pl.dmcs.bujazdowski.domain.Apartment;
import pl.dmcs.bujazdowski.domain.Block;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.util.Comparator;

@Controller
@RequestScoped
@RequestMapping(value = "/admin")
public class BlockController {

    private final HousingAssociationService housingAssociationService;
    private final BlockRepository blockRepository;
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public BlockController(HousingAssociationService housingAssociationService,
                           BlockRepository blockRepository,
                           ApartmentRepository apartmentRepository) {
        this.housingAssociationService = housingAssociationService;
        this.blockRepository = blockRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @RequestMapping(value = "/blocks")
    public String blocks(Model model) {
        model.addAttribute("blocks", blockRepository.findAll());
        model.addAttribute("address", new Address());
        return "/admin/blocks";
    }

    @RequestMapping(value = "/addBlock", method = RequestMethod.POST)
    public String addBlock(@ModelAttribute("address") Address address) {
        housingAssociationService.addBlock(address);
        return "redirect:/admin/blocks";
    }

    @RequestMapping(value = "/block/{blockId}")
    public String block(@PathVariable("blockId") Long blockId, Model model) {
        Block block = housingAssociationService.findBlock(blockId);
        Integer nextNumber = block.getApartments().stream()
                .max(Comparator.comparing(Apartment::getNumber))
                .map(Apartment::getNumber)
                .orElse(0) + 1;

        model.addAttribute("block", block);
        model.addAttribute("apartment", new Apartment(nextNumber));
        return "/admin/block";
    }

    @RequestMapping(value = "/block/{blockId}/addApartment", method = RequestMethod.POST)
    public String addApartment(@PathVariable("blockId") Long blockId,
                               @ModelAttribute("apartment") Apartment apartment) {
        housingAssociationService.addApartment(blockId, apartment);
        return "redirect:/admin/block/" + apartment.getBlock().getId();
    }


}
