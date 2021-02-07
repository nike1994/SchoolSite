package pl.edu.wszib.school.website.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.school.website.services.IUserServices;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/user")
public class UserRESTController {

    @Autowired
    IUserServices userServices;

    @RequestMapping(value = "/getUser", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public String getUser(@RequestBody LinkedHashMap JSON) {
        return userServices.getUserJSON(Integer.parseInt((String) JSON.get("id")));
    }
}
