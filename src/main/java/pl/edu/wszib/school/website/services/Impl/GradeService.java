package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IGradeDao;
import pl.edu.wszib.school.website.dao.IPupilDao;
import pl.edu.wszib.school.website.dao.ISubjectDao;
import pl.edu.wszib.school.website.model.Grade;
import pl.edu.wszib.school.website.model.Pupil;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.View.GradeModel;
import pl.edu.wszib.school.website.services.IGradeService;

@Service
public class GradeService implements IGradeService {

    @Autowired
    IGradeDao gradeDao;

    @Autowired
    IPupilDao pupilDao;

    @Autowired
    ISubjectDao subjectDao;


    @Override
    public int createGrade(GradeModel model) {
        SchoolSubjects subject = subjectDao.getSubjectByID(model.getId_subject());
        Pupil pupil = pupilDao.getPupilByID(model.getId_pupil());

        Grade grade = new Grade();
        grade.setGrade(model.getGrade());
        grade.setDescription(model.getDescription());
        grade.setPupil(pupil);
        grade.setSubject(subject);

        return gradeDao.insertGrade(grade);
    }

    @Override
    public void deleteGrade(int id) {
        Grade grade = gradeDao.getByID(id);
        gradeDao.removeGrade(grade);
    }

    @Override
    public void updateGrade(int id, int grade) {
        Grade gradeDB =gradeDao.getByID(id);
        gradeDB.setGrade(grade);
        gradeDao.updateGrade(gradeDB);
    }
}
