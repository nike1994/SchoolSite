package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.school.website.dao.IParentDao;
import pl.edu.wszib.school.website.model.Parent;
import pl.edu.wszib.school.website.model.SchoolSubjects;

import javax.persistence.NoResultException;
import java.util.List;

public class ParentDao implements IParentDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.Parent";


    @Override
    public void insertParent(Parent parent) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        try {
            tx=session.beginTransaction();
            session.save(parent);
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
    public void removeParent(Parent parent) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(parent != null){
            try{
                tx = session.beginTransaction();
                session.delete(parent);
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
    public void updateParent(Parent parent) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(parent != null) {
            try {
                tx = session.beginTransaction();
                session.update(parent);
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
    public Parent getByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Parent> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        Parent parent = null;
        try{
            parent= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono przedmiotu");
            return null;
        }finally {
            session.close();
        }
        return parent;
    }

    @Override
    public List<Parent> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Parent> query = session.createQuery("FROM "+model);
        List<Parent> parents = query.getResultList();
        session.close();
        return parents;
    }
}
