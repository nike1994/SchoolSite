package pl.edu.wszib.school.website.services.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.school.website.configuration.AppTestConfiguration;
import pl.edu.wszib.school.website.dao.IGradeDao;
import pl.edu.wszib.school.website.dao.IPupilDao;
import pl.edu.wszib.school.website.dao.ISubjectDao;
import pl.edu.wszib.school.website.services.IGradeService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class GradeServiceTest {
    @MockBean
    IGradeDao gradeDao;

    @MockBean
    IPupilDao pupilDao;

    @MockBean
    ISubjectDao subjectDao;

    @Autowired
    IGradeService gradeService;

    @Test

}
