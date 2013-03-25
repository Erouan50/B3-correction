package com.supinfo.supcommerce.dao.jpa;

import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class ProductDaoJpa implements ProductDao {

    private static final Logger log = Logger.getLogger(ProductDaoJpa.class.getName());
    private EntityManagerFactory emf;

    public ProductDaoJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Product product) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(product);
            tx.commit();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unable to persist product", e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    @Override
    public List<Product> getUnderPrice(Float max) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("findProductsWithPriceRaiseOf");
        query.setParameter("max", max);
        return query.getResultList();
    }

    @Override
    public List<Product> getAll() {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createNamedQuery("findAllProducts");
        return query.getResultList();
    }

    @Override
    public void delete(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.merge(product));
            tx.commit();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Unable to delete product", e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    @Override
    public Product getById(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Product.class, id);
    }
}
