package pl.edu.wszib.school.website.services.Impl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.school.website.configuration.AppTestConfiguration;
import pl.edu.wszib.school.website.dao.*;
import pl.edu.wszib.school.website.model.Pupil;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.services.ISchoolRegisterService;
import pl.edu.wszib.school.website.services.IUserServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class SchoolRegisterServiceTest {
    @MockBean
    IUserDao userDao;

    @MockBean
    ILoginDao loginDao;

    @MockBean
    IPupilDao pupilDao;

    @MockBean
    IParentDao parentDao;

    @MockBean
    IClassDao classDao;

    @MockBean
    IWebsiteInformationsDao websiteInformationsDao;

    @MockBean
    ISubjectDao subjectDao;

    @Autowired
    ISchoolRegisterService schoolRegisterService;

    @Test
    public void createGradesTest(){
        List<Map<String,String>> JSON = new ArrayList<>();
        JSON.add(new HashMap<String,String>(){{
            put("grade","4");
            put("pupil_id","1");
            put("description","Zadanie domowe");
            put("subject_id","1");
        }});
        JSON.add(new HashMap<String,String>(){{
            put("grade","2");
            put("pupil_id","1");
            put("description","sprawdzian");
            put("subject_id","1");
        }});

        Mockito.when(subjectDao.getSubjectByID(1)).thenReturn(returnSubject());
        Mockito.when(pupilDao.getPupilByID(1)).thenReturn(new Pupil());

        boolean result = schoolRegisterService.createGrades(JSON);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void createGradesWhenEmptyJSONElementTest(){
        List<Map<String,String>> JSON = new ArrayList<>();
        JSON.add(new HashMap<String,String>(){{
            put("grade","4");
            put("pupil_id","1");
            put("subject_id","1");
        }});
        JSON.add(new HashMap<String,String>(){{
            put("grade","2");
            put("pupil_id","1");
            put("description","sprawdzian");
            put("subject_id","1");
        }});

        Mockito.when(subjectDao.getSubjectByID(1)).thenReturn(returnSubject());
        Mockito.when(pupilDao.getPupilByID(1)).thenReturn(new Pupil());

        boolean result = schoolRegisterService.createGrades(JSON);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void createGradesWhenSubjectNotExsitTest(){
        List<Map<String,String>> JSON = new ArrayList<>();
        JSON.add(new HashMap<String,String>(){{
            put("grade","4");
            put("pupil_id","1");
            put("description","Zadanie domowe");
            put("subject_id","1");
        }});
        JSON.add(new HashMap<String,String>(){{
            put("grade","2");
            put("pupil_id","1");
            put("description","sprawdzian");
            put("subject_id","1");
        }});

        Mockito.when(subjectDao.getSubjectByID(1)).thenReturn(null);
        Mockito.when(pupilDao.getPupilByID(1)).thenReturn(new Pupil());

        boolean result = schoolRegisterService.createGrades(JSON);
        Assertions.assertEquals(false, result);
    }

    private SchoolSubjects returnSubject(){
        SchoolSubjects subject = new SchoolSubjects();
        subject.setName("Przyroda");
        subject.setId(3);

        return subject;
    }
}
