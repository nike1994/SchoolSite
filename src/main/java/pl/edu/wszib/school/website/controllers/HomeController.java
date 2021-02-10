package pl.edu.wszib.school.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.*;


@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/Home";
    }


    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String main(Model model) {


// TODO: 31.01.2021 trzeba stworzyć możliwość edycji tekstowej zawartości strony
// TODO: 01.02.2021 stworzenie strony dla administratora/ dodanie usuwanie edycja stron / dodanie usuwanie edycja uczniów, nauczycieli, rodziców
// TODO: 06.02.2021 nie można wejść do trybu edycji nie będąc zalogowanym
        // TODO: 06.02.2021 nie można dać dwóch stron o takich samych tytułach
        // TODO: 07.02.2021 trzeba zrobić okna dialogowe z informacjami o błędach dla urzytkownika
        return "main";
    }


}
