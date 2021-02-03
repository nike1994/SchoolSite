package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Parent;
import pl.edu.wszib.school.website.model.Pupil;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.User;

import java.util.List;

public interface IPupilDao {
    Integer insertPupil(Pupil pupil);
    void removePupil(Pupil pupil);
    void updatePupil(Pupil pupil);

    Pupil getPupilByID(int id);
    Pupil getPupilByUser(int userId);
    List<Pupil> getAllPupil();
    List<Pupil> getPupilsByClass(SchoolClass sClass);
    List<Pupil> getPupilsByParent(Parent parent);
}
