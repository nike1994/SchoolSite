package pl.edu.wszib.school.website.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.edu.wszib.school.website.dao.*;
import pl.edu.wszib.school.website.model.*;
import pl.edu.wszib.school.website.services.IClassService;
import pl.edu.wszib.school.website.services.IPageServices;
import pl.edu.wszib.school.website.services.IPostServices;
import pl.edu.wszib.school.website.services.IUserServices;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IWebsiteInformationsDao dao;

    @Autowired
    IUserDao userDao;

    @Autowired
    IPageServices pageServices;

    @Autowired
    IPostServices postServices;

    @Autowired
    IClassService classService;

    @Autowired
    IParentDao parentDao;

    @Autowired
    IClassDao classDao;

    @Autowired
    IPupilDao pupilDao;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        WebsiteInformations info = new WebsiteInformations(
                "Sp Laskowa",
                "./img/logo.svg",
                new ArrayList<String>(Arrays.asList("szkola@sp.pl")),
                new ArrayList<String>(Arrays.asList("234-432-233","429-432-134")),
                new ArrayList<String>(Arrays.asList("Home,Dziennik")));

        dao.createInformations(info);

        Page parent = new Page();
        parent.setTitle("aktualności");

        Page child = new Page();
        child.setTitle("Wiosna2021");
        child.setParent(parent);

        Page child2 = new Page();
        child2.setTitle("Wiosna2020");
        child2.setParent(parent);

        pageServices.createPage(parent);
        int id_page = pageServices.createPage(child);
        pageServices.createPage(child2);

        Login login = new Login();
        login.setLogin("admin");
        login.setPassword("admin");
        User user = new User();
        user.setRole(User.Role.ADMIN);
        user.setName("Wiki");
        user.setSurName("Kozak");
        user.setLogin(login);
        login.setUser(user);

        int id = userDao.insertUser(user);

        Post post = new Post();
        post.setAuthor(userDao.getUserByID(id));
        post.setDate("12/12/12 23:00");
        post.setPage(pageServices.getByID(id_page));
        post.setTitle("tytul1");
        post.setContent("tekst");

        postServices.insertPost(post);

        SchoolClass clas = new SchoolClass();
        clas.setName("A");
        clas.setYear(2020);

        classService.createClass(clas);


        User user1 = new User();
        user1.setName("Anna");
        user1.setSurName("Kowalska");
        user1.setRole(User.Role.PARENT);

        Login login1 = new Login();
        login1.setLogin("AKowalska");
        login1.setPassword("AKowalska");
        login1.setUser(user1);

        user1.setLogin(login1);

        Parent parent1 = new Parent();
        parent1.setUser(user1);

        parentDao.insertParent(parent1);

        User user2 = new User();
        user2.setName("Tomasz");
        user2.setSurName("Kowalski");
        user2.setRole(User.Role.PUPIL);

        Login login2 = new Login();
        login2.setLogin("TKowalski");
        login2.setPassword("123");
        login2.setUser(user2);

        user2.setLogin(login2);

        SchoolClass clas2 = classDao.getClassByID(1);

        Parent parent2 = parentDao.getByUserId(2);


        Pupil pupil = new Pupil();
        pupil.setsClass(clas2);
        pupil.setUser(user2);
        pupil.setParent(parent2);

        pupilDao.insertPupil(pupil);

    }
}