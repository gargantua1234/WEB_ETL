package com.arek.services.persistence;

import com.arek.dao.ProductDao;
import com.arek.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Arek on 31.01.2017.
 */

@Service("entityService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Override
    public List<Product> findAllProducts() {
        return dao.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        dao.updateProduct(product);
    }

    @Override
    public void saveProduct(Product product) {
        dao.saveProduct(product);
    }

    @Override
    public Product findProductByIdWithComments(int id) {
        return dao.findProductByIdWithComments(id);
    }

    @Override
    public Product findProductById(int id) {
        return dao.findProductById(id);
    }

    @Override
    public void deleteProductById(int id) {
        dao.deleteProductById(id);
    }

    @Override
    public Product findProductByIdFull(int id) {
        return dao.findProductByIdFull(id);
    }
}
