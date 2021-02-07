package pl.edu.wszib.school.website.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.school.website.dao.IPupilDao;
import pl.edu.wszib.school.website.services.IPupilService;
import pl.edu.wszib.school.website.services.IUserServices;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/pupil")
public class PupilRESTController {
    @Autowired
    IPupilService pupilServices;

    @RequestMapping(value = "/getPupil", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public String getUser(@RequestBody LinkedHashMap JSON) {
        return pupilServices.getPupilJSON(Integer.parseInt((String) JSON.get("id")));
    }
}
