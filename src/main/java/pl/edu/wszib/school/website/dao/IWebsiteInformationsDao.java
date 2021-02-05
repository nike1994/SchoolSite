package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.WebsiteInformations;

public interface IWebsiteInformationsDao {
    public void createInformations(WebsiteInformations websiteInformations);
    public  void updateInfomations(WebsiteInformations websiteInformations);

    public  WebsiteInformations getInformations();
}
