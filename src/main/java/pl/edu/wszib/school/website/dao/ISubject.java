package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;

import javax.security.auth.Subject;
import java.util.List;

public interface ISubject {
    void insertSubject(SchoolSubjects subjects);
    void removeSubject(SchoolSubjects subjects);
    void updateSubject(SchoolSubjects subjects);

    Subject getSubjectByID(int id);
    List<Subject> getAllSubjects();
    List<Subject> getSubjectsByTeacher(User user);
    List<Subject> getSubjectsByClass(SchoolClass sClass);
}
