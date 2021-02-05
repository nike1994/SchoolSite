package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.dao.Impl.WebsiteInformationsDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.WebsiteInformations;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;

import java.util.ArrayList;
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

    @Override
    public void updatePages(){
        WebsiteInformations informationsFromDB = infoDao.getInformations();

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


        this.allWbInfModel.setAllsites(sites);
    }


    @Override
    public List<Object> getButtonNavLogin(User.Role role) {
        List<Object> listbutton = new ArrayList<>();

        if(role.equals(User.Role.ADMIN)){
            listbutton.add(new ButtonObj("Strona","stronę", "page"));
            listbutton.add(new ButtonObj("Posty","post","post"));
            listbutton.add(new ButtonObj("Klasa","klasę","class"));
            listbutton.add(new ButtonObj("Rodzic","rodzica","parent"));
            listbutton.add(new ButtonObj("Uczeń","ucznia","pupil"));
            listbutton.add(new ButtonObj("Nauczyciel","nauczyciela","teacher"));
            listbutton.add(new ButtonObj("Przedmiot","przedmiot","subject"));
            listbutton.add(new ButtonObj("Ustawienia strony","webPageSettings"));

        }else if(role.equals(User.Role.TEACHER)){
            listbutton.add(new ButtonObj("Posty","post","post"));
        }else{
            listbutton =null;
        }

        return listbutton;
    }


    class  ButtonObj {
        String btnName;
        String subBtnName;
        String href;

        public ButtonObj(String btnName, String subBtnName, String href) {
            this.btnName = btnName;
            this.subBtnName = subBtnName;
            this.href = href;
        }

        public ButtonObj(String btnName, String href) {
            this.btnName = btnName;
            this.href = href;
            this.subBtnName=null;
        }

        public String getBtnName() {
            return btnName;
        }

        public void setBtnName(String btnName) {
            this.btnName = btnName;
        }

        public String getSubBtnName() {
            return subBtnName;
        }

        public void setSubBtnName(String subBtnName) {
            this.subBtnName = subBtnName;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
