package pl.edu.wszib.school.website.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.school.website.services.Impl.SchoolRegisterService;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class SchoolRegisterRESTControler {

    @Autowired
    SchoolRegisterService registerService;

    @Resource
    SessionObject sessionObject;


    @RequestMapping(value = "/saveGrade", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public String save(@RequestBody List<Map<String,String>> JSON) {
         if(registerService.createGrades(JSON)){
             return "ok";
         }else{
             return "błąd zapisu";
         }
    }

    @RequestMapping(value = "/getTableByID", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody
    public String getTable(@RequestBody LinkedHashMap JSON) {
        return registerService.getTableJSONByID(Integer.parseInt((String) JSON.get("id")), sessionObject.getLoggedUser().getRole());
    }
}
