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
import pl.edu.wszib.school.website.dao.IClassDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.View.ClassModel;
import pl.edu.wszib.school.website.services.IClassService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class ClassServiceTest {

    @MockBean
    IClassDao classDao;

    @Autowired
    IClassService classService;

    @MockBean
    IWebsiteInformationsDao websiteInformationsDao;


    @Test
    public void createClassTest(){
        ClassModel model = new ClassModel();
        model.setYear(2020);
        model.setName("IA");
        model.setId(1);

        Mockito.when(classDao.getClassByNameAndYear("IA", 2020)).thenReturn(null);

        boolean result = classService.createClass(model);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void createClassEmptyNameTest(){
        ClassModel model = new ClassModel();
        model.setYear(2020);
        model.setName("");
        model.setId(1);

        Mockito.when(classDao.getClassByNameAndYear("IA", 2020)).thenReturn(null);

        boolean result = classService.createClass(model);
        Assertions.assertEquals(false, result);
    }
    @Test
    public void createClassWhenClassExistTest(){
        ClassModel model = new ClassModel();
        model.setYear(2020);
        model.setName("IA");
        model.setId(1);

        Mockito.when(classDao.getClassByNameAndYear("IA", 2020)).thenReturn(new SchoolClass());

        boolean result = classService.createClass(model);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void updateClassTest(){
        ClassModel model = new ClassModel();
        model.setYear(2020);
        model.setName("IA");
        model.setId(1);

        Mockito.when(classDao.getClassByID(1)).thenReturn(new SchoolClass());

        boolean result = classService.updateClass(model);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void updateClassEmptyNameTest(){
        ClassModel model = new ClassModel();
        model.setYear(2020);
        model.setName("");
        model.setId(1);

        Mockito.when(classDao.getClassByID(1)).thenReturn(new SchoolClass());

        boolean result = classService.updateClass(model);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void updateClassNotExistTest(){
        ClassModel model = new ClassModel();
        model.setYear(2020);
        model.setName("IA");
        model.setId(1);

        Mockito.when(classDao.getClassByID(1)).thenReturn(null);

        boolean result = classService.updateClass(model);
        Assertions.assertEquals(false, result);
    }
}
