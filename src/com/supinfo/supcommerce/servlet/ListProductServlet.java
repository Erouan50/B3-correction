package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@WebServlet(name = "listProducts", urlPatterns = "/listProducts")
public class ListProductServlet extends HttpServlet {

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
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createNamedQuery("findAllProducts");
        List<Product> products = query.getResultList();
        req.setAttribute("products", products);
        RequestDispatcher rd = req.getRequestDispatcher("/listProducts.jsp");
        rd.forward(req, resp);
    }
}
