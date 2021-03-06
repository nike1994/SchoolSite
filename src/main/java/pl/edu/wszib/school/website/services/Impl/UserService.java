package pl.edu.wszib.school.website.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.*;
import pl.edu.wszib.school.website.model.*;
import pl.edu.wszib.school.website.model.View.ParentModel;
import pl.edu.wszib.school.website.model.View.PasswordUpdateModel;
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
        Login loginCheck = loginDao.getByLogin(model.getLogin());
        if(loginCheck == null) {
            User user = new User();
            if (model.getName()=="") return 0;

            user.setName(model.getName());

            if (model.getSurname()=="") return 0;

            user.setSurName(model.getSurname());

            user.setRole(User.Role.PUPIL);

            Login login = new Login();
            login.setLogin(model.getLogin());

            if (model.getPass()=="") return 0;

            login.setPassword(model.getPass());
            login.setUser(user);

            user.setLogin(login);

            SchoolClass clas = classDao.getClassByID(model.getSchoolClass_id());

            if (clas == null) return 0;

            Parent parent = parentDao.getByUserId(model.getParent_id());
            if(parent == null) return 0;

            Pupil pupil = new Pupil();
            pupil.setsClass(clas);
            pupil.setUser(user);
            pupil.setParent(parent);

            return pupilDao.insertPupil(pupil);
        }
        return 0;
    }

    @Override
    public int createTeacher(TeacherModel model) {
        Login loginCheck = loginDao.getByLogin(model.getLogin());
        if(loginCheck == null && model.getName() !="" && model.getLogin()!=""
                && model.getSurname()!="" && model.getPass()!=""){
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
        return 0;
    }

    @Override
    public int createParent(ParentModel model) {
        Login loginCheck = loginDao.getByLogin(model.getLogin());
        if(loginCheck == null && model.getName() !="" && model.getLogin()!=""
                && model.getSurname()!="" && model.getPass()!="") {
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
        return 0;
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public boolean updateParent(ParentModel model) {
        if (model.getPass() == "" || model.getLogin() == ""
                || model.getName() == "" || model.getSurname() == "")
            return false;

        Login loginCheck = loginDao.getByLogin(model.getLogin());
        if (loginCheck != null){
            if(loginCheck.getUser().getId() != model.getUser_id()) {
                return false;
            }
        }

        User user = userDao.getUserByID(model.getUser_id());

        if (user == null) return false;

        user.setName(model.getName());
        user.setSurName(model.getSurname());

        Login login = user.getLogin();
        login.setLogin(model.getLogin());
        login.setPassword(model.getPass());

        userDao.updateUser(user);
        loginDao.updateLogin(login);
        return true;
    }

    @Override
    public boolean updatePupil(PupilModel model) {
        if (model.getPass() == "" || model.getLogin() == ""
                || model.getName() == "" || model.getSurname() == "")
            return false;

        Login loginCheck = loginDao.getByLogin(model.getLogin());
        if(loginCheck != null){
            if(loginCheck.getUser().getId() != model.getUser_id()){
                return false;
            }
        }

        User user = userDao.getUserByID(model.getUser_id());
        if (user == null) return false;

        user.setName(model.getName());
        user.setSurName(model.getSurname());
        Login login = user.getLogin();
        login.setLogin(model.getLogin());
        login.setPassword(model.getPass());

        userDao.updateUser(user);
        loginDao.updateLogin(login);
        return true;
    }

    @Override
    public boolean updateTeacher(TeacherModel model) {
        if (model.getPass() == "" || model.getLogin() == ""
                || model.getName() == "" || model.getSurname() == "")
            return false;

        Login loginCheck = loginDao.getByLogin(model.getLogin());
        if(loginCheck != null){
            if(loginCheck.getUser().getId() != model.getUser_id()){
                return false;
            }
        }

        User user = userDao.getUserByID(model.getUser_id());
        if(user == null){
            return false;
        }
        user.setName(model.getName());
        user.setSurName(model.getSurname());

        Login login = user.getLogin();
        login.setLogin(model.getLogin());
        login.setPassword(model.getPass());

        userDao.updateUser(user);
        loginDao.updateLogin(login);
        return true;

    }

    @Override
    public void updateLogin(Login login) {
        loginDao.updateLogin(login);
    }

    @Override
    public boolean deleteTeacher(int user_id) {
        User user = userDao.getUserByID(user_id);
        if (user != null){
            userDao.removeUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePupil(int user_id) {

        Pupil pupil = pupilDao.getPupilByUser(user_id);
        if (pupil == null) return false;

        pupilDao.removePupil(pupil);
        return true;
    }

    @Override
    public boolean deleteParentandPupils(int user_id) {
        Parent parent = parentDao.getByUserId(user_id);
        if (parent==null) return false;

        parentDao.removeParent(parent);
        return true;
    }

    @Override
    public void passwordUpdate(PasswordUpdateModel model) {
        if(model.getPass1().equals(model.getPass2())){
            User user = model.getUser();
            Login login = user.getLogin();
            if (!model.getPass1().equals(login.getPassword())){
                login.setPassword(model.getPass1());
                loginDao.updateLogin(login);
            }else{
                System.out.println("hasło jest takie samo jak stare");
            }
        }else{
            System.out.println("hasła są różne");
        }
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
        if (loginFromDb.getPassword().equals(login.getPassword())){
            this.sessionObject.setLoggedUser(loginFromDb.getUser());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }

    @Override
    public int getHashPass(User user) {
        Login login = user.getLogin();
        return hashCode(login.getPassword());
    }

    private int hashCode(String str) {
        int hash = 0;
        int i;
        char chr;
        for (i = 0; i < str.length(); i++) {
            chr   = str.charAt(i);
            hash  = ((hash << 5) - hash) + chr;
            hash |= 0; // Convert to 32bit integer
        }
        System.out.println(hash);
        return hash;
    }
}
