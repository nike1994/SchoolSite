package pl.edu.wszib.school.website.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Classes")
public class SchoolClass {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int year;
    private String name;

    @OneToMany(targetEntity =Pupil.class ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="class_id", referencedColumnName = "id")
    private Set<Pupil> pupils;

    @OneToMany(targetEntity =SchoolSubjects.class ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="class_id", referencedColumnName = "id")
    private Set<SchoolSubjects> subjects;


    public SchoolClass() {
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }

    public Set<SchoolSubjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SchoolSubjects> subjects) {
        this.subjects = subjects;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
