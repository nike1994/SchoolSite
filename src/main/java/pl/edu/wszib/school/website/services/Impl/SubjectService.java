package pl.edu.wszib.school.website.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IClassDao;
import pl.edu.wszib.school.website.dao.ISubjectDao;
import pl.edu.wszib.school.website.dao.IUserDao;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.SubjectModel;
import pl.edu.wszib.school.website.services.ISubjectServices;

import java.util.List;

@Service
public class SubjectService implements ISubjectServices {

    @Autowired
    ISubjectDao subjectDao;

    @Autowired
    IUserDao userDao;

    @Autowired
    IClassDao classDao;

    @Override
    public void insertSubject(SchoolSubjects subjects) {
        subjectDao.insertSubject(subjects);
    }

    @Override
    public boolean createSubject(SubjectModel model) {
        SchoolSubjects subject = new SchoolSubjects();
        subject.setName(model.getName());

        User teacher = userDao.getUserByID(model.getTeacher_id());
        SchoolClass clas = classDao.getClassByID(model.getClass_id());
        if(teacher == null || clas == null){
            return false;
        }
        subject.setsClass(clas);
        subject.setTeacher(teacher);

        subjectDao.insertSubject(subject);
        return true;
    }

    @Override
    public boolean deleteSubject(int id) {
        SchoolSubjects subject = subjectDao.getSubjectByID(id);
        if (subject == null){
            return false;
        }
        subjectDao.removeSubject(subject);
        return true;
    }

    @Override
    public void updateSubject(SchoolSubjects subjects) {
        subjectDao.updateSubject(subjects);
    }

    @Override
    public boolean updateSubject(SubjectModel model) {
        SchoolSubjects subject = subjectDao.getSubjectByID(model.getId());

        if(subject == null) return false;

        subject.setName(model.getName());

        User teacher = userDao.getUserByID(model.getTeacher_id());
        SchoolClass clas = classDao.getClassByID(model.getClass_id());

        if(teacher == null || clas == null) return false;

        subject.setsClass(clas);
        subject.setTeacher(teacher);

        subjectDao.updateSubject(subject);
        return true;
    }

    @Override
    public SchoolSubjects getSubjectByID(int id) {
        return subjectDao.getSubjectByID(id);
    }

    @Override
    public String getSubjectJSONByID(int id) {
        SchoolSubjects subjectsDB = subjectDao.getSubjectByID(id);
        SubjectModel subject = new SubjectModel();
        subject.setName(subjectsDB.getName());
        subject.setClass_id(subjectsDB.getsClass().getId());
        subject.setTeacher_id(subjectsDB.getTeacher().getId());

        ObjectMapper mapper = new ObjectMapper();
        String response = null;

        try {
            response = mapper.writeValueAsString(subject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public List<SchoolSubjects> getAllSubjects() {
        return subjectDao.getAllSubjects();
    }

    @Override
    public List<SchoolSubjects> getTeacherSubjects(User user) {
        return getTeacherSubjects(user);
    }

    @Override
    public List<SchoolSubjects> getClassSubjects(SchoolClass sClass) {
        return subjectDao.getSubjectsByClass(sClass);
    }
}
