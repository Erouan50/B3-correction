package com.supinfo.supcommerce.resources;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Product;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@Path("products")
public class ProductResources {

    private ProductDao productDao;

    public ProductResources() {
        productDao = DaoFactory.getProductDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Product> getAllProductsInXml() {
        return productDao.getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProductsInJson() {
        return productDao.getAll();
    }

    @GET
    @Path("{idProduct}")
    @Produces(MediaType.APPLICATION_XML)
    public Product getProductInXml(@PathParam("idProduct") Long id) {
        return productDao.getById(id);
    }

    @GET
    @Path("{idProduct}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductInJson(@PathParam("idProduct") Long id) {
        return productDao.getById(id);
    }

    @DELETE
    @Path("{idProduct}")
    public void removeProduct(@PathParam("idProduct") Long id) {
        productDao.delete(productDao.getById(id));
    }
}
