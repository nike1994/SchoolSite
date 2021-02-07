package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.services.IPageServices;


@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    IPageServices pageServices;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPostSite(Model model){
        // TODO: 06.02.2021 testowanie danych wejściowych
        model.addAttribute("formFragment","fragments/pageForm.html :: createForm(sites=${sites},model=${model})");
        model.addAttribute("sites",pageServices.getAllPages());
        model.addAttribute("model", new PageModel());
        return "FormSite";
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createPost(@ModelAttribute PageModel model){
        pageServices.createPage(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePostSite(Model model){
        model.addAttribute("formFragment","fragments/pageForm.html :: updateForm(sites=${sites},model=${model})");
        model.addAttribute("sites",pageServices.getAllPages());
        model.addAttribute("model", new PageModel());
        return "FormSite";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updatePost(@ModelAttribute PageModel model){
        pageServices.updatePage(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePostSite(Model model){
        model.addAttribute("formFragment","fragments/pageForm.html :: deleteForm(sites=${sites},model=${model})");
        model.addAttribute("sites",pageServices.getAllPages());
        model.addAttribute("model", new PageModel());
        return "FormSite";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deletePost(@ModelAttribute PageModel model){
        pageServices.deletePageByModel(model);
        return "redirect:/Home";
    }


}
