package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.school.website.dao.IPupilDao;
import pl.edu.wszib.school.website.model.Parent;
import pl.edu.wszib.school.website.model.Pupil;
import pl.edu.wszib.school.website.model.SchoolClass;

import javax.persistence.NoResultException;
import java.util.List;

public class PupilDao implements IPupilDao {


    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.Pupil";

    @Override
    public void insertPupil(Pupil pupil) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        try {
            tx=session.beginTransaction();
            session.save(pupil);
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
        Query<Pupil> query = session.createQuery("FROM "+model+" WHERE "+model+".sClass = "+sClass);
        List<Pupil> pupils = query.getResultList();
        session.close();
        return pupils;
    }

    @Override
    public List<Pupil> getPupilsByParent(Parent parent) {
        Session session = this.sessionFactory.openSession();
        Query<Pupil> query = session.createQuery("FROM "+model+" WHERE "+model+".parent = "+parent);
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
}
