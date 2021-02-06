package pl.edu.wszib.school.website.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.dao.IPostDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.PostModel;
import pl.edu.wszib.school.website.services.IPostServices;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServices implements IPostServices {
    @Autowired
    IPostDao postDao;

    @Autowired
    IPageDao pageDao;

    @Override
    public int insertPost(Post post) {
        return postDao.insertPost(post);
    }

    @Override
    public void deletePost(Post post) {
        postDao.removePost(post);
    }

    @Override
    public void updatePost(Post post) {
        postDao.updatePost(post);
    }

    @Override
    public void updatePost(PostModel model) {
        Post post = postDao.getByID(model.getPost_id());
        post.setTitle(model.getTitle());
        post.setContent(model.getContent());
        postDao.updatePost(post);
    }

    @Override
    public void createPost(PostModel model) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");


        Post post = new Post();
        post.setTitle(model.getTitle());
        post.setAuthor(model.getAuthor());
        post.setPage(pageDao.getByID(model.getPage_id()));
        post.setContent(model.getContent());
        post.setDate(dtf.format(model.getDate()));

        postDao.insertPost(post);
    }

    @Override
    public Post getByID(int id) {
        return postDao.getByID(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return postDao.getAll();
    }

    @Override
    public List<Post> getPagePosts(Page page) {
        return postDao.getPostsByPage(page);
    }

    @Override
    public List<Post> getAuthorPosts(User user) {
        return postDao.getPostsByAuthor(user);
    }

    @Override
    public String getJSONPagePosts(Page page, User user) {

        ObjectMapper mapper = new ObjectMapper();
        List<Post> postsFromDB = getPagePosts(page);
        List<SimplePost> posts = new ArrayList<>();

        if(user.getRole().equals(User.Role.ADMIN)){
            for (Post post: postsFromDB) {
                posts.add(new SimplePost(post));
            }
        }else{
            for (Post post: postsFromDB) {
                if(post.getAuthor().getId() == user.getId()){
                    posts.add(new SimplePost(post));
                }
            }
        }

        try {
            String response = mapper.writeValueAsString(posts);
            return response;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    class SimplePost {
        private String title;
        private String content;
        private int id;

        public SimplePost(String title, String content, int id) {
            this.title = title;
            this.content = content;
            this.id = id;
        }

        public SimplePost(Post post) {
            this.title = post.getTitle();
            this.content = post.getContent();
            this.id = post.getId();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
