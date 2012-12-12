package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "addCategory", urlPatterns = "/auth/addCategory")
public class AddCategoryServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddCategoryServlet.class.getName());
    private EntityManagerFactory emf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/auth/addCategory.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category();
        category.setName(req.getParameter("name"));
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(category);
            tx.commit();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Category adding fail !", e);
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/listProducts");
    }

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("supcommerce-PU");
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
