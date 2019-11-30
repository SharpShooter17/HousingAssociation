package pl.dmcs.bujazdowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.faces.bean.RequestScoped;

@Controller
@RequestScoped
@RequestMapping("/error")
public class ExceptionController {

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "/error/accessDenied";
    }

}
