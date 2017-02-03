package com.arek.services.transformers;

import com.arek.models.Comment;
import com.arek.models.Product;
import com.arek.models.RawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTransformer implements Transformer {

    @Autowired
    private ProductTransformer productTransformer;
    @Autowired
    private CommentTransformer commentTransformer;



    @Override
    public Product transform(RawData data) {
        Product product ;
        List<Comment> comments;

        product = this.productTransformer.transformToProduct(data.getNext(), data.getProductId());
        data.reset();
        comments = this.commentTransformer.transformToComments(data);
        product.setComments(comments);

        return product;
    }

}
