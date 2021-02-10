package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.model.View.PasswordUpdateModel;
import pl.edu.wszib.school.website.model.WebsiteInformations;
import pl.edu.wszib.school.website.services.IUserServices;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class LoginControler {
    @Autowired
    IUserServices userService;

    @Autowired
    IWebsiteInformationsService webInfoService;

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
    public String login(@ModelAttribute Login login) {
        this.userService.authenticate(login);

        if(this.sessionObject.isLogged()) {
            return "redirect:/Home";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.userService.logout();
        return "redirect:/login";
    }

    @RequestMapping(value = "/passwordUpdate", method = RequestMethod.GET)
    public String passwordUpdateSite(Model model) {
        model.addAttribute("formFragment","fragments/passwordForm.html :: Form(model=${model})");
        model.addAttribute("model", new PasswordUpdateModel());
        // TODO: 08.02.2021 JS sprawdzić czy jest inne od starego można dac hash starego i porównać 
        return "FormSite";
    }

    @RequestMapping(value="/passwordUpdate",method = RequestMethod.POST)
    public String passwordUpdate(@ModelAttribute PasswordUpdateModel model){
        model.setUser(sessionObject.getLoggedUser());
        userService.passwordUpdate(model);
        return "redirect:/Home";
    }

}
