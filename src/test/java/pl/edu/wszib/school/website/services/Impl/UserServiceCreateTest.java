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
import pl.edu.wszib.school.website.model.*;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.model.View.PupilModel;
import pl.edu.wszib.school.website.model.View.TeacherModel;
import pl.edu.wszib.school.website.services.IUserServices;

import java.util.HashSet;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class UserServiceCreateTest {

    private static Logger log = Logger.getLogger(UserServiceCreateTest.class.getName());

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

    @Autowired
    IUserServices userService;


    @Test
    public void createPupilTest(){

        PupilModel model = new PupilModel();
        model.setName("Tomasz");
        model.setSurname("Komendera");
        model.setLogin("TKomendera");
        model.setPass("1234");
        model.setParent_id(1);
        model.setSchoolClass_id(1);

        Mockito.when(this.loginDao.getByLogin("TKomendera")).thenReturn(null);
        Mockito.when(this.classDao.getClassByID(1)).thenReturn(returnClass());
        Mockito.when(this.parentDao.getByUserId(1)).thenReturn(returnParent());
        Mockito.when(this.pupilDao.insertPupil(any(Pupil.class))).thenReturn(1);

        int id = userService.createPupil(model);
        log.info(""+id);

        Assertions.assertEquals(1, id);
    }

    @Test
    public void createEmptyPupilTest(){

        PupilModel model = new PupilModel();
        model.setName("");
        model.setSurname("Komendera");
        model.setLogin("TKomendera");
        model.setPass("1234");
        model.setParent_id(1);
        model.setSchoolClass_id(1);

        Mockito.when(this.loginDao.getByLogin("TKomendera")).thenReturn(null);
        Mockito.when(this.classDao.getClassByID(1)).thenReturn(returnClass());
        Mockito.when(this.parentDao.getByUserId(1)).thenReturn(returnParent());
        Mockito.when(this.pupilDao.insertPupil(any(Pupil.class))).thenReturn(1);

        int id = userService.createPupil(model);
        log.info(""+id);

        Assertions.assertEquals(0, id);
    }

    @Test
    public void createPupilWhenLoginExistTest(){

        PupilModel model = new PupilModel();
        model.setName("Tomasz");
        model.setSurname("Komendera");
        model.setLogin("TKomendera");
        model.setPass("1234");
        model.setParent_id(1);
        model.setSchoolClass_id(1);

        Mockito.when(this.loginDao.getByLogin("TKomendera")).thenReturn(new Login());
        Mockito.when(this.classDao.getClassByID(1)).thenReturn(returnClass());
        Mockito.when(this.parentDao.getByUserId(1)).thenReturn(returnParent());
        Mockito.when(this.pupilDao.insertPupil(any(Pupil.class))).thenReturn(1);

        int id = userService.createPupil(model);

        Assertions.assertEquals(0, id);

    }

    @Test
    public void createTeacher(){
        TeacherModel model = new TeacherModel();
        model.setLogin("BNowak");
        model.setName("Beata");
        model.setSurname("Nowak");
        model.setPass("1234");

        Mockito.when(loginDao.getByLogin(any(String.class))).thenReturn(null);
        Mockito.when(this.userDao.insertUser(any(User.class))).thenReturn(1);

        int id = userService.createTeacher(model);

        Assertions.assertEquals(1,id);
    }

    @Test
    public void createEmptyTeacher(){
        TeacherModel model = new TeacherModel();
        model.setLogin("");
        model.setName("Beata");
        model.setSurname("Nowak");
        model.setPass("1234");

        Mockito.when(this.loginDao.getByLogin("BNowak")).thenReturn(null);
        Mockito.when(this.userDao.insertUser(any(User.class))).thenReturn(1);

        int id = userService.createTeacher(model);

        Assertions.assertEquals(0,id);
    }

    @Test
    public void createTeacherWhenLoginExist(){
        TeacherModel model = new TeacherModel();
        model.setLogin("BNowak");
        model.setName("Beata");
        model.setSurname("Nowak");
        model.setPass("1234");

        Mockito.when(this.loginDao.getByLogin("BNowak")).thenReturn(new Login());
        Mockito.when(this.userDao.insertUser(any(User.class))).thenReturn(1);

        int id = userService.createTeacher(model);

        Assertions.assertEquals(0,id);
    }


    @Test
    public void createParentTest(){
        ParentModel model = new ParentModel();
        model.setName("Anna");
        model.setSurname("Nowak");
        model.setPass("1234");
        model.setLogin("ANowak");

        Mockito.when(this.loginDao.getByLogin("ANowak")).thenReturn(null);
        Mockito.when(this.parentDao.insertParent(any(Parent.class))).thenReturn(1);

        int id = userService.createParent(model);

        Assertions.assertEquals(1,id);
    }

    @Test
    public void createEmptyParentTest(){
        ParentModel model = new ParentModel();
        model.setName("");
        model.setSurname("");
        model.setPass("1234");
        model.setLogin("ANowak");

        Mockito.when(this.loginDao.getByLogin("ANowak")).thenReturn(null);
        Mockito.when(this.parentDao.insertParent(any(Parent.class))).thenReturn(1);

        int id = userService.createParent(model);

        Assertions.assertEquals(0,id);
    }

    @Test
    public void createParentWhenLoginExistTest(){
        ParentModel model = new ParentModel();
        model.setName("Anna");
        model.setSurname("Nowak");
        model.setPass("1234");
        model.setLogin("ANowak");

        Mockito.when(this.loginDao.getByLogin("ANowak")).thenReturn(new Login());
        Mockito.when(this.parentDao.insertParent(any(Parent.class))).thenReturn(1);

        int id = userService.createParent(model);

        Assertions.assertEquals(0,id);
    }


    private SchoolClass returnClass(){
        SchoolClass clas = new SchoolClass();
        clas.setId(1);
        clas.setYear(2020);
        clas.setName("IA");
        clas.setPupils(new HashSet<>());
        clas.setSubjects(new HashSet<>());

        return clas;
    }

    private Parent returnParent(){
        Parent parent = new Parent();
        User user = new User();
        user.setId(1);
        parent.setUser(user);
        parent.setId(1);
        parent.setChildrens(new HashSet<>());

        return parent;
    }

}
