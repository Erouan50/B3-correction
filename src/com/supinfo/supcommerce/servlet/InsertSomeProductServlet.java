package com.supinfo.supcommerce.servlet;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class InsertSomeProductServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupProduct product = new SupProduct();
        product.setName("Product");
        product.setContent("Content");
        product.setPrice(new Random().nextFloat() * 100);
        SupProductDao.addProduct(product);
    }
}
