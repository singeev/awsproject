package com.awsproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by singeev on 21/11/2017.
 */
@Controller
public class LoginController {

    public static final String LOGIN_VIEW_NAME = "user/login";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(){
        return LOGIN_VIEW_NAME;
    }
}
