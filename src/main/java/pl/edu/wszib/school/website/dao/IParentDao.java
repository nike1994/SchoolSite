package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Parent;

import java.util.List;

public interface IParentDao {
    Integer insertParent(Parent parent);
    void removeParent(Parent parent);
    void updateParent(Parent parent);

    Parent getByID(int id);
    List<Parent> getAll();
    Parent getByUserId(int id);
}
