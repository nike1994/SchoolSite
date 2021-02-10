package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.school.website.dao.ICommentDao;
import pl.edu.wszib.school.website.model.Comment;
import pl.edu.wszib.school.website.model.Post;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CommentDao implements ICommentDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.Comment";


    @Override
    public int createComment(Comment comment) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        Integer id = null;
        try {
            tx=session.beginTransaction();
            id = (Integer)session.save(comment);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            return 0;
        }finally {
            session.close();
        }
        return id;
    }

    @Override
    public void updateComment(Comment comment) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(comment != null) {
            try {
                tx = session.beginTransaction();
                session.update(comment);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            } finally {
                session.close();
            }
        }
    }

    @Override
    public void removeComment(Comment comment) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(comment != null){
            try{
                tx = session.beginTransaction();
                session.delete(comment);
                tx.commit();
            }catch (Exception e){
                if(tx != null){
                    tx.rollback();
                }
            }finally {
                session.close();
            }

        }
    }

    @Override
    public List<Comment> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Comment> query = session.createQuery("FROM "+model);
        List<Comment> comments = query.getResultList();
        session.close();
        return comments;
    }

    @Override
    public List<Comment> getCommentsByPost(Post post) {
        Session session = this.sessionFactory.openSession();
        Query<Comment> query = session.createQuery("FROM "+model+" WHERE post_id = :post")
                .setParameter("post", post.getId());
        List<Comment> comments = query.getResultList();
        session.close();
        return comments;
    }

    @Override
    public Comment getByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Comment> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        Comment comment = null;
        try{
            comment= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono komentarza");
            return null;
        }finally {
            session.close();
        }
        return comment;
    }
}
