package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Grade;
import pl.edu.wszib.school.website.model.Parent;
import pl.edu.wszib.school.website.model.Pupil;
import pl.edu.wszib.school.website.model.SchoolClass;

import java.util.List;

public interface IPupilService {

    Pupil getPupilByID(int id);
    List<Pupil> getAllPupils();
    List<Pupil> getClassPupils(SchoolClass sClass);
    List<Pupil> getParentPupils(Parent parent);
    List<Grade> getPupilGrades(Pupil pupil);
    SchoolClass getPupilClass(Pupil pupil);

}
