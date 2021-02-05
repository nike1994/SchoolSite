package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.dao.Impl.WebsiteInformationsDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.WebsiteInformations;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WebsiteInformationsService implements IWebsiteInformationsService {

    @Autowired
    IWebsiteInformationsDao infoDao;
    @Autowired
    IPageDao pageDao;

    private AllWebsiteInformationsModel allWbInfModel = null;

    @Override
    public void updateInformations(WebsiteInformations informations) {
        infoDao.updateInfomations(informations);
    }

    @Override
    public AllWebsiteInformationsModel getinformations() {
        if(allWbInfModel == null){
            this.allWbInfModel = createModel();
        }

        return this.allWbInfModel;
    }

    private AllWebsiteInformationsModel createModel(){
        WebsiteInformations informationsFromDB = infoDao.getInformations();

        AllWebsiteInformationsModel info = new AllWebsiteInformationsModel();
        info.setSiteName(informationsFromDB.getSiteName());
        info.setSiteLogo(informationsFromDB.getSiteLogo());
        info.setEmails(informationsFromDB.getEmails());
        info.setTelephons(informationsFromDB.getTelephons());

        List<Page> pages = pageDao.getAllPages();

        Map<String,Object> sites= new HashMap<String,Object>();

        for (Page page: pages) {
            if(page.getParent()==null){
                if(page.getChildren().isEmpty()){
                    sites.put(page.getTitle(),"/page/"+page.getId());
                }else{
                    HashMap<String, Object> subPages = new HashMap<>();

                    for (Page subPage: page.getChildren()) {
                        subPages.put(subPage.getTitle(),"/page/"+subPage.getId());
                    }

                    sites.put(page.getTitle(),subPages);
                }
            }
        }

        for (String page:informationsFromDB.getStaticPages()) {
            sites.put(page,page);
        }


        info.setAllsites(sites);
        return info;
    }
}
