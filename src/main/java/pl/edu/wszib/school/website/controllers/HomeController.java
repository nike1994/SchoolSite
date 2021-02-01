package pl.edu.wszib.school.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model){
        String emails[]= new String[]{"szkola@sp.pl"};
        String telephon[]= new String[]{"234-432-233","429-432-134"};
        Map<String,Object> sites= new HashMap<String,Object>(){{
            put("home","main");
            put("dropdown",new HashMap<String,Object>(){{
                put("site1","bla/site1");
                put("site2","bla/site2");
            }});
            put("site4","site4");
        }};

// TODO: 31.01.2021 trzeba stworzyć możliwość edycji tekstowej zawartości strony
// TODO: 01.02.2021 stworzenie strony dla administratora/ dodanie usuwanie edycja stron / dodanie usuwanie edycja uczniów, nauczycieli, rodziców
// TODO: 01.02.2021 stworzenie strony dla nauczyciela edycja postu/ dodanie usuwanie stron z postami/


        model.addAttribute("sites",sites);
        model.addAttribute("telephons",telephon);
        model.addAttribute("emails",emails);
        model.addAttribute("siteName","Nazwa Szkoły");
        model.addAttribute("siteLogo","./img/logo.svg");
        model.addAttribute("active", "home");
        return "main";
    }

}
