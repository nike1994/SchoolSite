package pl.edu.wszib.school.website.model.View;


import java.util.List;

public class TeacherModel {
    private int id;
    private String login;
    private String pass;
    private String name;
    private String surname;
    private List<Integer> subjects_id;


    public TeacherModel(String login, String pass, String name, String surname, List<Integer> subjects_id) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
        this.subjects_id = subjects_id;
    }

    public TeacherModel() {
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
}
