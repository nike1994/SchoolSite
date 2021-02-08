package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.WebsiteInformations;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;

import java.io.IOException;
import java.util.*;


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
    public void updateInformations(AllWebsiteInformationsModel model, MultipartFile file) {
        WebsiteInformations websiteInformations = infoDao.getInformations();

        websiteInformations.setTelephons(model.getTelephons());

        websiteInformations.setEmails(model.getEmails());

        StringBuilder sb = new StringBuilder();
        if(!file.isEmpty()){
            sb.append("data:image/png;base64,");
            try {
                sb.append(Base64.getEncoder().encodeToString(file.getBytes()));
                websiteInformations.setSiteLogo(sb.toString());
                allWbInfModel.setSiteLogo(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        websiteInformations.setSiteName(model.getSiteName());

        infoDao.updateInfomations(websiteInformations);

        allWbInfModel.setTelephons(model.getTelephons());
        allWbInfModel.setEmails(model.getEmails());
        allWbInfModel.setSiteName(model.getSiteName());

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


        info.setAllsites(getSites());
        return info;
    }


    @Override
    public void updatePages(){
        this.allWbInfModel.setAllsites(getSites());
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
            listbutton.add(new ButtonObj("Zmień swoje hasło","passwordUpdate"));

        }else if(role.equals(User.Role.TEACHER)){
            listbutton.add(new ButtonObj("Posty","post","post"));
            listbutton.add(new ButtonObj("Zmień swoje hasło","passwordUpdate"));
        }else{
            listbutton.add(new ButtonObj("Zmień swoje hasło","passwordUpdate"));
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


    private Map<String, Object> getSites(){
        WebsiteInformations informationsFromDB = infoDao.getInformations();
        List<String> staticPages = informationsFromDB.getStaticPages();

        List<Page> pages = pageDao.getAllPages();

        //home zawsze na 1 miejscu
        Comparator<String> customComparator = new Comparator<String>() {
            @Override public int compare(String s1, String s2) {
                if(s1.equals(staticPages.get(0))){
                    return -1;
                }else if(s2.equals(staticPages.get(0))) {
                    return 1;
                }

                return s1.compareTo(s2);
            }
        };

        Map<String,Object> sites= new TreeMap<>(customComparator);

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



        for (String page:staticPages) {
            sites.put(page,page);
        }


        return sites;
    }

}
