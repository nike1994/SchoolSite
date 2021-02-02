package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.school.website.dao.IGradeDao;
import pl.edu.wszib.school.website.model.Grade;
import pl.edu.wszib.school.website.model.Pupil;
import pl.edu.wszib.school.website.model.SchoolSubjects;

import javax.persistence.NoResultException;
import javax.security.auth.Subject;
import java.util.List;

public class GradeDao implements IGradeDao {
    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.Grade";



    @Override
    public void insertGrade(Grade grade) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        try {
            tx=session.beginTransaction();
            session.save(grade);
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
    public void updateGrade(Grade grade) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(grade != null){
            try{
                tx = session.beginTransaction();
                session.delete(grade);
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
    public void removeGrade(Grade grade) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(grade != null){
            try{
                tx = session.beginTransaction();
                session.delete(grade);
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
    public Grade getByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Grade> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        Grade grade = null;
        try{
            grade= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono oceny");
            return null;
        }finally {
            session.close();
        }
        return grade;
    }

    @Override
    public List<Grade> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Grade> query = session.createQuery("FROM "+model);
        List<Grade> grades = query.getResultList();
        session.close();
        return grades;
    }

    @Override
    public List<Grade> getBySubject(Subject subject) {
        Session session = this.sessionFactory.openSession();
        Query<Grade> query = session.createQuery("FROM "+model+" WHERE "+model+".subject = "+subject);
        List<Grade> grades = query.getResultList();
        session.close();
        return grades;
    }

    @Override
    public List<Grade> getByPupil(Pupil pupil) {
        Session session = this.sessionFactory.openSession();
        Query<Grade> query = session.createQuery("FROM "+model+" WHERE "+model+".pupil = "+pupil);
        List<Grade> grades = query.getResultList();
        session.close();
        return grades;
    }
}
