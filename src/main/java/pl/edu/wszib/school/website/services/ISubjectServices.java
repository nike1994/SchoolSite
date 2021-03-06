package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.SubjectModel;

import java.util.List;

public interface ISubjectServices {
    void insertSubject(SchoolSubjects subjects);
    boolean createSubject(SubjectModel model);
    boolean deleteSubject(int id);
    void updateSubject(SchoolSubjects subjects);
    boolean updateSubject(SubjectModel model);

    SchoolSubjects getSubjectByID(int id);
    String getSubjectJSONByID(int id);
    List<SchoolSubjects> getAllSubjects();
    List<SchoolSubjects> getTeacherSubjects(User user);
    List<SchoolSubjects> getClassSubjects(SchoolClass sClass);
}
