package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.View.ClassModel;
import pl.edu.wszib.school.website.services.IClassService;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    IClassService classService;


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createClassSite(Model model){
        // TODO: 06.02.2021 testowanie danych wejściowych
        model.addAttribute("formFragment","fragments/classForm.html :: createForm(model=${model})");
        model.addAttribute("model", new ClassModel());
        return "FormSite";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createClass(@ModelAttribute ClassModel model){
        classService.createClass(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateClassSite(Model model){
        model.addAttribute("formFragment","fragments/classForm.html :: updateForm(classes=${classes},model=${model})");
        model.addAttribute("model", new ClassModel());
        model.addAttribute("classes", classService.getAll());
        return "FormSite";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateClass(@ModelAttribute ClassModel model){
        classService.updateClass(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteClassSite(Model model){
        model.addAttribute("formFragment","fragments/classForm.html :: deleteForm(classes=${classes},model=${model})");
        model.addAttribute("model", new ClassModel());
        model.addAttribute("classes", classService.getAll());
        return "FormSite";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteClass(@ModelAttribute ClassModel model){
        classService.deleteClass(model.getId());
        return "redirect:/Home";
    }
}
