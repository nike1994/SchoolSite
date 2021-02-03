package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.*;
import pl.edu.wszib.school.website.model.*;
import pl.edu.wszib.school.website.model.View.CreationPupilModel;
import pl.edu.wszib.school.website.model.View.CreationTeacherParentModel;
import pl.edu.wszib.school.website.services.IUserServices;

import java.util.List;

@Service
public class UserServices implements IUserServices {

    @Autowired
    IUserDao userDao;

    @Autowired
    ILoginDao loginDao;

    @Autowired
    IPupilDao pupilDao;

    @Autowired
    IParentDao parentDao;

    @Autowired
    IClassDao classDao;

    @Override
    public int createPupil(CreationPupilModel model) {
        User user = new User();
        user.setName(model.getName());
        user.setSurName(model.getSurname());
        user.setRole(User.Role.PUPIL);

        Login login = new Login();
        login.setLogin(model.getLogin());
        login.setPassword(model.getPass());
        login.setUser(user);

        user.setLogin(login);

        SchoolClass clas = classDao.getClassByID(model.getSchoolClass_id());
        Parent parent = parentDao.getByID(model.getParent_id());

        Pupil pupil = new Pupil();
        pupil.setsClass(clas);
        pupil.setUser(user);
        pupil.setParent(parent);

        return pupilDao.insertPupil(pupil);
    }

    @Override
    public int createTeacher(CreationTeacherParentModel model) {
        User user = new User();
        user.setName(model.getName());
        user.setSurName(model.getSurname());
        user.setRole(User.Role.TEACHER);

        Login login = new Login();
        login.setLogin(model.getLogin());
        login.setPassword(model.getPass());
        login.setUser(user);

        user.setLogin(login);

        return userDao.insertUser(user);
    }

    @Override
    public int createParent(CreationTeacherParentModel model) {
        User user = new User();
        user.setName(model.getName());
        user.setSurName(model.getSurname());
        user.setRole(User.Role.PARENT);

        Login login = new Login();
        login.setLogin(model.getLogin());
        login.setPassword(model.getPass());
        login.setUser(user);

        user.setLogin(login);

        Parent parent = new Parent();
        parent.setUser(user);

       return parentDao.insertParent(parent);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateLogin(Login login) {
        loginDao.updateLogin(login);
    }

    @Override
    public void deleteTeacher(int id) {
        User user = userDao.getUserByID(id);

        userDao.removeUser(user);
    }

    @Override
    public void deletePupil(int id) {
        Pupil pupil = pupilDao.getPupilByUser(id);

        pupilDao.removePupil(pupil);
    }

    @Override
    public void deleteParentandPupils(int id) {
        Parent parent = parentDao.getByUserId(id);

        parentDao.removeParent(parent);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User getUserByID(int id) {
        return userDao.getUserByID(id);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userDao.getUserByRole(role);
    }

    @Override
    public void authenticate(Login login) {

    }

    @Override
    public void logout() {

    }
}
