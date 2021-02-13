package pl.edu.wszib.school.website.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wszib.school.website.services.IPageServices;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/page")
public class PageRESTController {
    @Autowired
    IPageServices pageServices;

    @RequestMapping(value = "/getPage", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public String getPage(@RequestBody LinkedHashMap JSON){
        if(JSON.containsKey("id")){
            return pageServices.getPageJSONByID(Integer.parseInt((String) JSON.get("id")));
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    }
}
