package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.services.IPageServices;

import java.util.List;

@Service
public class PageService implements IPageServices {

    @Autowired
    IPageDao pageDao;

    @Override
    public int createPage(Page page) {
        return pageDao.insertPage(page);
    }

    @Override
    public void updatePage(Page page) {
        pageDao.updatePage(page);
    }

    @Override
    public void deletePage(Page page) {
        pageDao.removePage(page);
    }

    @Override
    public Page getByID(int id) {
        return pageDao.getByID(id);
    }

    @Override
    public List<Page> getAllPages() {
        return pageDao.getAllPages();
    }
}
