package com.awsproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by singeev on 20/11/2017.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndexPage(){
        return "index";
    }
}
