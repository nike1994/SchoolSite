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
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping("/pupil")
public class PupilController {
    @Autowired
    IUserServices userServices;
    @Autowired
    IClassService classService;
    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPupilSite(Model model){
        if (sessionObject.isLogged()){
            if(sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/pupilForm.html :: createForm(model=${model},parents=${parents},classes=${classes})");
                model.addAttribute("model", new PupilModel());
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));

                return "FormSite";
            }
        }
        return "redirect:/Home";

    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createPupil(@ModelAttribute PupilModel modelPupil, Model model){
        try{
            if(userServices.createPupil(modelPupil)==0){
                model.addAttribute("formFragment","fragments/pupilForm.html :: createForm(model=${model},parents=${parents},classes=${classes})");
                model.addAttribute("model", new PupilModel());
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
                model.addAttribute("error","istnieje już taki login bądź wprowadzona klasa o danym id już nie istnieje");
                return "FormSite";
            }else{
                return "redirect:/Home";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/pupilForm.html :: createForm(model=${model},parents=${parents},classes=${classes})");
            model.addAttribute("model", new PupilModel());
            model.addAttribute("classes", classService.getAll());
            model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
            model.addAttribute("error","wystąpił błąd podczas tworzenia konta dla ucznia");
            return "FormSite";
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePupilSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/pupilForm.html :: updateForm(model=${model},parents=${parents},classes=${classes},pupils=${pupils})");
                model.addAttribute("model", new PupilModel());
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
                model.addAttribute("pupils", userServices.getUsersByRole(User.Role.PUPIL));
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updatePupil(@ModelAttribute PupilModel pupilModel, Model model){
        try {
            if(userServices.updatePupil(pupilModel)){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/pupilForm.html :: updateForm(model=${model},parents=${parents},classes=${classes},pupils=${pupils})");
                model.addAttribute("model", new PupilModel());
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
                model.addAttribute("pupils", userServices.getUsersByRole(User.Role.PUPIL));
                model.addAttribute("error","wprowadzono błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/pupilForm.html :: updateForm(model=${model},parents=${parents},classes=${classes},pupils=${pupils})");
            model.addAttribute("model", new PupilModel());
            model.addAttribute("classes", classService.getAll());
            model.addAttribute("parents", userServices.getUsersByRole(User.Role.PARENT));
            model.addAttribute("pupils", userServices.getUsersByRole(User.Role.PUPIL));
            model.addAttribute("error","wystąpił błąd podczas aktualizacji ucznia");
            return "FormSite";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteParentSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/pupilForm.html :: deleteForm(model=${model},pupils=${pupils})");
                model.addAttribute("model", new PupilModel());
                model.addAttribute("pupils", userServices.getUsersByRole(User.Role.PUPIL));
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteParent(@ModelAttribute ParentModel parentModel, Model model){
        try {
            if(userServices.deletePupil(parentModel.getUser_id())){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/pupilForm.html :: deleteForm(model=${model},pupils=${pupils})");
                model.addAttribute("model", new PupilModel());
                model.addAttribute("pupils", userServices.getUsersByRole(User.Role.PUPIL));
                model.addAttribute("error", "brak ucznia o podanym id");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/pupilForm.html :: deleteForm(model=${model},pupils=${pupils})");
            model.addAttribute("model", new PupilModel());
            model.addAttribute("pupils", userServices.getUsersByRole(User.Role.PUPIL));
            model.addAttribute("error", "wystąpił błąd podczas usuwania konta ucznia");
            return "FormSite";
        }


    }

}
