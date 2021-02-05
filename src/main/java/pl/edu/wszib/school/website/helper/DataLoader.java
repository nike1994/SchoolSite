package pl.edu.wszib.school.website.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.WebsiteInformations;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IWebsiteInformationsDao dao;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        WebsiteInformations info = new WebsiteInformations(
                "Sp Laskowa",
                "./img/logo.svg",
                new ArrayList<String>(Arrays.asList("szkola@sp.pl")),
                new ArrayList<String>(Arrays.asList("234-432-233","429-432-134")),
                new ArrayList<String>(Arrays.asList("Home,Dziennik")));

        dao.createInformations(info);
    }
}