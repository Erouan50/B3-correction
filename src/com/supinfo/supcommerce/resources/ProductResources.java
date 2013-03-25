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
import java.util.Iterator;
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
    public String getAllProductsInXml() {
        List<Product> products = productDao.getAll();
        StringBuilder builder = new StringBuilder();
        builder.append("<products>").append(System.lineSeparator());
        for (Product product : products) {
            parseProductInXml(builder, product);
        }
        builder.append("</products>");
        return builder.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllProductsInJson() {
        List<Product> products = productDao.getAll();
        StringBuilder builder = new StringBuilder();
        builder.append("{").append(System.lineSeparator());
        builder.append("\t\"products\": [").append(System.lineSeparator());
        Iterator<Product> productIt = products.iterator();
        while (productIt.hasNext()) {
            Product product = productIt.next();
            parseProductInJson(builder, product);
            if (productIt.hasNext()) {
                builder.append(",");
            }
            builder.append(System.lineSeparator());
        }
        builder.append("\t]").append(System.lineSeparator());
        builder.append("}");
        return builder.toString();
    }

    private void parseProductInJson(StringBuilder builder, Product product) {
        builder.append("\t\t{").append(System.lineSeparator());
        builder.append("\t\t\t\"id\":\"").append(product.getId()).append("\",").append(System.lineSeparator());
        builder.append("\t\t\t\"name\":\"").append(product.getName()).append("\",").append(System.lineSeparator());
        builder.append("\t\t\t\"description\":\"").append(product.getContent()).append("\",").append(System.lineSeparator());
        builder.append("\t\t\t\"price\":\"").append(product.getPrice()).append("\"").append(System.lineSeparator());
        builder.append("\t\t}");
    }

    @GET
    @Path("{idProduct}")
    @Produces(MediaType.APPLICATION_XML)
    public String getProductInXml(@PathParam("idProduct") Long id) {
        Product product = productDao.getById(id);
        StringBuilder builder = new StringBuilder();
        parseProductInXml(builder, product);
        return builder.toString();
    }

    @GET
    @Path("{idProduct}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProductInJson(@PathParam("idProduct") Long id) {
        Product product = productDao.getById(id);
        StringBuilder builder = new StringBuilder();
        parseProductInJson(builder, product);
        return builder.toString();
    }

    @DELETE
    @Path("{idProduct}")
    public void removeProduct(@PathParam("idProduct") Long id) {
        productDao.delete(productDao.getById(id));
    }

    private void parseProductInXml(StringBuilder builder, Product product) {
        builder.append("\t<product>").append(System.lineSeparator());
        builder.append("\t\t<id>").append(product.getId()).append("</id>")
                .append(System.lineSeparator());
        builder.append("\t\t<name>").append(product.getName())
                .append("</name>").append(System.lineSeparator());
        builder.append("\t\t<content>").append(product.getContent())
                .append("</content>").append(System.lineSeparator());
        builder.append("\t\t<price>").append(product.getPrice())
                .append("</price>").append(System.lineSeparator());
        builder.append("\t\t<category>").append(product.getCategory().getName())
                .append("</category>").append(System.lineSeparator());
        builder.append("\t</product>").append(System.lineSeparator());
    }
}
