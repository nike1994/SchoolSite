package pl.edu.wszib.school.website.model.View;

public class CreationGradeModel {
    private int grade;
    private int id_subject;
    private int id_pupil;
    private String description;

    public CreationGradeModel(int grade, int id_subject, int id_pupil, String description) {
        this.grade = grade;
        this.id_subject = id_subject;
        this.id_pupil = id_pupil;
        this.description = description;
    }

    public CreationGradeModel() {
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int getId_pupil() {
        return id_pupil;
    }

    public void setId_pupil(int id_pupil) {
        this.id_pupil = id_pupil;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
