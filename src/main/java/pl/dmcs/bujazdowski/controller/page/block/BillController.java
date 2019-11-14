package pl.dmcs.bujazdowski.controller.page.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.domain.Bill;
import pl.dmcs.bujazdowski.domain.BillingType;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestScoped
@RequestMapping(value = "/page/block/details/{blockId}/apartment/details/{apartmentId}/bill")
public class BillController {

    private final String addPath = "/add";

    private final HousingAssociationService service;

    @Autowired
    public BillController(HousingAssociationService service) {
        this.service = service;
    }

    @RequestMapping(value = addPath)
    public ModelAndView addPage(@PathVariable("blockId") Long blockId,
                                @PathVariable("apartmentId") Long apartmentId) {
        Bill bill = new Bill();
        bill.setDate(LocalDate.now());
        bill.setType(BillingType.ELECTRICITY);
        bill.setAmount(0.0);

        ModelAndView modelAndView = new ModelAndView("/page/block/apartment/bill/add");
        modelAndView.addObject("blockId", blockId);
        modelAndView.addObject("apartmentId", apartmentId);
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("availableTypes", Arrays.asList(BillingType.values()));
        return modelAndView;
    }

    @RequestMapping(value = addPath, method = RequestMethod.POST)
    public String addAction(@PathVariable("blockId") Long blockId,
                            @PathVariable("apartmentId") Long apartmentId,
                            @ModelAttribute("bill") Bill bill) {
        service.addBill(apartmentId, bill);
        return "redirect:/page/block/details/" + blockId + "/apartment/details/" + apartmentId;
    }
}
