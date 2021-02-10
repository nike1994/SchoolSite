package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ISchoolRegisterService {
    Set<?> getDropdownButtons(User user);
    String getTableJSON(User user);
    String getTableJSONByID(int id, User.Role role);
    boolean createGrades(List<Map<String,String>> JSON);
}
