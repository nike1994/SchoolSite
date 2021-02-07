package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.SubjectModel;
import pl.edu.wszib.school.website.services.IClassService;
import pl.edu.wszib.school.website.services.ISubjectServices;
import pl.edu.wszib.school.website.services.IUserServices;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    ISubjectServices subjectServices;

    @Autowired
    IClassService classService;

    @Autowired
    IUserServices userServices;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createSubjectSite(Model model){
        model.addAttribute("formFragment","fragments/subjectForm.html :: createForm(model=${model},classes=${classes},teachers=${teachers})");
        model.addAttribute("classes", classService.getAll());
        model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
        model.addAttribute("model", new SubjectModel());
        return "FormSite";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createSubject(@ModelAttribute SubjectModel model){
        subjectServices.createSubject(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateSubjectSite(Model model){
        model.addAttribute("formFragment","fragments/subjectForm.html :: updateForm(model=${model},subjects=${subjects},classes=${classes},teachers=${teachers})");
        model.addAttribute("model", new SubjectModel());
        model.addAttribute("subjects", subjectServices.getAllSubjects());
        model.addAttribute("classes", classService.getAll());
        model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
        return "FormSite";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateSubject(@ModelAttribute SubjectModel model){
        subjectServices.updateSubject(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteSubjectSite(Model model){
        model.addAttribute("formFragment","fragments/subjectForm.html :: deleteForm(model=${model},subjects=${subjects})");
        model.addAttribute("model", new SubjectModel());
        model.addAttribute("subjects", subjectServices.getAllSubjects());
        return "FormSite";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteSubject(@ModelAttribute SubjectModel model){
        subjectServices.deleteSubject(model.getId());
        return "redirect:/Home";
    }

}
