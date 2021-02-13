package pl.edu.wszib.school.website.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Parents")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    private Set<Pupil> childrens;

    public Parent() {
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

    public Set<Pupil> getChildrens() {
        return childrens;
    }

    public void setChildrens(Set<Pupil> childrens) {
        this.childrens = childrens;
    }
}
