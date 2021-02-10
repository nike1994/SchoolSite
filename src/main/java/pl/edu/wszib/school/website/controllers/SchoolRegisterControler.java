package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.services.Impl.SchoolRegisterService;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class SchoolRegisterControler {

    @Resource
    SessionObject sessionObject;

    @Autowired
    SchoolRegisterService schoolRegisterService;


    @RequestMapping(value = "/Dziennik", method = RequestMethod.GET)
    public String schoolRegisterSite(Model model) {
        if(this.sessionObject.isLogged()){
            model.addAttribute("user_role", sessionObject.getLoggedUser().getRole().name());
            //daje listę dzieci,klas lub null w zależności od urzytkownika
            model.addAttribute("dropdown", schoolRegisterService.getDropdownButtons(sessionObject.getLoggedUser()));
            model.addAttribute("table", schoolRegisterService.getTableJSON(sessionObject.getLoggedUser()));
            return "schoolRegister";
        }
        return "redirect:/login";
    }


}
