package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IGradeDao;
import pl.edu.wszib.school.website.dao.IPupilDao;
import pl.edu.wszib.school.website.model.Grade;
import pl.edu.wszib.school.website.model.Parent;
import pl.edu.wszib.school.website.model.Pupil;
import pl.edu.wszib.school.website.model.SchoolClass;
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
}
