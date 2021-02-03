package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Grade;
import pl.edu.wszib.school.website.model.Pupil;

import javax.security.auth.Subject;
import java.util.List;

public interface IGradeDao {
    Integer insertGrade(Grade grade);
    void updateGrade(Grade grade);
    void removeGrade(Grade grade);

    Grade getByID(int id);
    List<Grade> getAll();
    List<Grade> getBySubject(Subject subject);
    List<Grade> getByPupil(Pupil pupil);
}
