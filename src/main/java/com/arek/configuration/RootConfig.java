package com.arek.configuration;

import com.arek.services.transformers.CommentTransformer;
import com.arek.services.transformers.CommentTransformerImpl;
import com.arek.services.transformers.ProductTransformer;
import com.arek.services.transformers.ProductTransformerImpl;
import com.arek.validator.CodeValidator;
import com.arek.validator.SimpleCodeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Arek on 08.01.2017.
 */
@Configuration
@ComponentScan(basePackages = "com.arek")
public class RootConfig {

    @Bean
    public CodeValidator codeValidator(){
        return new SimpleCodeValidator();
    }

    @Bean
    public ProductTransformer productTransformer(){
        return new ProductTransformerImpl();
    }

    @Bean
    public CommentTransformer commentTransformer(){
        return new CommentTransformerImpl();
    }
}
