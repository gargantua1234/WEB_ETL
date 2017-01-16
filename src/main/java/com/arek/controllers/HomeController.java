package com.arek.controllers;

import com.arek.objects.Product;
import com.arek.objects.RawData;
import com.arek.services.extractors.Extractor;
import com.arek.services.transformers.Transformer;
import com.arek.validator.CodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Extractor extractor;
    @Autowired
    private Transformer transformer;

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model){
        return "home";
    }

    
    
    //// TODO: 16.01.2017 only for testing 
    @RequestMapping(method = RequestMethod.POST)
    public String testShowData(@RequestParam("productCode") String productCode,
                               ModelMap model){

        RawData rawData;
        Product product;
        try {
            rawData = extractor.extract(productCode);
            product = transformer.transform(rawData);
            model.addAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "show";
    }
}
