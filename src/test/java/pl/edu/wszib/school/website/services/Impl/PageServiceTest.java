package pl.edu.wszib.school.website.services.Impl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.school.website.configuration.AppTestConfiguration;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.services.IPageServices;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class PageServiceTest {

    @MockBean
    IPageDao pageDao;

    @MockBean
    IWebsiteInformationsDao websiteInformationsDao;

    @Autowired
    IPageServices pageServices;

    @Test
    public void createPageTest(){
        PageModel model = new PageModel();
        model.setTitle("strona1");
        model.setPage_id(1);
        model.setParent_id(2);

        Mockito.when(pageDao.getByTitle("strona1")).thenReturn(null);
        Mockito.when(pageDao.getByID(2)).thenReturn(new Page());

        boolean result = pageServices.createPage(model);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void createPageWhenTitleIsEmptyTest(){
        PageModel model = new PageModel();
        model.setTitle("");
        model.setPage_id(1);
        model.setParent_id(2);

        Mockito.when(pageDao.getByTitle("strona1")).thenReturn(new Page());
        Mockito.when(pageDao.getByID(2)).thenReturn(new Page());

        boolean result = pageServices.createPage(model);

        Assertions.assertEquals(false, result);
    }

    @Test
    public void createPageWhenPageExistTest(){
        PageModel model = new PageModel();
        model.setTitle("strona1");
        model.setPage_id(1);
        model.setParent_id(2);

        Mockito.when(pageDao.getByTitle("strona1")).thenReturn(new Page());
        Mockito.when(pageDao.getByID(2)).thenReturn(new Page());

        boolean result = pageServices.createPage(model);

        Assertions.assertEquals(false, result);
    }

    @Test
    public void updatePageTest(){
        PageModel model = new PageModel();
        model.setTitle("Strona1");
        model.setPage_id(1);
        model.setParent_id(2);

        Mockito.when(pageDao.getByID(1)).thenReturn(new Page());
        Mockito.when(pageDao.getByTitle("strona1")).thenReturn(null);
        Mockito.when(pageDao.getByID(2)).thenReturn(new Page());

        boolean result = pageServices.updatePage(model);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void updatePageWhenTitleIsEmptyTest(){
        PageModel model = new PageModel();
        model.setTitle("");
        model.setPage_id(1);
        model.setParent_id(2);

        Mockito.when(pageDao.getByID(1)).thenReturn(new Page());
        Mockito.when(pageDao.getByTitle("strona1")).thenReturn(null);
        Mockito.when(pageDao.getByID(2)).thenReturn(new Page());

        boolean result = pageServices.updatePage(model);

        Assertions.assertEquals(false, result);
    }

    @Test
    public void updatePageWhenPageNotExsistTest(){
        PageModel model = new PageModel();
        model.setTitle("Strina1");
        model.setPage_id(1);
        model.setParent_id(2);

        Mockito.when(pageDao.getByID(1)).thenReturn(null);
        Mockito.when(pageDao.getByTitle("strona1")).thenReturn(null);
        Mockito.when(pageDao.getByID(2)).thenReturn(new Page());

        boolean result = pageServices.updatePage(model);

        Assertions.assertEquals(false, result);
    }

}
