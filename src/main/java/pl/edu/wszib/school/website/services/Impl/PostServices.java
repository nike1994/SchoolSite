package pl.edu.wszib.school.website.services.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.school.website.dao.ICommentDao;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.dao.IPostDao;
import pl.edu.wszib.school.website.model.Comment;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.PostModel;
import pl.edu.wszib.school.website.services.IPostServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PostServices implements IPostServices {
    @Autowired
    IPostDao postDao;

    @Autowired
    IPageDao pageDao;

    @Autowired
    ICommentDao commentDao;

    @Override
    public int insertPost(Post post) {
        return postDao.insertPost(post);
    }

    @Override
    public boolean deletePost( int id) {
        Post post = postDao.getByID(id);
        if (post == null) return false;
        postDao.removePost(post);
        return true;
    }

    @Override
    public void updatePost(Post post) {
        postDao.updatePost(post);
    }

    @Override
    public boolean updatePost(PostModel model) {
        Post post = postDao.getByID(model.getPost_id());
        if (post == null || model.getTitle().isEmpty() || model.getContent().isEmpty()) return false;
        post.setTitle(model.getTitle());
        post.setContent(model.getContent());
        postDao.updatePost(post);
        return true;
    }

    @Override
    public boolean createPost(PostModel model) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");


        Post post = new Post();
        post.setTitle(model.getTitle());
        post.setAuthor(model.getAuthor());
        Page page = pageDao.getByID(model.getPage_id());
        if (page == null) return false;

        post.setPage(page);
        post.setContent(model.getContent());
        post.setDate(dtf.format(model.getDate()));

        postDao.insertPost(post);
        return true;
    }

    @Override
    public int createComment(LinkedHashMap JSON, User user) {
        if(JSON != null){
            if(JSON.isEmpty()){
                return 0;
            }else {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                LocalDateTime date = LocalDateTime.now();

                Comment comment = new Comment();
                comment.setPost(postDao.getByID(Integer.parseInt((String) JSON.get("id"))));
                comment.setContent((String) JSON.get("content"));

                comment.setDate(dtf.format(date));
                comment.setAuthor(user);
                return commentDao.createComment(comment);
            }
        }
        return 0;
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
            return mapper.writeValueAsString(posts);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    static class SimplePost {
        private String title;
        private String content;
        private int id;

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
