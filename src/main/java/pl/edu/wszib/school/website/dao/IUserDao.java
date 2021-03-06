package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.User;

import java.util.List;

public interface IUserDao {
    Integer insertUser(User user);
    void removeUser(User user);
    void updateUser(User user);

    List<User> getAll();
    User getUserByID(int id);
    List<User> getUserByRole(User.Role role);
}
