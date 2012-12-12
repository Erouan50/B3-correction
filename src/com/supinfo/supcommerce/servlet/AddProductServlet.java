package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.entity.Category;
import com.supinfo.supcommerce.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@WebServlet(name = "addProduct", urlPatterns = "/auth/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddProductServlet.class.getName());

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("findAllCategory");
        List<Category> categories = query.getResultList();
        req.setAttribute("categories", categories);
        RequestDispatcher rd = req.getRequestDispatcher("/auth/addProduct.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter("name"));
        product.setContent(req.getParameter("content"));
        product.setPrice(Float.parseFloat(req.getParameter("price")));

        EntityManager em = emf.createEntityManager();
        Long idCategory = Long.valueOf(req.getParameter("idCategory"));
        Category category = em.find(Category.class, idCategory);
        product.setCategory(category);

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
        resp.sendRedirect(getServletContext().getContextPath() + "/showProduct?id=" + product.getId());
    }
}
