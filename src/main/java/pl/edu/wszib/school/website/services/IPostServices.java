package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.PostModel;

import java.util.LinkedHashMap;
import java.util.List;

public interface IPostServices {
    int insertPost(Post post);
    boolean deletePost(int id);
    void updatePost(Post post);
    boolean createPost(PostModel model);
    boolean updatePost(PostModel model);
    int createComment(LinkedHashMap JSON, User user);

    Post getByID(int id);
    List<Post> getAllPosts();
    List<Post> getPagePosts(Page page);
    List<Post> getAuthorPosts(User user);
    String getJSONPagePosts(Page page, User user);
}
