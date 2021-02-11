package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.User;
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
        if (sessionObject.isLogged()){
            if (!sessionObject.getLoggedUser().getRole().equals(User.Role.PUPIL)){
                model.addAttribute("formFragment","fragments/postForm.html :: createForm(sites=${sites},model=${model})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PostModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";
    }

    @RequestMapping(value="create",method = RequestMethod.POST)
    public String createPost(@ModelAttribute PostModel postModel, Model model){
        try{
            postModel.setAuthor(sessionObject.getLoggedUser());
            postModel.setDate(LocalDateTime.now());

            if (postServices.createPost(postModel)){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/postForm.html :: createForm(sites=${sites},model=${model})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PostModel());
                model.addAttribute("error", "błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/postForm.html :: createForm(sites=${sites},model=${model})");
            model.addAttribute("sites",pageServices.getAllPages());
            model.addAttribute("model", new PostModel());
            model.addAttribute("error", "wystąpił bład podczas tworzenia postu");
            return "FormSite";
        }

    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updatePostSite(Model model){
        if (sessionObject.isLogged()){
            if (!sessionObject.getLoggedUser().getRole().equals(User.Role.PUPIL)){
                model.addAttribute("formFragment","fragments/postForm.html :: updateForm(model=${model},sites=${sites})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PostModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";

    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute PostModel postModel,Model model){
        try{
            postModel.setAuthor(sessionObject.getLoggedUser());
            postModel.setDate(LocalDateTime.now());

            if (postServices.updatePost(postModel)){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/postForm.html :: updateForm(model=${model},sites=${sites})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PostModel());
                model.addAttribute("error", "błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/postForm.html :: updateForm(model=${model},sites=${sites})");
            model.addAttribute("sites",pageServices.getAllPages());
            model.addAttribute("model", new PostModel());
            model.addAttribute("error", "wystąpił bład podczas tworzenia postu");
            return "FormSite";
        }

    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deletePostSite(Model model){
        if (sessionObject.isLogged()){
            if (!sessionObject.getLoggedUser().getRole().equals(User.Role.PUPIL)){
                model.addAttribute("formFragment","fragments/postForm.html :: deleteForm(model=${model},sites=${sites})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PostModel());
                return "FormSite";
            }
        }
        return "redirect:/Home";

    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deletePost(@ModelAttribute PostModel modelPost, Model model){
        try{
            modelPost.setAuthor(sessionObject.getLoggedUser());
            modelPost.setDate(LocalDateTime.now());

            if (postServices.deletePost(modelPost.getPost_id())){
                return "redirect:/Home";
            }else {
                model.addAttribute("formFragment","fragments/postForm.html :: deleteForm(model=${model},sites=${sites})");
                model.addAttribute("sites",pageServices.getAllPages());
                model.addAttribute("model", new PostModel());
                model.addAttribute("error", "błędne dane");
                return "FormSite";
            }
        }catch (Exception e){
            model.addAttribute("formFragment","fragments/postForm.html :: deleteForm(model=${model},sites=${sites})");
            model.addAttribute("sites",pageServices.getAllPages());
            model.addAttribute("model", new PostModel());
            model.addAttribute("error", "wystąpił bład podczas tworzenia postu");
            return "FormSite";
        }
    }


}
