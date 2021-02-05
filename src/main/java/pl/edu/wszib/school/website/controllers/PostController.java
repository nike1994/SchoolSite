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

        // TODO: 05.02.2021 po dodaniu zdjęcia kolumna jest za mała by to pomieścić trzeba zmienić na inny typ 
    }

}
