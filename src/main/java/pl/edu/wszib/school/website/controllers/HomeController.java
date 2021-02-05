package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.View.CreationPupilModel;
import pl.edu.wszib.school.website.model.View.CreationTeacherParentModel;
import pl.edu.wszib.school.website.services.IClassService;
import pl.edu.wszib.school.website.services.IPageServices;
import pl.edu.wszib.school.website.services.IUserServices;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;


import java.util.*;


@Controller
public class HomeController {

    //@Autowired
    //IUserServices userServices;

//    @Autowired
//    IClassService classService;

    @Autowired
    IWebsiteInformationsService websiteInformationsService;

    @Autowired
    IPageServices pageServices;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landingPage() {


        return "redirect:/main";
    }

//    @RequestMapping(value = "/remove", method = RequestMethod.GET)
//    public String addPage() {
//        userServices.deleteParentandPupils(1);
//
//
//        return "redirect:/main";
//    }

//    @RequestMapping(value = "/addPupil", method = RequestMethod.GET)
//    public String addPupil() {
//        CreationTeacherParentModel parentModel = new CreationTeacherParentModel("login","haslo","imie","nazwisko",new ArrayList<Integer>());
//        int id = userServices.createParent(parentModel);
//
//        SchoolClass clas = new SchoolClass();
//        clas.setYear(2020);
//        clas.setName("IIA");
//
//        int idClass = classService.createClass(clas);
//
//        CreationPupilModel pupilModel = new CreationPupilModel("loginu","haslou","imieu","nazwiskou", id, idClass);
//
//        userServices.createPupil(pupilModel);
//
//        return "redirect:/main";
//    }


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {


// TODO: 31.01.2021 trzeba stworzyć możliwość edycji tekstowej zawartości strony
// TODO: 01.02.2021 stworzenie strony dla administratora/ dodanie usuwanie edycja stron / dodanie usuwanie edycja uczniów, nauczycieli, rodziców
// TODO: 01.02.2021 stworzenie strony dla nauczyciela edycja postu/ dodanie usuwanie stron z postami/



//        model.addAttribute("sites",sites);
//        model.addAttribute("telephons",telephon);
//        model.addAttribute("emails",emails);
//        model.addAttribute("siteName","Nazwa Szkoły");
//        model.addAttribute("siteLogo","./img/logo.svg");
//        model.addAttribute("active", "home");

        return "main";
    }


    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String main() {
        Page parent = new Page();
        parent.setTitle("aktualności");

        Page child = new Page();
        child.setTitle("Wiosna2021");
        child.setParent(parent);

        Page child2 = new Page();
        child2.setTitle("Wiosna2021"); // hashset nie może mieć dwóch takich samych kluczy/nazwy
        child2.setParent(parent);

        int id = pageServices.createPage(parent);
        pageServices.createPage(child);
        pageServices.createPage(child2);

        Set<Page> pages = pageServices.getByID(id).getChildren();
        System.out.println();
        System.out.println(pages.size());
        for (Page page: pages
             ) {

            System.out.println(page.getTitle());
        }

        return "redirect:/main";
    }



}
