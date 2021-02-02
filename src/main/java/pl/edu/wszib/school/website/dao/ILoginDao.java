package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.User;

public interface ILoginDao {
    void insertLogin(Login login);
    void updateLogin(Login login);
    void removeLogin(Login login);

    Login getByID(int id);
    Login getByUser(User user);
    Login getByLogin(String login);
}
