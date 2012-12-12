package com.supinfo.supcommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@Entity
@Table(name = "PRODUCTS")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "SELECT product FROM Product AS product"),
        @NamedQuery(name = "findProductsWithPriceRaiseOf", query = "SELECT product FROM Product AS product WHERE product.price < :max")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String content;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
