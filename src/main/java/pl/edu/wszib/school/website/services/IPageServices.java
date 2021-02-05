package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Page;

import java.util.List;

public interface IPageServices {
    int createPage(Page page);
    void updatePage(Page page);
    void deletePage(Page page);

    Page getByID(int id);
    List<Page> getAllPages();
}
