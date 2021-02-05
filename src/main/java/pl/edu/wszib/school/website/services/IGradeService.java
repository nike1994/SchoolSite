package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Grade;
import pl.edu.wszib.school.website.model.View.CreationGradeModel;

public interface IGradeService {
    public int createGrade(CreationGradeModel model);
    public void deleteGrade(int id);
    public void updateGrade(int id, int grade);
}
