package pl.dmcs.bujazdowski.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.bujazdowski.dao.BlockRepository;
import pl.dmcs.bujazdowski.domain.Address;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
@RequestMapping(value = "/admin")
public class BlockController {

    private final HousingAssociationService housingAssociationService;
    private final BlockRepository blockRepository;

    @Autowired
    public BlockController(HousingAssociationService housingAssociationService,
                           BlockRepository blockRepository) {
        this.housingAssociationService = housingAssociationService;
        this.blockRepository = blockRepository;
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


}
