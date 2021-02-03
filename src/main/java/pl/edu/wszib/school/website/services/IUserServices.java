package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.CreationPupilModel;
import pl.edu.wszib.school.website.model.View.CreationTeacherParentModel;

import java.util.List;


public interface IUserServices {
    int createPupil(CreationPupilModel model);
    int createTeacher(CreationTeacherParentModel model);
    int createParent(CreationTeacherParentModel model);
    void updateUser(User user);
    void updateLogin(Login login);
    void deleteTeacher(int id);
    void deletePupil(int id);
    void deleteParentandPupils(int id);

    List<User> getAllUsers();
    User getUserByID(int id);
    List<User> getUsersByRole(String role);

    void authenticate(Login login);
    void logout();
}
