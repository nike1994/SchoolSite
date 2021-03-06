package pl.edu.wszib.school.website.model.View;

import java.util.List;

public class ParentModel {
    private int id;
    private int user_id;
    private String login;
    private String pass;
    private String name;
    private String surname;
    private List<Integer> subjects_id;


    public ParentModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Integer> getSubjects_id() {
        return subjects_id;
    }

    public void setSubjects_id(List<Integer> subjects_id) {
        this.subjects_id = subjects_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
