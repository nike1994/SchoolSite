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
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    ISubjectServices subjectServices;

    @Autowired
    IClassService classService;

    @Autowired
    IUserServices userServices;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createSubjectSite(Model model){
        if(sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/subjectForm.html :: createForm(model=${model},classes=${classes},teachers=${teachers})");
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
                model.addAttribute("model", new SubjectModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createSubject(@ModelAttribute SubjectModel modelSubject, Model model){
        try{
            if (subjectServices.createSubject(modelSubject)){
                return "redirect:/Home";
            }else{
                model.addAttribute("formFragment","fragments/subjectForm.html :: createForm(model=${model},classes=${classes},teachers=${teachers})");
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
                model.addAttribute("model", new SubjectModel());
                model.addAttribute("error","nie istnieje taki nauczyciel bądź klasa o podanym id");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/subjectForm.html :: createForm(model=${model},classes=${classes},teachers=${teachers})");
            model.addAttribute("classes", classService.getAll());
            model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
            model.addAttribute("model", new SubjectModel());
            model.addAttribute("error","wystąpił błąd podczas tworzenia przedmiotu");
            return "FormSite";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateSubjectSite(Model model){
        if(sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)) {
                model.addAttribute("formFragment","fragments/subjectForm.html :: updateForm(model=${model},subjects=${subjects},classes=${classes},teachers=${teachers})");
                model.addAttribute("model", new SubjectModel());
                model.addAttribute("subjects", subjectServices.getAllSubjects());
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateSubject(@ModelAttribute SubjectModel modelSubject , Model model){
        try{
            if (subjectServices.updateSubject(modelSubject)) {
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/subjectForm.html :: updateForm(model=${model},subjects=${subjects},classes=${classes},teachers=${teachers})");
                model.addAttribute("model", new SubjectModel());
                model.addAttribute("subjects", subjectServices.getAllSubjects());
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
                model.addAttribute("error", "wprowadzono błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/subjectForm.html :: updateForm(model=${model},subjects=${subjects},classes=${classes},teachers=${teachers})");
            model.addAttribute("model", new SubjectModel());
            model.addAttribute("subjects", subjectServices.getAllSubjects());
            model.addAttribute("classes", classService.getAll());
            model.addAttribute("teachers", userServices.getUsersByRole(User.Role.TEACHER));
            model.addAttribute("error", "wystąpił błąd podczas aktualizowania przedmiotu");
            return "FormSite";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteSubjectSite(Model model){
        if(sessionObject.isLogged()) {
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)) {
                model.addAttribute("formFragment","fragments/subjectForm.html :: deleteForm(model=${model},subjects=${subjects})");
                model.addAttribute("model", new SubjectModel());
                model.addAttribute("subjects", subjectServices.getAllSubjects());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteSubject(@ModelAttribute SubjectModel modelSubject, Model model){
        try{
            if (subjectServices.deleteSubject(modelSubject.getId())) {
                return "redirect:/Home";
            }else{
                model.addAttribute("formFragment","fragments/subjectForm.html :: deleteForm(model=${model},subjects=${subjects})");
                model.addAttribute("model", new SubjectModel());
                model.addAttribute("subjects", subjectServices.getAllSubjects());
                model.addAttribute("error","brak przedmiotu o podanym ID");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/subjectForm.html :: deleteForm(model=${model},subjects=${subjects})");
            model.addAttribute("model", new SubjectModel());
            model.addAttribute("subjects", subjectServices.getAllSubjects());
            model.addAttribute("error","wystąpił błąd podczas usuwania ");
            return "FormSite";
        }

    }

}
