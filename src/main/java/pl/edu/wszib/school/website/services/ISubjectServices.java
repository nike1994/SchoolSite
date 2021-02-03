package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;

import java.util.List;

public interface ISubjectServices {
    void insertSubject(SchoolSubjects subjects);
    void deleteSubject(SchoolSubjects subjects);
    void updateSubject(SchoolSubjects subjects);

    SchoolSubjects getSubjectByID(int id);
    List<SchoolSubjects> getAllSubjects();
    List<SchoolSubjects> getTeacherSubjects(User user);
    List<SchoolSubjects> getClassSubjects(SchoolClass sClass);
}
