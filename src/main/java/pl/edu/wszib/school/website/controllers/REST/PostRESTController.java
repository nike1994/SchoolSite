package pl.edu.wszib.school.website.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.services.IPageServices;
import pl.edu.wszib.school.website.services.IPostServices;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/post")
public class PostRESTController {
    @Autowired
    IPostServices postServices;

    @Autowired
    IPageServices pageServices;

    @Resource
    SessionObject sessionObject;


    @RequestMapping(value = "/getPostsByPage", method = RequestMethod.POST,
                    consumes = "application/json")
    @ResponseBody public String createPostSite(@RequestBody LinkedHashMap JSON){
        if(JSON.containsKey("id")){
            Page page = pageServices.getByID(Integer.parseInt((String) JSON.get("id")));
            System.out.println();
            System.out.println(page.getTitle());
            System.out.println();
            if (page != null){
                    String response = postServices.getJSONPagePosts(page, sessionObject.getLoggedUser());
                    if(response == null){
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                    }else{
                        return response;
                    }
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/createComment", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseBody public String createComment(@RequestBody LinkedHashMap JSON){
        if(postServices.createComment(JSON,sessionObject.getLoggedUser()) == 0){
            return "error";
        }else{
            return "ok";
        }

    }

}
