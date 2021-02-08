package pl.edu.wszib.school.website.model.View;

import pl.edu.wszib.school.website.model.User;

public class PasswordUpdateModel {
    private String pass1;
    private String pass2;
    private User user;

    public PasswordUpdateModel() {
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
