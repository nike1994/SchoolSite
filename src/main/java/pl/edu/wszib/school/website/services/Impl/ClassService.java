package pl.edu.wszib.school.website.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IClassDao;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.View.ClassModel;
import pl.edu.wszib.school.website.services.IClassService;

import java.util.List;

@Service
public class ClassService implements IClassService {
    @Autowired
    IClassDao classDao;


    @Override
    public int createClass(SchoolClass schoolClass) {
        return classDao.insertClass(schoolClass);
    }

    @Override
    public boolean deleteClass(int id) {
        SchoolClass clas = classDao.getClassByID(id);
        if (clas == null) return false;
        classDao.removeClass(clas);
        return true;
    }

    @Override
    public boolean createClass(ClassModel model) {
        if (model.getName()==null || model.getName() =="" || model.getYear() == 0)
            return false;

        SchoolClass check = classDao.getClassByNameAndYear(model.getName(), model.getYear());
        if (check != null) return false;

        SchoolClass clas = new SchoolClass();
        clas.setYear(model.getYear());
        clas.setName(model.getName());

        classDao.insertClass(clas);
        return true;
    }

    @Override
    public boolean updateClass(ClassModel model) {
        if (model.getName()==null || model.getName() =="" || model.getYear() == 0)
            return false;

        SchoolClass clas = classDao.getClassByID(model.getId());
        if (clas == null) return false;

        clas.setName(model.getName());
        clas.setYear(model.getYear());
        classDao.updateClass(clas);
        return true;
    }

    @Override
    public List<SchoolClass> getAll() {
        return classDao.getAll();
    }

    @Override
    public SchoolClass getSchoolClass(int id) {
        return classDao.getClassByID(id);
    }

    @Override
    public String getClassJSONByID(int id) {
        SchoolClass classDB = classDao.getClassByID(id);
        ClassModel clas = new ClassModel();
        clas.setName(classDB.getName());
        clas.setYear(classDB.getYear());
        ObjectMapper mapper = new ObjectMapper();
        String response = null;
        try {
            response = mapper.writeValueAsString(clas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
