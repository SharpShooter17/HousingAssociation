package pl.dmcs.bujazdowski.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
public class LoginController {

    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }
}
