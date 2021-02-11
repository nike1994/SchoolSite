package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.services.IPageServices;
import pl.edu.wszib.school.website.services.IPostServices;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;


@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    IPageServices pageServices;
    @Autowired
    IPostServices postServices;

    @Resource
    SessionObject sessionObject;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String createPageSite(@PathVariable int id, Model model){
        Page page = pageServices.getByID(id);
        if(page!=null){
            System.out.println(postServices.getPagePosts(page).size());
            model.addAttribute("posts",postServices.getPagePosts(page));
            return "postSite";
        }else{
            return "redirect:/Home";
        }

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPageSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/pageForm.html :: createForm(sites=${sites},model=${model})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PageModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";

    }


    @RequestMapping(value="/create",method = RequestMethod.POST)
    public String createPage(@ModelAttribute PageModel pageModel, Model model){
        try {
            if (pageServices.createPage(pageModel)){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/pageForm.html :: createForm(sites=${sites},model=${model})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PageModel());
                model.addAttribute("error", "istnieje już strona o takim tytule");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/pageForm.html :: createForm(sites=${sites},model=${model})");
            model.addAttribute("sites",pageServices.getAllPages());
            model.addAttribute("model", new PageModel());
            model.addAttribute("error", "wystąpił błąd podczas tworzenia strony");
            return "FormSite";
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updatePostSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/pageForm.html :: updateForm(sites=${sites},model=${model})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PageModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updatePage(@ModelAttribute PageModel modepageModel, Model model){
        try {
            if (pageServices.updatePage(modepageModel)){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/pageForm.html :: updateForm(sites=${sites},model=${model})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PageModel());
                model.addAttribute("error", "wprowadzono błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/pageForm.html :: updateForm(sites=${sites},model=${model})");
            model.addAttribute("sites",pageServices.getAllPages());
            model.addAttribute("model", new PageModel());
            model.addAttribute("error", "wystąpił błąd podczas aktualizowania");
            return "FormSite";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePageSite(Model model){
        if (sessionObject.isLogged()){
            if (sessionObject.getLoggedUser().getRole().equals(User.Role.ADMIN)){
                model.addAttribute("formFragment","fragments/pageForm.html :: deleteForm(sites=${sites},model=${model})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PageModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deletePage(@ModelAttribute PageModel modelPage, Model model){
        try {
            if (pageServices.deletePageByModel(modelPage)){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/pageForm.html :: updateForm(sites=${sites},model=${model})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PageModel());
                model.addAttribute("error", "wprowadzono błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/pageForm.html :: updateForm(sites=${sites},model=${model})");
            model.addAttribute("sites",pageServices.getAllPages());
            model.addAttribute("model", new PageModel());
            model.addAttribute("error", "wystąpił błąd podczas aktualizowania");
            return "FormSite";
        }
    }


}
