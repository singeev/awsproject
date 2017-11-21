package com.awsproject.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by singeev on 21/11/2017.
 */
@Controller
public class PayloadController {
    public static final String PAYLOAD_VIEW_NAME = "payload/payload";

    @RequestMapping("/payload")
    public String showPayload() {
        return PAYLOAD_VIEW_NAME;
    }
}
