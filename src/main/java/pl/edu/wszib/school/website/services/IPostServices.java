package pl.edu.wszib.school.website.services;

import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.User;

import java.util.List;

public interface IPostServices {
    void insertPost(Post post);
    void deletePost(Post post);
    void updatePost(Post post);

    Post getByID(int id);
    List<Post> getAllPosts();
    List<Post> getPagePosts(Page page);
    List<Post> getAuthorPosts(User user);
}
