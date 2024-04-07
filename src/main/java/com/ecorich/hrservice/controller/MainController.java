package com.ecorich.hrservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * swagger에 redirect
     * @return
     */
    @GetMapping
    public String swagger() {
        return "redirect:/swagger-ui/index.html";
    }

}
