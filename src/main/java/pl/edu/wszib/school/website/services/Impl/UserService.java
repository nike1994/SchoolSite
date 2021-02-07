package pl.edu.wszib.school.website.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.*;
import pl.edu.wszib.school.website.model.*;
import pl.edu.wszib.school.website.model.View.PageModel;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.model.View.PupilModel;
import pl.edu.wszib.school.website.model.View.TeacherModel;
import pl.edu.wszib.school.website.services.IUserServices;
import pl.edu.wszib.school.website.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserServices {

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

    @Resource
    SessionObject sessionObject;

    @Override
    public int createPupil(PupilModel model) {
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

        System.out.println();
        System.out.println(model.getParent_id());
        System.out.println();
        Parent parent = parentDao.getByUserId(model.getParent_id());
        System.out.println(parent);

        Pupil pupil = new Pupil();
        pupil.setsClass(clas);
        pupil.setUser(user);
        pupil.setParent(parent);

        return pupilDao.insertPupil(pupil);
    }

    @Override
    public int createTeacher(TeacherModel model) {
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
    public int createParent(ParentModel model) {
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
    public void updateParent(ParentModel model) {
        // TODO: 06.02.2021 sprawdzić dane i czy istnieje user
        User user = userDao.getUserByID(model.getUser_id());
        user.setName(model.getName());
        user.setSurName(model.getSurname());

        Login login = user.getLogin();
        login.setLogin(model.getLogin());
        login.setPassword(model.getPass());

        userDao.updateUser(user);
        loginDao.updateLogin(login);
    }

    @Override
    public void updatePupil(PupilModel model) {
        User user = userDao.getUserByID(model.getUser_id());
        user.setName(model.getName());
        user.setSurName(model.getSurname());
        Login login = user.getLogin();
        login.setLogin(model.getLogin());
        login.setPassword(model.getPass());

        userDao.updateUser(user);
        loginDao.updateLogin(login);
    }

    @Override
    public void updateLogin(Login login) {
        loginDao.updateLogin(login);
    }

    @Override
    public void deleteTeacher(int user_id) {
        User user = userDao.getUserByID(user_id);

        userDao.removeUser(user);
    }

    @Override
    public void deletePupil(int user_id) {
        Pupil pupil = pupilDao.getPupilByUser(user_id);

        pupilDao.removePupil(pupil);
    }

    @Override
    public void deleteParentandPupils(int user_id) {
        Parent parent = parentDao.getByUserId(user_id);

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
    public List<User> getUsersByRole(User.Role role) {
        return userDao.getUserByRole(role);
    }

    @Override
    public String getUserJSON(int id) {
        User user = userDao.getUserByID(id);
        if(user != null){
            Login login = user.getLogin();
            ParentModel model = new ParentModel();
            model.setLogin(login.getLogin());
            model.setPass(login.getPassword());
            model.setName(user.getName());
            model.setSurname(user.getSurName());

            ObjectMapper mapper = new ObjectMapper();
            String response = null;

            try {
                response = mapper.writeValueAsString(model);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return response;

        }
        return null;
    }

    @Override
    public void authenticate(Login login) {
        Login loginFromDb = this.loginDao.getByLogin(login.getLogin());
        if(loginFromDb == null){
            return;
        }
        if (loginFromDb.getPassword().equals(loginFromDb.getPassword())){
            this.sessionObject.setLoggedUser(loginFromDb.getUser());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }
}
