package pl.edu.wszib.school.website.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.services.IClassService;
import pl.edu.wszib.school.website.services.IUserServices;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    IUserServices userServices;
    @Resource
    SessionObject sessionObject;


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createParentSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/parentForm.html :: createForm(model=${model})");
                model.addAttribute("model", new ParentModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";

    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createParent(@ModelAttribute ParentModel parentModel, Model model){
        try{
            if (userServices.createParent(parentModel)==0){
                model.addAttribute("formFragment","fragments/parentForm.html :: createForm(model=${model})");
                model.addAttribute("model", new ParentModel());
                model.addAttribute("error","istnieje już urzytkownik o takim loginie");
                return "FormSite";
            }else {
                return "redirect:/Home";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/parentForm.html :: createForm(model=${model})");
            model.addAttribute("model", new ParentModel());
            model.addAttribute("error","wystąpił błąd podczas tworzenia konta rodzica");
            return "FormSite";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateParentSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/parentForm.html :: updateForm(model=${model},parents=${parents})");
                model.addAttribute("model", new ParentModel());
                model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
                return "FormSite";
            }
        }
        return "redirect:/Home";

    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateParent(@ModelAttribute ParentModel parentModel,Model model){
        try {
            if (userServices.updateParent(parentModel)){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/parentForm.html :: updateForm(model=${model},parents=${parents})");
                model.addAttribute("model", new ParentModel());
                model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
                model.addAttribute("error","wprowadzono błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/parentForm.html :: updateForm(model=${model},parents=${parents})");
            model.addAttribute("model", new ParentModel());
            model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
            model.addAttribute("error","wystapił błąd podczas aktualizacji");
            return "FormSite";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteParentSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/parentForm.html :: deleteForm(model=${model},parents=${parents})");
                model.addAttribute("model", new ParentModel());
                model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
                return "FormSite";
            }
        }
        return "redirect:/Home";

    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteParent(@ModelAttribute ParentModel parentModel,Model model){
        try {
            if (userServices.deleteParentandPupils(parentModel.getUser_id())){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/parentForm.html :: deleteForm(model=${model},parents=${parents})");
                model.addAttribute("model", new ParentModel());
                model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
                model.addAttribute("error","brak konta o podanym id");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/parentForm.html :: deleteForm(model=${model},parents=${parents})");
            model.addAttribute("model", new ParentModel());
            model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
            model.addAttribute("error", "wystąpił błąd podczas usuwania konta");
            return "FormSite";
        }
    }

}
