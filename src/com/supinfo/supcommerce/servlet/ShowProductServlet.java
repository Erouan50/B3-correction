package com.supinfo.supcommerce.servlet;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class ShowProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        SupProduct product = SupProductDao.findProductById(Long.parseLong(id));
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Show product</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Show product</h1>");
        out.println("<p>Id : " + product.getId() +
                "\nName: " + product.getName() +
                "\nContent: " + product.getContent() +
                "\nPrice: " + product.getPrice() + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
