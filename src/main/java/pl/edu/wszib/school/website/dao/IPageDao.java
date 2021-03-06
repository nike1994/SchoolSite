package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Page;

import java.util.List;

public interface IPageDao {
    Integer insertPage(Page page);
    void updatePage(Page page);
    void removePage(Page page);

    Page getByID(int id);
    List<Page> getAllPages();
    Page getByTitle(String title);
}
