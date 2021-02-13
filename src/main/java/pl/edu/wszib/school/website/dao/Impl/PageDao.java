package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.school.website.dao.IPageDao;
import pl.edu.wszib.school.website.model.Page;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PageDao implements IPageDao {

    @Autowired
    SessionFactory sessionFactory;

    private  String model = "pl.edu.wszib.school.website.model.Page";


    @Override
    public Integer insertPage(Page page) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        Integer id=null;
        try {
            tx=session.beginTransaction();
            id = (Integer)session.save(page);
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
    public void updatePage(Page page) {
        Transaction tx = null;
        if(page != null) {
            try (Session session = this.sessionFactory.openSession()) {
                tx = session.beginTransaction();
                session.update(page);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
    }

    @Override
    public void removePage(Page page) {
        Transaction tx = null;
        if(page != null){
            try (Session session = this.sessionFactory.openSession()) {
                tx = session.beginTransaction();
                session.delete(page);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            }

        }
    }

    @Override
    public Page getByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Page> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", id);
        Page page = null;
        try{
            page= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono strony");
            return null;
        }finally {
            session.close();
        }
        return page;
    }

    @Override
    public List<Page> getAllPages() {
        Session session = this.sessionFactory.openSession();
        Query<Page> query = session.createQuery("FROM "+model);
        List<Page> pages = query.getResultList();
        session.close();
        return pages;
    }

    @Override
    public Page getByTitle(String title) {
        Session session = this.sessionFactory.openSession();
        Query<Page> query = session.createQuery("FROM "+this.model+" WHERE title = :title")
                .setParameter("title", title);
        Page page = null;
        try{
            page= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono strony");
            return null;
        }finally {
            session.close();
        }
        return page;
    }
}
