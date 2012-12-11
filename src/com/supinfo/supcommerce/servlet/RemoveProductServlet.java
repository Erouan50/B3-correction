package com.supinfo.supcommerce.servlet;

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
@WebServlet(name = "removeProduct", urlPatterns = "/auth/removeProduct")
public class RemoveProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        SupProductDao.removeProduct(Long.parseLong(id));
        resp.sendRedirect(getServletContext().getContextPath() + "/listProducts.jsp");
    }
}
