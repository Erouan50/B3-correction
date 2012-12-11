package com.supinfo.supcommerce.servlet;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@WebServlet(name = "addProduct", urlPatterns = "/auth/addProduct")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupProduct product = new SupProduct();
        product.setName(req.getParameter("name"));
        product.setContent(req.getParameter("content"));
        product.setPrice(Float.parseFloat(req.getParameter("price")));
        SupProductDao.addProduct(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/showProduct?id=" + product.getId());
    }
}
