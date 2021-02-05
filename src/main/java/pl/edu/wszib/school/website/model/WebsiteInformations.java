package pl.edu.wszib.school.website.model;

import pl.edu.wszib.school.website.helper.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "informations")
public class WebsiteInformations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String siteName;

    private String siteLogo;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> emails;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> telephons;

    @Column
    @Convert(converter = StringListConverter.class)
    private List<String> staticPages;

    public WebsiteInformations() {
    }

    public WebsiteInformations(String siteName, String siteLogo, List<String> emails, List<String> telephons, List<String> staticPages) {
        this.siteName = siteName;
        this.siteLogo = siteLogo;
        this.emails = emails;
        this.telephons = telephons;
        this.staticPages = staticPages;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteLogo() {
        return siteLogo;
    }

    public void setSiteLogo(String siteLogo) {
        this.siteLogo = siteLogo;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getTelephons() {
        return telephons;
    }

    public void setTelephons(List<String> telephons) {
        this.telephons = telephons;
    }

    public List<String> getStaticPages() {
        return staticPages;
    }

    public void setStaticPages(List<String> staticPages) {
        this.staticPages = staticPages;
    }
}
