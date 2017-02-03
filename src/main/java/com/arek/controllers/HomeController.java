package com.arek.controllers;

import com.arek.models.Comment;
import com.arek.models.Product;
import com.arek.models.RawData;
import com.arek.models.Remark;
import com.arek.services.loaders.Loader;
import com.arek.services.extractors.Extractor;
import com.arek.services.transformers.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Extractor extractor;

    @Autowired
    private Transformer transformer;

    @Autowired
    private Loader loader;


    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model){
        return "home";
    }


    //ONLY FOR TESTING
    //#######################################################################################
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

    @RequestMapping("/test")
    public String testMethod(ModelMap model){
        Remark remark = new Remark();
        remark.setName("remark1");
        remark.setValue("value1");

        Comment comment = new Comment();
        comment.setUnhelpful(1);
        comment.setUnhelpful(1);
        comment.setCons(Arrays.asList("con1", "con2"));
        comment.setPros(Arrays.asList("pros1", "pros2"));
        comment.setAuthor("author1");
        comment.setContent("content1");
        comment.setDate("date1");
        comment.setId(1111111);
        comment.setRate(111.1f);
        comment.setRecommended(true);

        Comment comment2 = new Comment();
        comment2.setUnhelpful(2);
        comment2.setUnhelpful(2);
        comment2.setCons(Arrays.asList("con21", "con22"));
        comment2.setPros(Arrays.asList("pros21", "pros22"));
        comment2.setAuthor("author2");
        comment2.setContent("content2");
        comment2.setDate("date2");
        comment2.setId(2222222);
        comment2.setRate(222.2f);
        comment2.setRecommended(true);

        Comment comment3 = new Comment();
        comment3.setUnhelpful(3);
        comment3.setUnhelpful(3);
        comment3.setCons(Arrays.asList("con31", "con32"));
        comment3.setPros(Arrays.asList("pros31", "pros32"));
        comment3.setAuthor("author3");
        comment3.setContent("content3");
        comment3.setDate("date3");
        comment3.setId(3333333);
        comment3.setRate(333.3f);
        comment3.setRecommended(true);


        Product product = new Product();
        product.setModel("model1");
        product.setRemarks(Collections.singletonList(remark));
        product.setType("type1");
        product.setBrand("brand1");
        product.setId(1111);
        product.setComments(Arrays.asList(comment, comment2));


        loader.load(product);

        return "home";
    }

    //################################################################################
}
