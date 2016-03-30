package com.mycompany.vacationplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String getStartPage() {
        System.out.println("ENTER");
        return "start";
    }
    
    @RequestMapping("/test")
    public String getTestPage() {
        return "test";
    }

}
