package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.View.PasswordUpdateModel;
import pl.edu.wszib.school.website.services.IUserServices;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class LoginControler {
    @Autowired
    IUserServices userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        if(this.sessionObject.isLogged()) {
            return "redirect:/Home";
        }
        model.addAttribute("loginModel", new Login());
        return "loginSite";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String  login(@ModelAttribute Login login, Model model) {
        this.userService.authenticate(login);
        if(this.sessionObject.isLogged()) {
            return "redirect:/Home";
        }
        model.addAttribute("loginModel", new Login());
        model.addAttribute("error","zły login lub hasło");
        return "loginSite";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.userService.logout();
        return "redirect:/login";
    }

    @RequestMapping(value = "/passwordUpdate", method = RequestMethod.GET)
    public String passwordUpdateSite(Model model) {
        if(sessionObject.isLogged()){
            model.addAttribute("formFragment","fragments/passwordForm.html :: Form(model=${model}, hash = ${hash})");
            model.addAttribute("model", new PasswordUpdateModel());
            model.addAttribute("hash", userService.getHashPass(sessionObject.getLoggedUser()));
            return "FormSite";
        }else{
            return "redirect:/Home";
        }

    }

    @RequestMapping(value="/passwordUpdate",method = RequestMethod.POST)
    public String passwordUpdate(@ModelAttribute PasswordUpdateModel modelPass, Model model){
        modelPass.setUser(sessionObject.getLoggedUser());
        try {
            userService.passwordUpdate(modelPass);
        }catch (Exception e){
            model.addAttribute("error","wystąpił błąd podczas zmiany hasła");
        }
        return "redirect:/Home";
    }
}
