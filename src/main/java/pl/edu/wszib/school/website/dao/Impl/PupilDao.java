package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.school.website.dao.IPupilDao;
import pl.edu.wszib.school.website.model.Parent;
import pl.edu.wszib.school.website.model.Pupil;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PupilDao implements IPupilDao {


    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.Pupil";

    @Override
    public Integer insertPupil(Pupil pupil) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        Integer id = null;
        try {
            tx=session.beginTransaction();
            id = (Integer)session.save(pupil);
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
    public void removePupil(Pupil pupil) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(pupil != null){
            try{
                tx = session.beginTransaction();
                session.delete(pupil);
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
    public void updatePupil(Pupil pupil) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(pupil != null) {
            try {
                tx = session.beginTransaction();
                session.update(pupil);
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
    public Pupil getPupilByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Pupil> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        Pupil pupil = null;
        try{
            pupil= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono użytkownika");
            return null;
        }finally {
            session.close();
        }
        return pupil;
    }

    @Override
    public List<Pupil> getPupilsByClass(SchoolClass sClass) {
        Session session = this.sessionFactory.openSession();
        Query<Pupil> query = session.createQuery("FROM "+model+" WHERE sClass_id = :class")
                                    .setParameter("class", sClass.getId());
        List<Pupil> pupils = query.getResultList();
        session.close();
        return pupils;
    }

    @Override
    public List<Pupil> getPupilsByParent(Parent parent) {
        Session session = this.sessionFactory.openSession();
        Query<Pupil> query = session.createQuery("FROM "+model+" WHERE parent_id = :parent")
                                    .setParameter("parent", parent.getId());
        List<Pupil> pupils = query.getResultList();
        session.close();
        return pupils;
    }

    @Override
    public List<Pupil> getAllPupil() {
        Session session = this.sessionFactory.openSession();
        Query<Pupil> query = session.createQuery("FROM "+model);
        List<Pupil> pupils = query.getResultList();
        session.close();
        return pupils;
    }

    @Override
    public Pupil getPupilByUser(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Pupil> query = session.createQuery("FROM "+this.model+" WHERE user_id = :id")
                .setParameter("id", userId);
        Pupil pupil = null;
        try{
            pupil= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono użytkownika");
            return null;
        }finally {
            session.close();
        }
        return pupil;
    }

    @Override
    public Pupil getPupilByUserID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Pupil> query = session.createQuery("FROM "+this.model+" WHERE user_id = :id")
                .setParameter("id", id);
        Pupil pupil = null;
        try{
            pupil= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono ucznia");
            return null;
        }finally {
            session.close();
        }
        return pupil;
    }
}
