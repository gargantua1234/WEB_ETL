package com.arek.services.transformers;

import com.arek.objects.Comment;
import com.arek.objects.Product;
import com.arek.objects.RawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    //// TODO: 16.01.2017 delete them if wont be necessary  
    public void setProductTransformer(ProductTransformer prTrans){
        this.productTransformer = prTrans;
    }

    public void setCommentTransformer(CommentTransformer comTrans){
        this.commentTransformer = comTrans;
    }

}
