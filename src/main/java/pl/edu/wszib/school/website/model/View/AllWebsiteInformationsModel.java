package pl.edu.wszib.school.website.model.View;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.school.website.helper.StringListConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.beans.Transient;
import java.util.List;
import java.util.Map;

public class AllWebsiteInformationsModel {
    private String siteName;

    private String siteLogo;

    private List<String> emails;
    private List<String> telephons;

    Map<String,Object> Allsites;

    public AllWebsiteInformationsModel() {
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

    public Map<String, Object> getAllsites() {
        return Allsites;
    }

    public void setAllsites(Map<String, Object> allsites) {
        Allsites = allsites;
    }
}
