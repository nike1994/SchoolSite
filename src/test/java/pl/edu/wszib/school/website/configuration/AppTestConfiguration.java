package pl.edu.wszib.school.website.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.school.website.dao.*;
import pl.edu.wszib.school.website.dao.Impl.*;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.school.website.controllers",
        "pl.edu.wszib.school.website.services",
        "pl.edu.wszib.school.website.session"
})
public class AppTestConfiguration {

    @Bean
    public IWebsiteInformationsDao websiteInformationsDao(){
        return Mockito.mock(WebsiteInformationsDao.class);
    }

    @Bean
    public IClassDao classDao(){
        return Mockito.mock(IClassDao.class);
    }

    @Bean
    public ICommentDao commentDao(){
        return Mockito.mock(ICommentDao.class);
    }

    @Bean
    public IGradeDao gradeDao(){
        return Mockito.mock(IGradeDao.class);
    }

    @Bean
    public ILoginDao loginDao(){
        return Mockito.mock(ILoginDao.class);
    }

    @Bean
    public IPageDao pageDao(){
        return Mockito.mock(IPageDao.class);
    }

    @Bean
    public IParentDao parentDao(){
        return Mockito.mock(IParentDao.class);
    }

    @Bean
    public IPostDao postDao(){
        return Mockito.mock(IPostDao.class);
    }

    @Bean
    public IPupilDao pupilDao(){
        return Mockito.mock(IPupilDao.class);
    }

    @Bean
    public ISubjectDao subjectDao(){
        return Mockito.mock(ISubjectDao.class);
    }

    @Bean
    public IUserDao userDao(){
        return Mockito.mock(IUserDao.class);
    }


}
