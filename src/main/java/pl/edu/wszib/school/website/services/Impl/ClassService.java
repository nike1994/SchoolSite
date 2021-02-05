package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IClassDao;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.services.IClassService;

@Service
public class ClassService implements IClassService {
    @Autowired
    IClassDao classDao;


    @Override
    public int createClass(SchoolClass schoolClass) {
        return classDao.insertClass(schoolClass);
    }

    @Override
    public void deleteClass(int id) {
        classDao.removeClass(classDao.getClassByID(id));
    }
}
