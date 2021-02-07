package pl.edu.wszib.school.website.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IGradeDao;
import pl.edu.wszib.school.website.dao.IPupilDao;
import pl.edu.wszib.school.website.model.*;
import pl.edu.wszib.school.website.model.View.PupilModel;
import pl.edu.wszib.school.website.services.IPupilService;

import java.util.List;

@Service
public class PupilService implements IPupilService {

    @Autowired
    IPupilDao pupilDao;

    @Autowired
    IGradeDao gradeDao;

    @Override
    public Pupil getPupilByID(int id) {
        return pupilDao.getPupilByID(id);
    }

    @Override
    public List<Pupil> getAllPupils() {
        return pupilDao.getAllPupil();
    }

    @Override
    public List<Pupil> getClassPupils(SchoolClass sClass) {
        return pupilDao.getPupilsByClass(sClass);
    }

    @Override
    public List<Pupil> getParentPupils(Parent parent) {
        return pupilDao.getPupilsByParent(parent);
    }

    @Override
    public List<Grade> getPupilGrades(Pupil pupil) {
        return gradeDao.getByPupil(pupil);
    }

    @Override
    public String getPupilJSON(int id) {
        Pupil pupil = pupilDao.getPupilByUserID(id);
        PupilModel model = new PupilModel();
        User user = pupil.getUser();
        Login login = user.getLogin();
        User parent = pupil.getParent().getUser();
        SchoolClass clas = pupil.getsClass();

        model.setLogin(login.getLogin());
        model.setPass(login.getPassword());
        model.setName(user.getName());
        model.setSurname(user.getSurName());
        model.setParent_id(parent.getId());
        model.setSchoolClass_id(clas.getId());

        ObjectMapper mapper = new ObjectMapper();
        String response = null;

        try {
            response = mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }
}
