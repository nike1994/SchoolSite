package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;

@ControllerAdvice
public class CommonController {

    @Autowired
    IWebsiteInformationsService websiteInformationsService;

    @ModelAttribute("webInfo")
    public AllWebsiteInformationsModel populateUser() {
        AllWebsiteInformationsModel info = websiteInformationsService.getinformations();
        return info;
    }
}
