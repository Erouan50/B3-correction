package com.supinfo.supcommerce.resources;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.entity.Category;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@Path("categories")
public class CategoryResources {

    private CategoryDao categoryDao;

    public CategoryResources() {
        categoryDao = DaoFactory.getCategoryDao();
    }

    @POST
    public Response addCategory(Category category) {
        categoryDao.save(category);
        String uri = "/" + category.getId();
        return Response.created(URI.create(uri)).build();
    }

    @GET
    @Path("{idCategory}")
    public Category getCategory(@PathParam("idCategory") Long id) {
        return categoryDao.getWithProduct(id);
    }

    @PUT
    public Category updateCategory(Category category) {
        categoryDao.updateCategory(category);
        return category;
    }
}
