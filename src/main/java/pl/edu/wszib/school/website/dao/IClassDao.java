package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.User;

import java.util.List;

public interface IClassDao {
    Integer insertClass(SchoolClass sClass);
    void updateClass(SchoolClass sClass);
    void removeClass(SchoolClass sClass);

    SchoolClass getClassByID(int id);
    SchoolClass getClassByUser(User user);
    SchoolClass getClassByNameAndYear(String name, int year);
    List<SchoolClass> getAll();
}
