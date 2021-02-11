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
import pl.edu.wszib.school.website.model.View.TeacherModel;
import pl.edu.wszib.school.website.services.IUserServices;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    IUserServices userServices;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTeacherSite(Model model){
        if(sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/teacherForm.html :: createForm(model=${model})");
                model.addAttribute("model", new TeacherModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createTeacher(@ModelAttribute TeacherModel modelTeacher, Model model){
        try{
            int id = userServices.createTeacher(modelTeacher);
            if(id==0){
                model.addAttribute("formFragment","fragments/teacherForm.html :: createForm(model=${model})");
                model.addAttribute("model", new TeacherModel());
                model.addAttribute("error","istnieje już taki login");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/teacherForm.html :: createForm(model=${model})");
            model.addAttribute("model", new TeacherModel());
            model.addAttribute("error","wystąpił błąd podczas tworzenia konta dla nauczyciela");
            return "FormSite";
        }

        return "redirect:/Home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateTeacherSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/teacherForm.html :: updateForm(model=${model},teachers=${teachers})");
                model.addAttribute("model", new TeacherModel());
                model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateTeacher(@ModelAttribute TeacherModel modelTeacher,Model model){
        try{
            if (userServices.updateTeacher(modelTeacher)){
                return "redirect:/Home";
            }else{
                model.addAttribute("formFragment","fragments/teacherForm.html :: updateForm(model=${model},teachers=${teachers})");
                model.addAttribute("model", new TeacherModel());
                model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
                model.addAttribute("error","istnieje już taki login bądź konto o takim id nie istnieje");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/teacherForm.html :: updateForm(model=${model},teachers=${teachers})");
            model.addAttribute("model", new TeacherModel());
            model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
            model.addAttribute("error","wystąpił błąd podczas aktualizacji konta nauczyciela");
            return "FormSite";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteTeacherSite(Model model){
        if(sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/teacherForm.html :: deleteForm(model=${model},teachers=${teachers})");
                model.addAttribute("model", new TeacherModel());
                model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteTeacher(@ModelAttribute ParentModel modelUser, Model model){
        try{
            if(userServices.deleteTeacher(modelUser.getUser_id())){
                return "redirect:/Home";
            }else{
                model.addAttribute("formFragment","fragments/teacherForm.html :: deleteForm(model=${model},teachers=${teachers})");
                model.addAttribute("model", new TeacherModel());
                model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
                model.addAttribute("error", "nie naleziono takiego konta");
                return "FormSite";
            }

        }catch (Exception e){
            model.addAttribute("formFragment","fragments/teacherForm.html :: deleteForm(model=${model},teachers=${teachers})");
            model.addAttribute("model", new TeacherModel());
            model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
            model.addAttribute("error", "wystąpił błąd podczas usuwania konta nauczyciela");
            return "FormSite";
        }
    }
}
