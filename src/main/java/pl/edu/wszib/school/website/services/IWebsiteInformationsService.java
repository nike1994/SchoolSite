package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.WebsiteInformations;

import java.util.List;

public interface IWebsiteInformationsService {
    public void updateInformations(WebsiteInformations informations);

    public AllWebsiteInformationsModel getinformations();

    public List<Object> getButtonNavLogin(User.Role role);
    public void updatePages();
}
