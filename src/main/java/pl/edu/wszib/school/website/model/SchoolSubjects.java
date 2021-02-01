package pl.edu.wszib.school.website.model;

import javax.persistence.*;

@Entity(name="Subjects")
public class SchoolSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    private  SchoolClass sClass;

    private String name;

    public SchoolSubjects() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public SchoolClass getsClass() {
        return sClass;
    }

    public void setsClass(SchoolClass sClass) {
        this.sClass = sClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
