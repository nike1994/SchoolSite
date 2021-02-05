package pl.edu.wszib.school.website.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.school.website.model.User;

@Component
@SessionScope
public class SessionObject {
    private User loggedUser =null;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isLogged(){
        return this.loggedUser != null;
    }
}
