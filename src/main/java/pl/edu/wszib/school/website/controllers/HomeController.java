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


// TODO: 31.01.2021 trzeba stworzyć możliwość edycji tekstowej zawartości strony
// TODO: 06.02.2021 nie można wejść do trybu edycji nie będąc zalogowanym
        // TODO: 06.02.2021 nie można dać dwóch stron o takich samych tytułach
        return "main";
    }


}
