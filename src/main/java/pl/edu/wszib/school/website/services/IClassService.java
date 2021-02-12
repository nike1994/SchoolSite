package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.View.ClassModel;

import java.util.List;

public interface IClassService {
    public int createClass(SchoolClass schoolClass);
    public boolean createClass(ClassModel model);
    public boolean updateClass(ClassModel model);
    public boolean deleteClass(int id);

    public List<SchoolClass> getAll();
    public SchoolClass getSchoolClass(int id);
    public  String getClassJSONByID(int id);

}
