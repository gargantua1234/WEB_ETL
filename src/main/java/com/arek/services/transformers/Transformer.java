package com.arek.services.transformers;

import com.arek.models.Product;
import com.arek.models.RawData;

/**
 * Created by Arek on 15.01.2017.
 */
public interface Transformer {
     Product transform(RawData data);
}
