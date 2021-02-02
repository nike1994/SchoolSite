package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.school.website.dao.IUserDao;
import pl.edu.wszib.school.website.model.User;

import javax.persistence.NoResultException;
import java.util.List;

public class UserDao implements IUserDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.User";

    @Override
    public void insertUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        try {
            tx=session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void removeUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(user != null){
            try{
                tx = session.beginTransaction();
                session.delete(user);
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
    public void updateUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(user != null) {
            try {
                tx = session.beginTransaction();
                session.update(user);
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
    public List<User> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM "+model);
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public User getUserByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        User user = null;
        try{
            user= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono użytkownika");
            return null;
        }finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> getUserByRole(String role) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM "+model+" WHERE role LIKE "+role);
        List<User> users = query.getResultList();
        session.close();
        return users;
    }
}
