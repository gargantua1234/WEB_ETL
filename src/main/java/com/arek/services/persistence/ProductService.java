package com.arek.services.persistence;

import com.arek.models.Product;

import java.util.List;

/**
 * Created by Arek on 31.01.2017.
 */

public interface ProductService {
    List<Product> findAllProducts();
    void updateProduct(Product product);
    void saveProduct(Product product);
    Product findByIdProductWithComments(int id);
}
