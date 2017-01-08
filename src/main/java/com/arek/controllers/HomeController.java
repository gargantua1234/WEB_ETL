package com.arek.controllers;

import com.arek.validator.CodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CodeValidator codeValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap model){
        model.addAttribute("message", "Main page for web etl");
        model.addAttribute("value", codeValidator.validateProductCode("12312312"));
        return "home";
    }
}
