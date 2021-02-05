package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.View.PostModel;

import java.util.List;

public interface IPostServices {
    int insertPost(Post post);
    void deletePost(Post post);
    void updatePost(Post post);
    void createPost(PostModel model);

    Post getByID(int id);
    List<Post> getAllPosts();
    List<Post> getPagePosts(Page page);
    List<Post> getAuthorPosts(User user);
}
