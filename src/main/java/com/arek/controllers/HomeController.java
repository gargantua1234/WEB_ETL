package com.arek.controllers;

import com.arek.models.Product;
import com.arek.models.RawData;
import com.arek.services.extractors.Extractor;
import com.arek.services.loaders.Loader;
import com.arek.services.persistence.ProductService;
import com.arek.services.transformers.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private Extractor extractor;

    @Autowired
    private Transformer transformer;

    @Autowired
    private Loader loader;

    @Autowired
    private ProductService service;


    @RequestMapping("/")
    public String home(ModelMap model){
        return "home";
    }


    @RequestMapping("/products")
    public String showProducts(ModelMap model){
        List<Product> products = service.findAllProducts();
        model.addAttribute("products", products);
        return "showProducts";
    }

    @RequestMapping("/product/{id}/show")
    public String showProductData(ModelMap model, @PathVariable("id") int id){
        Product product = service.findProductByIdFull(id);
        model.addAttribute("product", product);
        return "show";
    }

    @RequestMapping("/product/{id}/delete")
    public ModelAndView deleteProduct(@PathVariable int id){
        service.deleteProductById(id);
        return new ModelAndView("redirect:/products");
    }

    @RequestMapping(value = "/",  method = RequestMethod.POST)
    public String testShowData(@RequestParam("productCode") String productCode,
                               ModelMap model){

        RawData rawData;
        Product product;
        try {
            rawData = extractor.extract(productCode);
            product = transformer.transform(rawData);
            loader.load(product);
            model.addAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "show";
    }

}
