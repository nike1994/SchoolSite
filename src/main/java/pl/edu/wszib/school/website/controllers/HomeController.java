package pl.edu.wszib.school.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/Home";
    }


    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String main(Model model) {
        return "main";
    }


}
