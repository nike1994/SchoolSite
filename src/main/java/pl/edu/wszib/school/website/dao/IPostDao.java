package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.User;

import java.util.List;

public interface IPostDao {
    void insertPost(Post post);
    void removePost(Post post);
    void updatePost(Post post);

    Post getByID(int id);
    List<Post> getAll();
    List<Post> getPostsByPage(Page page);
    List<Post> getPostsByAuthor(User user);

}
