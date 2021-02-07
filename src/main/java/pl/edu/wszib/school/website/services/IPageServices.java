package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.View.PageModel;

import java.util.List;

public interface IPageServices {
    int createPage(Page page);
    void updatePage(Page page);
    void deletePage(Page page);
    void createPage(PageModel model);
    void updatePage(PageModel model);
    void deletePageByModel(PageModel model);

    Page getByID(int id);
    String getPageJSONByID(int id);
    List<Page> getAllPages();
}
