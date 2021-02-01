package pl.edu.wszib.school.website.model;

import javax.persistence.*;

@Entity(name="Grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int grade;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pupil pupil;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchoolSubjects subject;
    private String description;

    public Grade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Pupil getPupil() {
        return pupil;
    }

    public void setPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    public SchoolSubjects getSubject() {
        return subject;
    }

    public void setSubject(SchoolSubjects subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
