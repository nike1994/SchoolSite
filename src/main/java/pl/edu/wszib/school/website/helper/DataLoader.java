package pl.edu.wszib.school.website.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.edu.wszib.school.website.dao.IUserDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.*;
import pl.edu.wszib.school.website.services.IPageServices;
import pl.edu.wszib.school.website.services.IPostServices;

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
    }
}