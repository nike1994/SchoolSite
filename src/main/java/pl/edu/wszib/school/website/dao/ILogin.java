package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.User;

public interface ILogin {
    void insertLogin(Login login);
    void updateLogin(Login login);
    void removeLogin(Login login);

    Login getByID();
    Login getByUser(User user);
    Login getByLogin(String login);
}
