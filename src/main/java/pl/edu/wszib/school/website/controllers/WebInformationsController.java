package pl.edu.wszib.school.website.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;

@Controller
public class WebInformationsController {
    @Autowired
    IWebsiteInformationsService websiteInformationsService;

    @RequestMapping(value = "/webPageSettings", method = RequestMethod.GET)
    public String updatePostSite(Model model){
        model.addAttribute("formFragment","fragments/webInfoForm.html :: updateForm(model=${model})");
        //webinfo jest dodawany do każdej strony przez CommonController
        model.addAttribute("model", websiteInformationsService.getinformations());
        return "FormSite";
    }

    @RequestMapping(value="/webPageSettings",method = RequestMethod.POST)
    public String updatePost(@RequestParam("logoFile") MultipartFile file, @ModelAttribute AllWebsiteInformationsModel model){
        websiteInformationsService.updateInformations(model,file);
        return "redirect:/Home";
    }

}
