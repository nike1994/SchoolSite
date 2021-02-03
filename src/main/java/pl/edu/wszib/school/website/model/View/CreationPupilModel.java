package pl.edu.wszib.school.website.model.View;

public class CreationPupilModel {
    private String login;
    private String pass;
    private String name;
    private String surname;
    private int parent_id;
    private int schoolClass_id;

    public CreationPupilModel(String login, String pass, String name, String surname, int parent_id, int schoolClass) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
        this.parent_id = parent_id;
        this.schoolClass_id = schoolClass;
    }

    public CreationPupilModel() {
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

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getSchoolClass_id() {
        return schoolClass_id;
    }

    public void setSchoolClass_id(int schoolClass_id) {
        this.schoolClass_id = schoolClass_id;
    }
}
