package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Parent;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.User;

import java.util.List;

public interface IUser {
    void insertUser(User user);
    void removeUser(int id);
    void updateUser(User user);

    List<User> getAll();
    User getUserByID(int id);
    List<User> getUserByRole(User.Role role);
}
