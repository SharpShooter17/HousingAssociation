package pl.dmcs.bujazdowski.controller.page.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.domain.Bill;
import pl.dmcs.bujazdowski.domain.BillingType;
import pl.dmcs.bujazdowski.exception.ApplicationException;
import pl.dmcs.bujazdowski.service.HousingAssociationService;

import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;

@Controller
@RequestScoped
@RequestMapping(value = "/page/block/details/{blockId}/apartment/details/{apartmentId}/bill")
public class BillController {

    private final String addPath = "/add";
    private final String fullAddPage = "/page/block/apartment/bill/add";

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

        ModelAndView modelAndView = new ModelAndView(fullAddPage);
        modelAndView.addObject("blockId", blockId);
        modelAndView.addObject("apartmentId", apartmentId);
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("availableTypes", Arrays.asList(BillingType.values()));
        return modelAndView;
    }

    @RequestMapping(value = addPath, method = RequestMethod.POST)
    public String addAction(@PathVariable("blockId") Long blockId,
                            @PathVariable("apartmentId") Long apartmentId,
                            @Valid @ModelAttribute("bill") Bill bill,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blockId", blockId);
            model.addAttribute("apartmentId", apartmentId);
            model.addAttribute("bill", bill);
            model.addAttribute("availableTypes", Arrays.asList(BillingType.values()));
            return fullAddPage;
        }

        service.addBill(apartmentId, bill);
        return "redirect:/page/block/details/" + blockId + "/apartment/details/" + apartmentId;
    }

    @RequestMapping(value = "/download/{billId}")
    public void download(@PathVariable("billId") Long billId,
                         HttpServletResponse response) {
        ByteArrayOutputStream file = service.downloadBillReport(billId);
        try {
            InputStream inputStream = new ByteArrayInputStream(file.toByteArray());
            response.setContentType("application/pdf");
            org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new ApplicationException("IOError writing file to output stream");
        }
    }
}
