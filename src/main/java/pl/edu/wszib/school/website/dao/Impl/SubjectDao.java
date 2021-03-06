package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.school.website.dao.ISubjectDao;
import pl.edu.wszib.school.website.model.SchoolClass;
import pl.edu.wszib.school.website.model.SchoolSubjects;
import pl.edu.wszib.school.website.model.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class SubjectDao implements ISubjectDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.SchoolSubjects";


    @Override
    public Integer insertSubject(SchoolSubjects subjects) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        Integer id = null;
        try {
            tx=session.beginTransaction();
            id = (Integer)session.save(subjects);
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
    public void removeSubject(SchoolSubjects subjects) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(subjects != null){
            try{
                tx = session.beginTransaction();
                session.delete(subjects);
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
    public void updateSubject(SchoolSubjects subjects) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(subjects != null) {
            try {
                tx = session.beginTransaction();
                session.update(subjects);
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
    public SchoolSubjects getSubjectByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<SchoolSubjects> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        SchoolSubjects subjects = null;
        try{
            subjects= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono przedmiotu");
            return null;
        }finally {
            session.close();
        }
        return subjects;
    }

    @Override
    public List<SchoolSubjects> getAllSubjects() {
        Session session = this.sessionFactory.openSession();
        Query<SchoolSubjects> query = session.createQuery("FROM "+model);
        List<SchoolSubjects> subjects = query.getResultList();
        session.close();
        return subjects;
    }

    @Override
    public List<SchoolSubjects> getSubjectsByTeacher(User user) {
        Session session = this.sessionFactory.openSession();
        Query<SchoolSubjects> query = session.createQuery("FROM "+model+" WHERE teacher_id =:user")
                                            .setParameter("user",user.getId());
        List<SchoolSubjects> subjects = query.getResultList();
        session.close();
        return subjects;
    }

    @Override
    public List<SchoolSubjects> getSubjectsByClass(SchoolClass sClass) {
        Session session = this.sessionFactory.openSession();
        Query<SchoolSubjects> query = session.createQuery("FROM "+model+" WHERE sClass_id = :class")
                                        .setParameter("class",sClass.getId());
        List<SchoolSubjects> subjects = query.getResultList();
        session.close();
        return subjects;
    }
}
