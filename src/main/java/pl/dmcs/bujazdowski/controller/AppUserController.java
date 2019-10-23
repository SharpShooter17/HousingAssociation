package pl.dmcs.bujazdowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.bujazdowski.domain.AppUser;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
public class AppUserController {

    private AppUser appUser = new AppUser();

    @RequestMapping(value = "/appUsers")
    public ModelAndView showAppUsers() {

        appUser.setFirstName("Bartosz");
        appUser.setLastName("Ujazdowski");
        appUser.setEmail("buja@wp.pl");
        appUser.setTelephone("123456789");

        return new ModelAndView("appUser", "appUser", new AppUser());
    }

    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@ModelAttribute("appUser") AppUser appUser) {

        System.out.println("First Name: " + appUser.getFirstName() +
                " Last Name: " + appUser.getLastName() + " Tel.: " +
                appUser.getTelephone() + " Email: " + appUser.getEmail());

        return "redirect:appUsers";
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}

