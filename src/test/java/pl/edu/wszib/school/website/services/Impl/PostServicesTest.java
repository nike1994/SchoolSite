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
import pl.edu.wszib.school.website.dao.ICommentDao;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.dao.IPostDao;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.PostModel;
import pl.edu.wszib.school.website.services.IPostServices;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfiguration.class})
@WebAppConfiguration
public class PostServicesTest {
    @MockBean
    IPostDao postDao;

    @MockBean
    IPageDao pageDao;

    @MockBean
    IWebsiteInformationsDao websiteInformationsDao;

    @MockBean
    ICommentDao commentDao;

    @Autowired
    IPostServices postServices;

    @Test
    public void createPostTest(){
        PostModel model = new PostModel();
        model.setTitle("tytuł1");
        model.setContent("bla bla bla");
        model.setAuthor(new User());
        model.setDate(LocalDateTime.now());
        model.setPage_id(1);
        model.setPost_id(1);

        Mockito.when(pageDao.getByID(1)).thenReturn(new Page());

        boolean result = postServices.createPost(model);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void createPostWhenTitleIsEmptyTest(){
        PostModel model = new PostModel();
        model.setTitle("");
        model.setContent("bla bla bla");
        model.setAuthor(new User());
        model.setDate(LocalDateTime.now());
        model.setPage_id(1);
        model.setPost_id(1);

        Mockito.when(pageDao.getByID(1)).thenReturn(new Page());

        boolean result = postServices.createPost(model);

        Assertions.assertEquals(false, result);
    }

    @Test
    public void createPostWhenAuthorIsNullTest(){
        PostModel model = new PostModel();
        model.setTitle("Tytuł1");
        model.setContent("bla bla bla");
        model.setDate(LocalDateTime.now());
        model.setPage_id(1);
        model.setPost_id(1);

        Mockito.when(pageDao.getByID(1)).thenReturn(new Page());

        boolean result = postServices.createPost(model);

        Assertions.assertEquals(false, result);
    }

    @Test
    public void updatePostTest(){
        PostModel model = new PostModel();
        model.setTitle("tytuł1");
        model.setContent("bla bla bla");
        model.setAuthor(new User());
        model.setDate(LocalDateTime.now());
        model.setPage_id(1);
        model.setPost_id(1);

        Mockito.when(postDao.getByID(1)).thenReturn(new Post());

        boolean result = postServices.updatePost(model);

        Assertions.assertEquals(true, result);
    }

    @Test
    public void updatePostWhenTitleIsEmptyTest(){
        PostModel model = new PostModel();
        model.setTitle("");
        model.setContent("bla bla bla");
        model.setAuthor(new User());
        model.setDate(LocalDateTime.now());
        model.setPage_id(1);
        model.setPost_id(1);

        Mockito.when(postDao.getByID(1)).thenReturn(new Post());

        boolean result = postServices.updatePost(model);

        Assertions.assertEquals(false, result);
    }


}
