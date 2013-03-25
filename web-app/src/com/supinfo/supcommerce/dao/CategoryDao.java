package com.supinfo.supcommerce.dao;

import com.supinfo.supcommerce.entity.Category;

import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public interface CategoryDao {
    void save(Category category);

    Category getById(Long idCategory);

    List<Category> getAll();

    Category getWithProduct(Long id);

    void updateCategory(Category category);
}
