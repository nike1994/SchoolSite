package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;

import java.util.List;

public interface ISubjectDao {
    void insertSubject(SchoolSubjects subjects);
    void removeSubject(SchoolSubjects subjects);
    void updateSubject(SchoolSubjects subjects);

    SchoolSubjects getSubjectByID(int id);
    List<SchoolSubjects> getAllSubjects();
    List<SchoolSubjects> getSubjectsByTeacher(User user);
    List<SchoolSubjects> getSubjectsByClass(SchoolClass sClass);
}
