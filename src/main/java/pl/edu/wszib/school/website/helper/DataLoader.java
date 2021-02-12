package pl.edu.wszib.school.website.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.edu.wszib.school.website.dao.IUserDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.WebsiteInformations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IWebsiteInformationsDao dao;

    @Autowired
    private IUserDao userDao;

    @Override
    public void run(ApplicationArguments args) {
        WebsiteInformations info = dao.getInformations();
        if (info == null){
             info = new WebsiteInformations(
                    "Sp Laskowa",
                    "/img/logo.svg",
                    new ArrayList<>(Arrays.asList("szkola@sp.pl")),
                    new ArrayList<>(Arrays.asList("234-432-233","429-432-134")),
                    new ArrayList<>(Arrays.asList("Home,Dziennik")));

            dao.createInformations(info);
        }

        List<User> admins = userDao.getUserByRole(User.Role.ADMIN);
        if (admins.isEmpty()){
            User admin = new User();
            admin.setRole(User.Role.ADMIN);
            admin.setName("Admin");
            admin.setSurName("");

            Login login = new Login();
            login.setLogin("admin");
            login.setPassword("admin");
            login.setUser(admin);

            admin.setLogin(login);

            userDao.insertUser(admin);
        }
    }
}