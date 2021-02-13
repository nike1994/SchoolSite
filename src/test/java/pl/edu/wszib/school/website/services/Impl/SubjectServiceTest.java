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
import pl.edu.wszib.school.website.dao.*;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.SubjectModel;
import pl.edu.wszib.school.website.services.ISubjectServices;
import pl.edu.wszib.school.website.services.IUserServices;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class SubjectServiceTest {
    @MockBean
    IUserDao userDao;

    @MockBean
    ILoginDao loginDao;

    @MockBean
    IPupilDao pupilDao;

    @MockBean
    IParentDao parentDao;

    @MockBean
    IClassDao classDao;

    @MockBean
    IWebsiteInformationsDao websiteInformationsDao;

    @MockBean
    ISubjectDao subjectDao;

    @Autowired
    ISubjectServices subjectServices;

    @Test
    public void createSubjectTest(){
        SubjectModel model = new SubjectModel();
        model.setTeacher_id(1);
        model.setClass_id(1);
        model.setName("Przyroda");
        model.setId(1);

        Mockito.when(userDao.getUserByID(1)).thenReturn(new User());
        Mockito.when(classDao.getClassByID(1)).thenReturn(new SchoolClass());

        boolean response = subjectServices.createSubject(model);

        Assertions.assertEquals(true, response);
    }

    @Test
    public void createSubjectEmptyNameTest(){
        SubjectModel model = new SubjectModel();
        model.setTeacher_id(1);
        model.setClass_id(1);
        model.setName("");
        model.setId(1);

        Mockito.when(userDao.getUserByID(1)).thenReturn(new User());
        Mockito.when(classDao.getClassByID(1)).thenReturn(new SchoolClass());

        boolean response = subjectServices.createSubject(model);

        Assertions.assertEquals(false, response);
    }

    @Test
    public void createSubjectClassNotExistTest(){
        SubjectModel model = new SubjectModel();
        model.setTeacher_id(1);
        model.setClass_id(1);
        model.setName("Przyroda");
        model.setId(1);

        Mockito.when(userDao.getUserByID(1)).thenReturn(new User());
        Mockito.when(classDao.getClassByID(1)).thenReturn(null);

        boolean response = subjectServices.createSubject(model);

        Assertions.assertEquals(false, response);
    }

    @Test
    public void updateSubjectTest(){
        SubjectModel model = new SubjectModel();
        model.setTeacher_id(1);
        model.setClass_id(1);
        model.setName("Przyroda");
        model.setId(1);

        Mockito.when(subjectDao.getSubjectByID(1)).thenReturn(new SchoolSubjects());
        Mockito.when(userDao.getUserByID(1)).thenReturn(new User());
        Mockito.when(classDao.getClassByID(1)).thenReturn(new SchoolClass());

        boolean response = subjectServices.updateSubject(model);

        Assertions.assertEquals(true, response);
    }

    @Test
    public void updateSubjectEmptyNameTest(){
        SubjectModel model = new SubjectModel();
        model.setTeacher_id(1);
        model.setClass_id(1);
        model.setName("");
        model.setId(1);

        Mockito.when(subjectDao.getSubjectByID(1)).thenReturn(new SchoolSubjects());
        Mockito.when(userDao.getUserByID(1)).thenReturn(new User());
        Mockito.when(classDao.getClassByID(1)).thenReturn(new SchoolClass());

        boolean response = subjectServices.updateSubject(model);

        Assertions.assertEquals(false, response);
    }

    @Test
    public void updateSubjectWhenSubjectNotExistTest(){
        SubjectModel model = new SubjectModel();
        model.setTeacher_id(1);
        model.setClass_id(1);
        model.setName("Przyroda");
        model.setId(1);

        Mockito.when(subjectDao.getSubjectByID(1)).thenReturn(null);
        Mockito.when(userDao.getUserByID(1)).thenReturn(new User());
        Mockito.when(classDao.getClassByID(1)).thenReturn(new SchoolClass());

        boolean response = subjectServices.updateSubject(model);

        Assertions.assertEquals(false, response);
    }

    @Test
    public void updateSubjectWhenClassNotExistTest(){
        SubjectModel model = new SubjectModel();
        model.setTeacher_id(1);
        model.setClass_id(1);
        model.setName("Przyroda");
        model.setId(1);

        Mockito.when(subjectDao.getSubjectByID(1)).thenReturn(null);
        Mockito.when(userDao.getUserByID(1)).thenReturn(new User());
        Mockito.when(classDao.getClassByID(1)).thenReturn(null);

        boolean response = subjectServices.updateSubject(model);

        Assertions.assertEquals(false, response);
    }

}
