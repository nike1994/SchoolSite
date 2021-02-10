package pl.edu.wszib.school.website.dao;

import pl.edu.wszib.school.website.model.Comment;
import pl.edu.wszib.school.website.model.Post;

import java.util.List;

public interface ICommentDao {
    public int createComment(Comment comment);
    public void updateComment(Comment comment);
    public void removeComment(Comment comment);

    public List<Comment> getAll();
    public List<Comment> getCommentsByPost(Post post);
    public  Comment getByID(int id);
}
