package com.supinfo.geekquote.dao.jpa;

import com.supinfo.geekquote.dao.QuoteDao;
import com.supinfo.geekquote.model.Quote;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteJpaDao implements QuoteDao {

    private EntityManagerFactory factory;

    public QuoteJpaDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void insertQuote(Quote quote) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(quote);
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    @Override
    public void updateQuote(Quote quote) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(quote);
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Quote> getAllQuotes() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createNamedQuery("findAllQuotes");
        return query.getResultList();
    }
}
