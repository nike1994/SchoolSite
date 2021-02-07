package pl.edu.wszib.school.website.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wszib.school.website.services.IClassService;
import pl.edu.wszib.school.website.services.IPageServices;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/class")
public class ClassRESTController {
    @Autowired
    IClassService classService;

    @RequestMapping(value = "/getClass", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public String getClass(@RequestBody LinkedHashMap JSON){
        if(JSON.containsKey("id")){
            return classService.getClassJSONByID(Integer.parseInt((String) JSON.get("id")));
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    }
}
