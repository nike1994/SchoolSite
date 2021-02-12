package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.ClassModel;
import pl.edu.wszib.school.website.services.IClassService;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    IClassService classService;

    @Resource
    SessionObject sessionObject;



    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createClassSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/classForm.html :: createForm(model=${model})");
                model.addAttribute("model", new ClassModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createClass(@ModelAttribute ClassModel classModel, Model model){
        try{
         if(classService.createClass(classModel)){
             return "redirect:/Home";
         }else{
             model.addAttribute("formFragment","fragments/classForm.html :: createForm(model=${model})");
             model.addAttribute("model", new ClassModel());
             model.addAttribute("error", "wprowadzono błędne dane");
             return "FormSite";
         }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/classForm.html :: createForm(model=${model})");
            model.addAttribute("model", new ClassModel());
            model.addAttribute("error", "wystąpił błąd podczas tworzenia klasy");
            return "FormSite";
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateClassSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/classForm.html :: updateForm(classes=${classes},model=${model})");
                model.addAttribute("model", new ClassModel());
                model.addAttribute("classes", classService.getAll());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateClass(@ModelAttribute ClassModel classModel,Model model){
        try{
            if(classService.updateClass(classModel)){
                return "redirect:/Home";
            }else{
                model.addAttribute("formFragment","fragments/classForm.html :: updateForm(classes=${classes},model=${model})");
                model.addAttribute("model", new ClassModel());
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("error", "wprowadzono błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/classForm.html :: updateForm(classes=${classes},model=${model})");
            model.addAttribute("model", new ClassModel());
            model.addAttribute("classes", classService.getAll());
            model.addAttribute("error", "wystąpił błąd podczas aktualizacji klasy");
            return "FormSite";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteClassSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/classForm.html :: deleteForm(classes=${classes},model=${model})");
                model.addAttribute("model", new ClassModel());
                model.addAttribute("classes", classService.getAll());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteClass(@ModelAttribute ClassModel classModel,Model model){
        try{
            if(classService.deleteClass(classModel.getId())){
                return "redirect:/Home";
            }else{
                model.addAttribute("formFragment","fragments/classForm.html :: deleteForm(classes=${classes},model=${model})");
                model.addAttribute("model", new ClassModel());
                model.addAttribute("classes", classService.getAll());
                model.addAttribute("error", "wprowadzono błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/classForm.html :: deleteForm(classes=${classes},model=${model})");
            model.addAttribute("model", new ClassModel());
            model.addAttribute("classes", classService.getAll());
            model.addAttribute("error", "wystąpił błąd podczas usuwania klasy");
            return "FormSite";
        }
    }
}
