package com.arek.services.transformers;

import com.arek.models.Comment;
import com.arek.models.RawData;

import java.util.List;

/**
 * Created by Arek on 16.01.2017.
 */
public interface CommentTransformer {
    List<Comment> transformToComments(RawData rawData);
}
