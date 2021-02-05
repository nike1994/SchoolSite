package pl.edu.wszib.school.website.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.school.website.dao.IWebsiteInformationsDao;
import pl.edu.wszib.school.website.model.User;
import pl.edu.wszib.school.website.model.WebsiteInformations;

import javax.persistence.NoResultException;

@Repository
public class WebsiteInformationsDao implements IWebsiteInformationsDao {

    @Autowired
    SessionFactory sessionFactory;

    String model = "pl.edu.wszib.school.website.model.WebsiteInformations";

    @Override
    public void createInformations(WebsiteInformations websiteInformations) {
        Session session = this.sessionFactory.openSession();
        Transaction tx= null;
        Integer id = null;
        try {
            tx=session.beginTransaction();
            id = (Integer)session.save(websiteInformations);
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

    @Override
    public void updateInfomations(WebsiteInformations websiteInformations) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        if(websiteInformations != null) {
            try {
                tx = session.beginTransaction();
                session.update(websiteInformations);
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
    public WebsiteInformations getInformations() {
        Session session = this.sessionFactory.openSession();
        Query<WebsiteInformations> query = session.createQuery("FROM "+this.model+" WHERE id = :id")
                .setParameter("id", 1);
        WebsiteInformations info = null;
        try{
            info= query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono informacji");
            return null;
        }finally {
            session.close();
        }
        return info;
    }
}
