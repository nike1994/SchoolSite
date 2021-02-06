package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.View.PostModel;
import pl.edu.wszib.school.website.services.IPageServices;
import pl.edu.wszib.school.website.services.IPostServices;
import pl.edu.wszib.school.website.services.IUserServices;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    IPostServices postServices;

    @Autowired
    IPageServices pageServices;

    @Resource
    SessionObject sessionObject;



    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createPostSite(Model model){
        model.addAttribute("formFragment","fragments/postForm.html :: createForm(sites=${sites},model=${model})");
        model.addAttribute("sites",pageServices.getAllPages());
        model.addAttribute("model", new PostModel());
        return "FormSite";
    }

    @RequestMapping(value="create",method = RequestMethod.POST)
    public String createPost(@ModelAttribute PostModel model){
        model.setAuthor(sessionObject.getLoggedUser());
        model.setDate(LocalDateTime.now());
        postServices.createPost(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updatePostSite(Model model){
        model.addAttribute("formFragment","fragments/postForm.html :: updateForm(model=${model},sites=${sites})");
        model.addAttribute("sites",pageServices.getAllPages());
        model.addAttribute("model", new PostModel());
        return "FormSite";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute PostModel model){
        // TODO: 06.02.2021 sprawdzenie czy model nie jest pusty
        postServices.updatePost(model);
        return "redirect:/Home";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deletePostSite(Model model){
        model.addAttribute("formFragment","fragments/postForm.html :: deleteForm(model=${model},sites=${sites})");
        model.addAttribute("sites",pageServices.getAllPages());
        model.addAttribute("model", new PostModel());
        return "FormSite";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deletePost(@ModelAttribute PostModel model){
        // TODO: 06.02.2021 sprawdzenie czy jest takie id
        postServices.deletePost(postServices.getByID(model.getPost_id()));
        return "redirect:/Home";
    }


}
