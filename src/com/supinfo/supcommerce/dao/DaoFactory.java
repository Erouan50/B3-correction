package com.supinfo.supcommerce.dao;

import com.supinfo.supcommerce.dao.jpa.CategoryDaoJpa;
import com.supinfo.supcommerce.dao.jpa.ProductDaoJpa;
import com.supinfo.supcommerce.utils.PersistenceManager;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class DaoFactory {

    public static CategoryDao getCategoryDao() {
        return new CategoryDaoJpa(PersistenceManager.getEntityManagerFactory());
    }

    public static ProductDao getProductDao() {
        return new ProductDaoJpa(PersistenceManager.getEntityManagerFactory());
    }
}
