package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.View.ButtonModel;
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

        if (!model.getTelephons().isEmpty()) {
            allWbInfModel.setTelephons(model.getTelephons());
            websiteInformations.setTelephons(model.getTelephons());
        }

        if (!model.getEmails().isEmpty()){
            allWbInfModel.setEmails(model.getEmails());
            websiteInformations.setEmails(model.getEmails());
        }

        if(!file.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");
            try {
                sb.append(Base64.getEncoder().encodeToString(file.getBytes()));
                websiteInformations.setSiteLogo(sb.toString());
                allWbInfModel.setSiteLogo(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        if (model.getSiteName() != ""){
            allWbInfModel.setSiteName(model.getSiteName());
            websiteInformations.setSiteName(model.getSiteName());
        }

        infoDao.updateInfomations(websiteInformations);

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
        if (this.allWbInfModel != null){
            this.allWbInfModel.setAllsites(getSites());
        }
    }

    @Override
    public List<Object> getButtonNavLogin(User.Role role) {
        List<Object> listbutton = new ArrayList<>();

        if(role.equals(User.Role.ADMIN)){
            listbutton.add(new ButtonModel("Strona", "stronę", "page"));
            listbutton.add(new ButtonModel("Posty", "post", "post"));
            listbutton.add(new ButtonModel("Klasa", "klasę", "class"));
            listbutton.add(new ButtonModel("Rodzic", "rodzica", "parent"));
            listbutton.add(new ButtonModel("Uczeń", "ucznia", "pupil"));
            listbutton.add(new ButtonModel("Nauczyciel", "nauczyciela", "teacher"));
            listbutton.add(new ButtonModel("Przedmiot", "przedmiot", "subject"));
            listbutton.add(new ButtonModel("Ustawienia strony", "webPageSettings"));
            listbutton.add(new ButtonModel("Zmień swoje hasło", "passwordUpdate"));

        }else if(role.equals(User.Role.TEACHER)){
            listbutton.add(new ButtonModel("Posty", "post", "post"));
            listbutton.add(new ButtonModel("Zmień swoje hasło", "passwordUpdate"));
        }else{
            listbutton.add(new ButtonModel("Zmień swoje hasło", "passwordUpdate"));
        }

        return listbutton;
    }


    private Map<String, Object> getSites(){
        WebsiteInformations informationsFromDB = infoDao.getInformations();
        List<String> staticPages = informationsFromDB.getStaticPages();

        List<Page> pages = pageDao.getAllPages();

        //home zawsze na 1 miejscu
        Comparator<String> customComparator = (s1, s2) -> {
            if(s1.equals(staticPages.get(0))){
                return -1;
            }else if(s2.equals(staticPages.get(0))) {
                return 1;
            }

            return s1.compareTo(s2);
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
