package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@WebServlet(name = "showProduct", urlPatterns = "/showProduct")
public class ShowProductServlet extends HttpServlet {

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
        String id = req.getParameter("id");
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, Long.valueOf(id));
        req.setAttribute("product", product);
        RequestDispatcher rd = req.getRequestDispatcher("/showProduct.jsp");
        rd.forward(req, resp);
    }

}
