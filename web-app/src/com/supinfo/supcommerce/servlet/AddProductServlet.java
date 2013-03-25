package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Category;
import com.supinfo.supcommerce.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@WebServlet(name = "addProduct", urlPatterns = "/auth/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        List<Category> categories = categoryDao.getAll();
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

        CategoryDao categoryDao = DaoFactory.getCategoryDao();
        Category category = categoryDao.getById(Long.valueOf(req.getParameter("idCategory")));
        product.setCategory(category);

        ProductDao productDao = DaoFactory.getProductDao();
        productDao.save(product);

        resp.sendRedirect(getServletContext().getContextPath() + "/showProduct?id=" + product.getId());
    }
}
