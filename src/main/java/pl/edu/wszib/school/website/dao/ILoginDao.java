package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.User;

public interface ILoginDao {
    //void insertLogin(Login login); nie trzeba kaskadowo usuwa i tworzy się podczas tworzenia i usuwania urzytkownika
    void updateLogin(Login login);
    //void removeLogin(Login login);

    Login getByID(int id);
    Login getByUser(User user);
    Login getByLogin(String login);
}
