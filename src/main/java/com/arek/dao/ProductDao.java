package com.arek.dao;

import com.arek.models.Product;

import java.util.List;

/**
 * Created by Arek on 31.01.2017.
 */
public interface ProductDao {

    List<Product> findAll();
    void updateProduct(Product product);
    void saveProduct(Product product);
    Product findProductByIdWithComments(int id);
    Product findProductById(int id);
    void deleteProductById(int id);
    Product findProductByIdFull(int id);
}
