package com.arek.services.loaders;

import com.arek.models.Comment;
import com.arek.models.Product;
import com.arek.services.persistence.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 03.02.2017.
 */
@Service
public class DatabaseLoader implements Loader {

    @Autowired
    private ProductService productService;

    @Override
    public void load(Product product) {
        saveOrUpdate(product);
    }

    private void saveOrUpdate(Product product) {
        Product equivalent = getEquivalentFromDatabase(product);
        if(equivalent != null){
            updateInDatabase(product, equivalent);

        }
        else
            saveNewProductInDatabase(product);
    }

    private Product getEquivalentFromDatabase(Product product){
        int productId = product.getId();
        return productService.findByIdProductWithComments(productId);
    }

    private void updateInDatabase(Product product, Product equivalent) {
        List<Comment> toDelete = findCollectionAMinusB(equivalent.getComments(),
                product.getComments());
        List<Comment> toAdd = findCollectionAMinusB(product.getComments(),
                equivalent.getComments());

        equivalent.getComments()
                .removeAll(toDelete);

        equivalent.getComments()
                .addAll(toAdd);

        productService.updateProduct(equivalent);
    }

    private List<Comment> findCollectionAMinusB(List<Comment> a, List<Comment> b){
        List<Comment> result = new ArrayList<>();
        result.addAll(a);
        result.removeAll(b);

        return result;
    }


    private void saveNewProductInDatabase(Product product) {
        productService.saveProduct(product);
    }

}
