package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.Parent;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.model.View.PasswordUpdateModel;
import pl.edu.wszib.school.website.model.View.PupilModel;
import pl.edu.wszib.school.website.model.View.TeacherModel;

import java.util.List;


public interface IUserServices {
    int createPupil(PupilModel model);
    int createTeacher(TeacherModel model);
    int createParent(ParentModel model);
    void updateUser(User user);
    boolean updateParent(ParentModel model);
    boolean updatePupil(PupilModel model);
    boolean updateTeacher(TeacherModel model);
    void updateLogin(Login login);
    boolean deleteTeacher(int user_id);
    boolean deletePupil(int user_id);
    boolean deleteParentandPupils(int user_id);
    void passwordUpdate(PasswordUpdateModel model);

    List<User> getAllUsers();
    User getUserByID(int id);
    List<User> getUsersByRole(User.Role role);
    String getUserJSON(int id);
    int getHashPass(User user);

    void authenticate(Login login);
    void logout();
}
