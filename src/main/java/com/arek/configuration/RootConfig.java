package com.arek.configuration;

import com.arek.validator.CodeValidator;
import com.arek.validator.SimpleCodeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Arek on 08.01.2017.
 */
@Configuration
@ComponentScan({"com.arek.services"})
public class RootConfig {

    @Bean
    public CodeValidator codeValidator(){
        return new SimpleCodeValidator();
    }

}
