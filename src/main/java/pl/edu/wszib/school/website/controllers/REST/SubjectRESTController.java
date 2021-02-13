package pl.edu.wszib.school.website.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.school.website.services.ISubjectServices;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/subject")
public class SubjectRESTController {
    @Autowired
    ISubjectServices subjectServices;

    @RequestMapping(value = "/getSubject", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public String getSubject(@RequestBody LinkedHashMap JSON) {
        return subjectServices.getSubjectJSONByID(Integer.parseInt((String) JSON.get("id")));
    }
}
