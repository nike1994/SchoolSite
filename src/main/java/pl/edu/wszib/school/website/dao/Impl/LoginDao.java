package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.school.website.dao.ILoginDao;
import pl.edu.wszib.school.website.model.Login;
import pl.edu.wszib.school.website.model.Page;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class LoginDao implements ILoginDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.Login";


//    @Override
//    public void insertLogin(Login login) {
//        Session session = this.sessionFactory.openSession();
//        Transaction tx= null;
//        try {
//            tx=session.beginTransaction();
//            session.save(login);
//            tx.commit();
//        }catch (Exception e){
//
//            System.out.println(e.getMessage());
//            if(tx != null){
//                tx.rollback();
//            }
//        }finally {
//            session.close();
//        }
//    }

    @Override
    public void updateLogin(Login login) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(login != null) {
            try {

                tx = session.beginTransaction();
                session.update(login);
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

//    @Override
//    public void removeLogin(Login login) {
//        Session session = this.sessionFactory.openSession();
//        Transaction tx = null;
//        if(login != null){
//            try{
//                tx = session.beginTransaction();
//                session.delete(login);
//                tx.commit();
//            }catch (Exception e){
//
//                System.out.println(e.getMessage());
//                if(tx != null){
//                    tx.rollback();
//                }
//            }finally {
//                session.close();
//            }
//
//        }
//    }

    @Override
    public Login getByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Login> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        Login login = null;
        try{
            login= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono loginu");
            return null;
        }finally {
            session.close();
        }
        return login;
    }

    @Override
    public Login getByUser(User user) {
        Session session = this.sessionFactory.openSession();
        Query<Login> query = session.createQuery("FROM "+model+" WHERE "+model+".user = "+user);
        Login login = null;
        try{
            login= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono loginu");
            return null;
        }finally {
            session.close();
        }
        return login;
    }

    @Override
    public Login getByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<Login> query = session.createQuery("FROM "+model+" WHERE login = :login");
        query.setParameter("login",login);
        Login result = null;
        try {
            result = query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono loginu");
        }
        session.close();
        return result;
    }
}
