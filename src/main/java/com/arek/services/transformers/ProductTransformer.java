package com.arek.services.transformers;

import com.arek.objects.Product;
import org.jsoup.nodes.Element;

/**
 * Created by Arek on 16.01.2017.
 */
public interface ProductTransformer {
    Product transformToProduct(Element rawData, int productId);
}
