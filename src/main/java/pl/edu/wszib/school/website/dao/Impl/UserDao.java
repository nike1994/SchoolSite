package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.school.website.dao.IUserDao;
import pl.edu.wszib.school.website.model.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDao implements IUserDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.User";

    @Override
    public Integer insertUser(User user) {
        System.out.println("usuchomiono dodawanie urzytkownika");
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        Integer id = null;
        try {
            tx=session.beginTransaction();
            id = (Integer)session.save(user);
            System.out.println("dodano urzytkownika");
            tx.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
        return id;
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
                System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
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
        Query<User> query = session.createQuery("FROM "+model+" WHERE role LIKE :role")
                            .setParameter("role",role);
        List<User> users = query.getResultList();
        session.close();
        return users;
    }
}
