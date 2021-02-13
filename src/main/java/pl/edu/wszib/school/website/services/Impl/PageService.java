package pl.edu.wszib.school.website.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.services.IPageServices;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;

import java.util.List;

@Service
public class PageService implements IPageServices {

    @Autowired
    IPageDao pageDao;

    @Autowired
    IWebsiteInformationsService websiteInformationsService;

    @Override
    public int createPage(Page page) {
        return pageDao.insertPage(page);
    }

    @Override
    public boolean createPage(PageModel model) {
        if(model.getTitle() == "") return false;

        Page check =pageDao.getByTitle(model.getTitle());
        if (check != null) return false;

        Page parent= null;
        if(model.getParent_id()!=0){
            parent = pageDao.getByID(model.getParent_id());
        }

        Page page = new Page();
        page.setTitle(model.getTitle());
        page.setParent(parent);

        pageDao.insertPage(page);

        websiteInformationsService.updatePages();
        return true;
    }

    @Override
    public void updatePage(Page page) {
        pageDao.updatePage(page);
    }

    @Override
    public boolean updatePage(PageModel model) {

        if(model.getTitle() == "") return false;

        Page page = pageDao.getByID(model.getPage_id());
        if (page == null) return false;

        Page check = pageDao.getByTitle(model.getTitle());
        if (check != null){ //czy istnieje strona o takim tytule
            if (check.getId() != model.getPage_id()) // czy ta strona jest tą którą edytujemy
                return false;
        }

        page.setParent(pageDao.getByID(model.getParent_id()));
        page.setTitle(model.getTitle());

        pageDao.updatePage(page);

        websiteInformationsService.updatePages();
        return true;
    }

    @Override
    public void deletePage(Page page) {
        pageDao.removePage(page);
    }

    @Override
    public boolean deletePageByModel(PageModel model) {
        if(model.getPage_id() != 0){
            Page page = pageDao.getByID(model.getPage_id());
            if(page != null){
                pageDao.removePage(page);

                websiteInformationsService.updatePages();
                return true;
            }
        }
        return false;
    }

    @Override
    public Page getByID(int id) {
        return pageDao.getByID(id);
    }

    @Override
    public String getPageJSONByID(int id) {
        Page page = pageDao.getByID(id);
        PageModel model = new PageModel();
        model.setPage_id(page.getId());
        model.setTitle(page.getTitle());
        Page parent= page.getParent();
        if(parent != null){
            model.setParent_id(parent.getId());
        }
        if (page.getChildren().isEmpty()){
            model.setHasChild(false);
        }else{
            model.setHasChild(true);
        }

        ObjectMapper mapper = new ObjectMapper();
        String response = null;
        try {
            response = mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public List<Page> getAllPages() {
        return pageDao.getAllPages();
    }
}
