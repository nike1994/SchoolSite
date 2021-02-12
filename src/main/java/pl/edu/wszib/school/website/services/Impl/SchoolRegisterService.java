package pl.edu.wszib.school.website.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.*;
import pl.edu.wszib.school.website.model.*;
import pl.edu.wszib.school.website.services.ISchoolRegisterService;

import javax.security.auth.Subject;
import java.util.*;

@Service
public class SchoolRegisterService implements ISchoolRegisterService {

    @Autowired
    IParentDao parentDao;

    @Autowired
    ISubjectDao subjectDao;

    @Autowired
    IPupilDao pupilDao;

    @Autowired
    IGradeDao gradeDao;

    @Override
    public Set<?> getDropdownButtons(User user) {
        if(user.getRole().equals(User.Role.PARENT)){
            Parent parent=parentDao.getByUserId(user.getId());
            Set<Pupil> children = parent.getChildrens();
            return children;
        }else if(user.getRole().equals(User.Role.TEACHER)){
            Set<SchoolSubjects> subjects = new HashSet<>(subjectDao.getSubjectsByTeacher(user));
            return subjects;
        }
        return null;
    }

    public String getTableJSON(User user){
        if(user.getRole().equals(User.Role.PARENT)){
            Parent parent=parentDao.getByUserId(user.getId());
            Set<Pupil> children = parent.getChildrens();
            Pupil child = children.iterator().next();
            return getTablePupil(child);

        }else if(user.getRole().equals(User.Role.TEACHER)){
            List<SchoolSubjects> subjects = subjectDao.getSubjectsByTeacher(user);
            if (subjects == null){
                return null;
            }
            return getTableTeacher(subjects.iterator().next());

        }else if(user.getRole().equals(User.Role.PUPIL)){
            Pupil pupil = pupilDao.getPupilByUser(user.getId());
            return getTablePupil(pupil);
        }
        return null;
    }

    @Override
    public boolean createGrades(List<Map<String,String>> JSON) {
        if (JSON == null){
            return false;
        }else if(JSON.isEmpty()){
           return false;
        }else{
            //każda ocena w tablicy ma ten sam przedmiot
            System.out.println();
            System.out.println(JSON.size());
            System.out.println();
            SchoolSubjects subject = subjectDao.getSubjectByID(Integer.parseInt(JSON.get(0).get("subject_id")));

            for (Map<String,String> gradeJSON: JSON) {
                Grade grade = new Grade();
                grade.setGrade(Integer.parseInt(gradeJSON.get("grade")));
                System.out.println(grade.getGrade());
                grade.setDescription(gradeJSON.get("description"));
                System.out.println(grade.getDescription());
                grade.setSubject(subject);
                System.out.println(grade.getSubject().getId());

                Pupil pupil = pupilDao.getPupilByID(Integer.parseInt(gradeJSON.get("pupil_id")));
                grade.setPupil(pupil);
                System.out.println(grade.getPupil().getId());

                gradeDao.insertGrade(grade);
            }
            return true;
        }
    }

    @Override
    public String getTableJSONByID(int id, User.Role role) {
        if (role.equals(User.Role.PARENT)){
            Pupil pupil = pupilDao.getPupilByID(id);
            return getTablePupil(pupil);

        }else if(role.equals(User.Role.TEACHER)){
            SchoolSubjects subject = subjectDao.getSubjectByID(id);
            return getTableTeacher(subject);
        }
        return null;
    }

    private String getTablePupil(Pupil pupil){
        List<Grade> grades = gradeDao.getByPupil(pupil);
        System.out.println(grades.size());
        Set<SchoolSubjects> subjects = pupil.getsClass().getSubjects();
        System.out.println(subjects.size());

        String JSONTable ="{\"tableConfiguration\":[{\"title\":\"Przedmiot\", \"field\":\"name\", \"width\":150}," +
                                                "{\"title\":\"Ocena\", \"field\":\"ocena\"}],"+
                          "\"tableData\":[";

        Iterator<SchoolSubjects> schoolSubjectsIterator = subjects.iterator();
        while (schoolSubjectsIterator.hasNext()) {
            SchoolSubjects subject = schoolSubjectsIterator.next();
            JSONTable+="{\"name\":\""+subject.getName()+"\", \"ocena\":\"\", \"_children\":[";

            Iterator<Grade> gradeIterator = grades.iterator();
            while (gradeIterator.hasNext()){
                Grade grade = gradeIterator.next();
                if(grade.getSubject().getId() == subject.getId()){
                    JSONTable+="{\"name\":\""+grade.getDescription().replaceAll("\"", "\\\\\"")+"\",\"ocena\":\""+grade.getGrade()+"\"}";
                    if (gradeIterator.hasNext()){
                        JSONTable+=",";
                    }
                }
            }

            JSONTable+="]}";
            if(schoolSubjectsIterator.hasNext()){
                JSONTable+=",";
            }
        }

        JSONTable+="]}";
        System.out.println(JSONTable);
        return JSONTable;
    }

    private String getTableTeacher(SchoolSubjects subject){
        List<Grade> grades = gradeDao.getBySubject(subject);
        SchoolClass clas = subject.getsClass();
        Set<Pupil> pupils = clas.getPupils();

        //definicja kolumn {tekst, odwołanie, inne parametry}
        String tableConfiguration="\"tableConfiguration\":[{\"title\":\"id\", \"field\":\"id\", \"visible\":false},{\"title\":\"Uczeń\", \"field\":\"pupil\",\"width\":150}";
        //definicja wierszy {uczeń, ocena0, ..., ocenaN}
        String tableRow =",\"tableData\":[";

        Map<String,String > descriptions = new HashMap<>();
        Iterator<Grade> gradeIterator = grades.listIterator();
        int index =0;
        while (gradeIterator.hasNext()){
            Grade grade = gradeIterator.next();
            if(!descriptions.containsKey(grade.getDescription())){
                tableConfiguration+=",{\"title\":\""+grade.getDescription().replaceAll("\"", "\\\\\"")+"\", \"field\":\"ocena"+index+"\"}";
                descriptions.put(grade.getDescription(),"ocena"+index);
                index++;
            }

        }
        tableConfiguration+="]";

        Iterator<Pupil> pupilIterator = pupils.iterator();

        while (pupilIterator.hasNext()){
            Pupil pupil = pupilIterator.next();
            User user =pupil.getUser();
            tableRow+="{\"id\":\""+pupil.getId()+"\",\"pupil\":\""+user.getName()+" "+user.getSurName()+"\"";
            for (int j =0; j<grades.size();j++){
                if(grades.get(j).getPupil().getId() == pupil.getId()){
                    tableRow+=",\""+descriptions.get(grades.get(j).getDescription())+"\":\""+grades.get(j).getGrade()+"\"";
                }
            }
            tableRow+="}";
            if(pupilIterator.hasNext()){
                tableRow+=",";
            }
        }
        tableRow+="]";

        String JSONTable ="{"+tableConfiguration+tableRow+"}";
        return JSONTable;
    }
}
