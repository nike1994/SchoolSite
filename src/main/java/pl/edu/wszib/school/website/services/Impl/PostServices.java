package pl.edu.wszib.school.website.services.Impl;

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
}
