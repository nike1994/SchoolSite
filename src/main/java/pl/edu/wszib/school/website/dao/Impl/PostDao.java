package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.school.website.dao.IPostDao;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.Post;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PostDao implements IPostDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.Post";



    @Override
    public Integer insertPost(Post post) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        Integer id = null;
        try {
            tx=session.beginTransaction();
            id = (Integer)session.save(post);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return id;
    }

    @Override
    public void removePost(Post post) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(post != null){
            try{
                tx = session.beginTransaction();
                session.delete(post);
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
    public void updatePost(Post post) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(post != null) {
            try {
                tx = session.beginTransaction();
                session.update(post);
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
    public Post getByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        Post post = null;
        try{
            post= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono postu");
            return null;
        }finally {
            session.close();
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM "+model);
        List<Post> posts = query.getResultList();
        session.close();
        return posts;
    }

    @Override
    public List<Post> getPostsByPage(Page page) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM "+model+" WHERE page_id = :page")
                                    .setParameter("page",page.getId());
        List<Post> posts = query.getResultList();
        session.close();
        return posts;
    }

    @Override
    public List<Post> getPostsByAuthor(User user) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM "+model+" WHERE author_id = :author")
                                    .setParameter("author", user.getId());
        List<Post> posts = query.getResultList();
        session.close();
        return posts;
    }
}
