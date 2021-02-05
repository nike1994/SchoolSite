package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.View.AllWebsiteInformationsModel;
import pl.edu.wszib.school.website.model.WebsiteInformations;

public interface IWebsiteInformationsService {
    public void updateInformations(WebsiteInformations informations);

    public AllWebsiteInformationsModel getinformations();
}
