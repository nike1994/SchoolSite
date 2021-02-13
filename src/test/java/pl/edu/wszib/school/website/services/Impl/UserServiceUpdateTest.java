package pl.edu.wszib.school.website.services.Impl;

import org.junit.Assert;
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
import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.model.View.PupilModel;
import pl.edu.wszib.school.website.model.View.TeacherModel;
import pl.edu.wszib.school.website.services.IUserServices;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class UserServiceUpdateTest {

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
    public void updatePupilTest(){
        PupilModel model = new PupilModel();
        model.setName("Tomasz");
        model.setSurname("Komendera");
        model.setLogin("TKomendera");
        model.setPass("1234");
        model.setParent_id(1);
        model.setSchoolClass_id(1);
        model.setUser_id(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(null);
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updatePupil(model);

        Assertions.assertEquals(true,response);
    }

    @Test
    public void updateEmptyPupilTest(){
        PupilModel model = new PupilModel();
        model.setName("");
        model.setSurname("Komendera");
        model.setLogin("TKomendera");
        model.setPass("1234");
        model.setParent_id(1);
        model.setSchoolClass_id(1);
        model.setUser_id(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(null);
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updatePupil(model);

        Assertions.assertEquals(false,response);
    }

    @Test
    public void updatePupilWhenLoginExistTest(){
        PupilModel model = new PupilModel();
        model.setName("Tomasz");
        model.setSurname("Komendera");
        model.setLogin("TKomendera");
        model.setPass("1234");
        model.setParent_id(1);
        model.setSchoolClass_id(1);
        model.setUser_id(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(returnLogin());
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updatePupil(model);

        Assertions.assertEquals(false,response);
    }

    @Test
    public void updateParentTest(){
        ParentModel model = new ParentModel();
        model.setName("Anna");
        model.setSurname("Nowak");
        model.setPass("1234");
        model.setLogin("ANowak");
        model.setId(1);
        model.setUser_id(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(null);
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updateParent(model);

        Assertions.assertEquals(true,response);

    }


    @Test
    public void updateEmptyParentTest(){
        ParentModel model = new ParentModel();
        model.setName("");
        model.setSurname("Nowak");
        model.setPass("1234");
        model.setLogin("ANowak");
        model.setId(1);
        model.setUser_id(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(null);
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updateParent(model);

        Assertions.assertEquals(false,response);

    }

    @Test
    public void updateParentWhenLoginExistTest(){
        ParentModel model = new ParentModel();
        model.setName("Anna");
        model.setSurname("Nowak");
        model.setPass("1234");
        model.setLogin("ANowak");
        model.setId(1);
        model.setUser_id(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(returnLogin());
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updateParent(model);

        Assertions.assertEquals(false,response);

    }

    @Test
    public void updateTeacherTest(){
        TeacherModel model = new TeacherModel();
        model.setLogin("BNowak");
        model.setName("Beata");
        model.setSurname("Nowak");
        model.setPass("1234");
        model.setUser_id(1);
        model.setId(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(null);
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updateTeacher(model);

        Assertions.assertEquals(true,response);

    }


    @Test
    public void updateEmptyTeacherTest(){
        TeacherModel model = new TeacherModel();
        model.setLogin("");
        model.setName("Beata");
        model.setSurname("Nowak");
        model.setPass("1234");
        model.setUser_id(1);
        model.setId(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(null);
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updateTeacher(model);

        Assertions.assertEquals(false,response);

    }

    @Test
    public void updateTeacherWhenLoginExistTest(){
        TeacherModel model = new TeacherModel();
        model.setLogin("BNowak");
        model.setName("Beata");
        model.setSurname("Nowak");
        model.setPass("1234");
        model.setUser_id(1);
        model.setId(1);

        Mockito.when(this.loginDao.getByLogin(any(String.class))).thenReturn(returnLogin());
        Mockito.when(this.userDao.getUserByID(1)).thenReturn(returnUser());

        boolean response = userService.updateTeacher(model);

        Assertions.assertEquals(false,response);

    }


    private Login returnLogin(){
        User user = new User();
        user.setName("Ala");
        user.setSurName("Kot");
        user.setId(2);
        Login login = new Login();
        login.setPassword("1234");
        login.setLogin("AKot");
        login.setId(2);
        user.setLogin(login);
        login.setUser(user);

        return login;
    }

    private User returnUser(){
        User user = new User();
        user.setName("Ala");
        user.setSurName("Kot");
        user.setId(5);
        Login login = new Login();
        login.setPassword("1234");
        login.setLogin("AKot");
        login.setId(5);
        user.setLogin(login);
        login.setUser(user);

        return user;
    }

}
