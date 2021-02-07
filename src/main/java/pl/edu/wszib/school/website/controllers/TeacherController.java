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

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    IUserServices userServices;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTeacherSite(Model model){
        model.addAttribute("formFragment","fragments/teacherForm.html :: createForm(model=${model})");
        model.addAttribute("model", new TeacherModel());
        return "FormSite";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createTeacher(@ModelAttribute TeacherModel model){
        userServices.createTeacher(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateTeacherSite(Model model){
        model.addAttribute("formFragment","fragments/teacherForm.html :: updateForm(model=${model},teachers=${teachers})");
        model.addAttribute("model", new TeacherModel());
        model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
        return "FormSite";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateTeacher(@ModelAttribute TeacherModel model){
        userServices.updateTeacher(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteTeacherSite(Model model){
        model.addAttribute("formFragment","fragments/teacherForm.html :: deleteForm(model=${model},teachers=${teachers})");
        model.addAttribute("model", new TeacherModel());
        model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
        return "FormSite";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteTeacher(@ModelAttribute ParentModel model){
        userServices.deleteTeacher(model.getUser_id());
        return "redirect:/Home";
    }
}
