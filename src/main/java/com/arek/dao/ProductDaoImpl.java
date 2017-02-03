package com.arek.dao;

import com.arek.models.Comment;
import com.arek.models.Product;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Arek on 31.01.2017.
 */
@Repository
public class ProductDaoImpl extends AbstractDao implements ProductDao {


    @Override
    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        Criteria criteria = getSession().createCriteria(Product.class);
        return (List<Product>) criteria.list();
    }

    @Override
    public void updateProduct(Product product) {
        getSession().update(product);
    }

    @Override
    public void saveProduct(Product product) {
        persist(product);
    }

    @Override
    public Product findProductByIdWithComments(int id) {
        Product product;
        //// TODO: 04.02.2017 sprobowac tu wywolac metodde findproductbyid
        product = getSession().get(Product.class, id);
        if(product != null)
            Hibernate.initialize(product.getComments());
         return product;
    }

    @Override
    public Product findProductById(int id) {
        return getSession().get(Product.class, id);
    }

    @Override
    public void deleteProductById(int id) {
        Product tmp = findProductById(id);
        getSession().delete(tmp);
    }

    @Override
    public Product findProductByIdFull(int id) {
        Product product ;
        product = getSession().get(Product.class, id);
        if(product!=null){
            Hibernate.initialize(product.getComments());
            Hibernate.initialize(product.getRemarks());
            for(Comment comment: product.getComments()) {
                Hibernate.initialize(comment.getCons());
                Hibernate.initialize(comment.getPros());
            }
        }
        return product;
    }

}
