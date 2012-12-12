package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@WebServlet(name = "basicInsert", urlPatterns = "/auth/basicInsert")
public class InsertSomeProductServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(InsertSomeProductServlet.class.getName());
    private EntityManagerFactory emf;

    @Override
    public void destroy() {
        emf.close();
    }

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("supcommerce-PU");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName("Product");
        product.setContent("Content");
        product.setPrice(new Random().nextFloat() * 100);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(product);
            tx.commit();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Unable to persist product", e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }
}
