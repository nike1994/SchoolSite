package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Page;

import java.util.List;

public interface IPage {
    void insertPage(Page page);
    void updatePage(Page page);
    void removePage(Page page);

    Page getByID(int id);
    List<Page> getAll();
    

}
