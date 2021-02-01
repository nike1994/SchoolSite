package pl.edu.wszib.school.website.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Parents")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Pupil> kids;

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

    public Set<Pupil> getKids() {
        return kids;
    }

    public void setKids(Set<Pupil> kids) {
        this.kids = kids;
    }
}
