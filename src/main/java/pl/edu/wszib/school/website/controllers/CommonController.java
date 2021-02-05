package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@ControllerAdvice
public class CommonController {

    @Autowired
    IWebsiteInformationsService websiteInformationsService;

    @Resource
    SessionObject sessionObject;

    @ModelAttribute("webInfo")
    public AllWebsiteInformationsModel populateUser() {
        AllWebsiteInformationsModel info = websiteInformationsService.getinformations();
        return info;
    }

    @ModelAttribute("isLogged")
    public boolean isLogged(){
        return  sessionObject.isLogged();
    }

    @ModelAttribute("navLogged")
    public List<Object> navAdmin(){

        if (sessionObject.isLogged()){
            User.Role role = sessionObject.getLoggedUser().getRole();
            List<Object> list = websiteInformationsService.getButtonNavLogin(role);
            System.out.println(list);
            return list;
        }else{
            return null;
        }
    }


}
