package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.model.View.PupilModel;
import pl.edu.wszib.school.website.services.IClassService;
import pl.edu.wszib.school.website.services.IUserServices;

@Controller
@RequestMapping("/pupil")
public class PupilController {
    @Autowired
    IUserServices userServices;
    @Autowired
    IClassService classService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createParentSite(Model model){
        model.addAttribute("formFragment","fragments/pupilForm.html :: createForm(model=${model},parents=${parents},classes=${classes})");
        model.addAttribute("model", new PupilModel());
        model.addAttribute("classes", classService.getAll());
        model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));

        return "FormSite";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createParent(@ModelAttribute PupilModel model){
        userServices.createPupil(model);
        // TODO: 06.02.2021 sprawdzenie czy taki login już istnieje
        // TODO: 06.02.2021 trzeba do bazy danych dodać warunki że muszą być unikalne loginy i tytuły stron
        return "redirect:/Home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateParentSite(Model model){
        model.addAttribute("formFragment","fragments/pupilForm.html :: updateForm(model=${model},parents=${parents},classes=${classes},pupils=${pupils})");
        model.addAttribute("model", new PupilModel());
        model.addAttribute("classes", classService.getAll());
        model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
        model.addAttribute("pupils", userServices.getUsersByRole(User.Role.PUPIL));
        return "FormSite";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateParent(@ModelAttribute PupilModel model){
        userServices.updatePupil(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteParentSite(Model model){
        model.addAttribute("formFragment","fragments/pupilForm.html :: deleteForm(model=${model},pupils=${pupils})");
        model.addAttribute("model", new PupilModel());
        model.addAttribute("pupils", userServices.getUsersByRole(User.Role.PUPIL));
        return "FormSite";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteParent(@ModelAttribute ParentModel model){
        userServices.deletePupil(model.getUser_id());
        return "redirect:/Home";
    }

}
