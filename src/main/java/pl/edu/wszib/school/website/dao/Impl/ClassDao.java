package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.school.website.dao.IClassDao;
import pl.edu.wszib.school.website.model.Grade;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;

import javax.persistence.NoResultException;
import java.util.List;

public class ClassDao implements IClassDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.SchoolClass";


    @Override
    public void insertClass(SchoolClass sClass) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        try {
            tx=session.beginTransaction();
            session.save(sClass);
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
    public void updateClass(SchoolClass sClass) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(sClass != null){
            try{
                tx = session.beginTransaction();
                session.delete(sClass);
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
    public void removeClass(SchoolClass sClass) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(sClass != null){
            try{
                tx = session.beginTransaction();
                session.delete(sClass);
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
    public SchoolClass getClassByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<SchoolClass> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        SchoolClass sClass = null;
        try{
            sClass= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono klasy");
            return null;
        }finally {
            session.close();
        }
        return sClass;
    }

    @Override
    public SchoolClass getClassByUser(User user) {
        Session session = this.sessionFactory.openSession();
        Query<SchoolClass> query = session.createQuery("FROM "+model+"JOIN "+model+".user user WHERE  user.id = "+user.getId());
        SchoolClass sClass = null;
        try{
            sClass= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono klasy");
            return null;
        }finally {
            session.close();
        }
        return sClass;
    }
}
