package pl.edu.wszib.school.website.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Pupils")
public class Pupil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchoolClass sClass;

    @ManyToOne(fetch = FetchType.EAGER)
    private Parent parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Grade> grades;

    public Pupil() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SchoolClass getsClass() {
        return sClass;
    }

    public void setsClass(SchoolClass sClass) {
        this.sClass = sClass;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}
