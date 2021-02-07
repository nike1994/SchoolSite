package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.services.IUserServices;

@Controller
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    IUserServices userServices;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createParentSite(Model model){
        model.addAttribute("formFragment","fragments/parentForm.html :: createForm(model=${model})");
        model.addAttribute("model", new ParentModel());
        return "FormSite";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createParent(@ModelAttribute ParentModel model){
        userServices.createParent(model);
        // TODO: 06.02.2021 sprawdzenie czy taki login już istnieje
        // TODO: 06.02.2021 trzeba do bazy danych dodać warunki że muszą być unikalne loginy i tytuły stron
        return "redirect:/Home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateParentSite(Model model){
        model.addAttribute("formFragment","fragments/parentForm.html :: updateForm(model=${model},parents=${parents})");
        model.addAttribute("model", new ParentModel());
        model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
        return "FormSite";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateParent(@ModelAttribute ParentModel model){
        userServices.updateParent(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteParentSite(Model model){
        model.addAttribute("formFragment","fragments/parentForm.html :: deleteForm(model=${model},parents=${parents})");
        model.addAttribute("model", new ParentModel());
        model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
        return "FormSite";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteParent(@ModelAttribute ParentModel model){
        userServices.deleteParentandPupils(model.getUser_id());
        return "redirect:/Home";
    }

}
