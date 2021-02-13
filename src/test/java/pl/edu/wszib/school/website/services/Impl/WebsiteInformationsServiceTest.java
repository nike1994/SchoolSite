package pl.edu.wszib.school.website.services.Impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.school.website.configuration.AppTestConfiguration;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.WebsiteInformations;
import pl.edu.wszib.school.website.services.IWebsiteInformationsService;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.logging.Logger;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class WebsiteInformationsServiceTest {

    @MockBean
    IWebsiteInformationsDao websiteInformationsDao;


    @Autowired
    IWebsiteInformationsService websiteInformationsService;

    @Test
    public void updateInformationsTest(){
        Mockito.when(this.websiteInformationsDao.getInformations()).thenReturn(returnInformations());

        AllWebsiteInformationsModel informationsModel = new AllWebsiteInformationsModel();
        informationsModel.setSiteName("nowa nazwa strony");
        informationsModel.setSiteLogo("nowe logo");
        informationsModel.setEmails(Arrays.asList("nowy mail"));
        informationsModel.setTelephons( new ArrayList<>(Arrays.asList("234-432-230","429-432-130")));

      //  Mockito.when(this.websiteInformationsDao.updateInfomations(any(WebsiteInformations.class)))

        AllWebsiteInformationsModel currentWebsiteInformations = this.websiteInformationsService.getinformations();

        AllWebsiteInformationsModel before = new AllWebsiteInformationsModel();
        before.setTelephons(currentWebsiteInformations.getTelephons());
        before.setAllsites(currentWebsiteInformations.getAllsites());
        before.setEmails(currentWebsiteInformations.getEmails());
        before.setSiteLogo(currentWebsiteInformations.getSiteLogo());
        before.setSiteName(currentWebsiteInformations.getSiteName());


        MultipartFile file = new MockMultipartFile("jakiśPlik.jpg",new byte[]{});//pusty
        this.websiteInformationsService.updateInformations(informationsModel,file);

       // Assertions.assertThat(currentWebsiteInformations).isEqualToComparingFieldByField(before);
        Assertions.assertThat(currentWebsiteInformations).usingRecursiveComparison()
                .isNotEqualTo(before);

    }




    @Test
    public void updateEmptyInformationsTest(){
        Mockito.when(this.websiteInformationsDao.getInformations()).thenReturn(returnInformations());

        AllWebsiteInformationsModel informationsModel = new AllWebsiteInformationsModel();
        informationsModel.setSiteName("");
        informationsModel.setSiteLogo("");
        informationsModel.setEmails(new ArrayList<>());
        informationsModel.setTelephons( new ArrayList<>());

        //  Mockito.when(this.websiteInformationsDao.updateInfomations(any(WebsiteInformations.class)))

        AllWebsiteInformationsModel currentWebsiteInformations = this.websiteInformationsService.getinformations();

        AllWebsiteInformationsModel before = new AllWebsiteInformationsModel();
        before.setTelephons(currentWebsiteInformations.getTelephons());
        before.setAllsites(currentWebsiteInformations.getAllsites());
        before.setEmails(currentWebsiteInformations.getEmails());
        before.setSiteLogo(currentWebsiteInformations.getSiteLogo());
        before.setSiteName(currentWebsiteInformations.getSiteName());


        MultipartFile file = new MockMultipartFile("jakiśPlik.jpg",new byte[]{}); //pusty
        this.websiteInformationsService.updateInformations(informationsModel,file);

        Assertions.assertThat(currentWebsiteInformations).isEqualToComparingFieldByField(before);

    }




    private WebsiteInformations returnInformations(){

        return new WebsiteInformations(
                "Sp Laskowa",
                "/img/logo.svg",
                new ArrayList<>(Arrays.asList("szkola@sp.pl")),
                new ArrayList<>(Arrays.asList("234-432-233","429-432-134")),
                new ArrayList<>(Arrays.asList("Home,Dziennik")));
    }
}
