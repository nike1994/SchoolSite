package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.View.GradeModel;

public interface IGradeService {
    public int createGrade(GradeModel model, int subject_id);

    public void deleteGrade(int id);
    public void updateGrade(int id, int grade);
}
