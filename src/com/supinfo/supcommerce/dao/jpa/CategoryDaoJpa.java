package com.supinfo.supcommerce.dao.jpa;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.entity.Category;

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
public class CategoryDaoJpa implements CategoryDao {

    private static final Logger log = Logger.getLogger(CategoryDaoJpa.class.getName());
    private EntityManagerFactory emf;

    public CategoryDaoJpa(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Category category) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(category);
            tx.commit();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Category adding fail !", e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    @Override
    public Category getById(Long idCategory) {
        EntityManager em = emf.createEntityManager();
        return em.find(Category.class, idCategory);
    }

    @Override
    public List<Category> getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("findAllCategory");
        return query.getResultList();
    }
}
