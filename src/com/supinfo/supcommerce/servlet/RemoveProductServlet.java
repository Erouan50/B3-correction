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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@WebServlet(name = "removeProduct", urlPatterns = "/auth/removeProduct")
public class RemoveProductServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RemoveProductServlet.class.getName());

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product product = new Product();
        product.setId(Long.valueOf(id));
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
        resp.sendRedirect(getServletContext().getContextPath() + "/listProducts");
    }
}
