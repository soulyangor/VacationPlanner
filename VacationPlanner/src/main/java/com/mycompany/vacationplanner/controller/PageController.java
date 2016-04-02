package com.mycompany.vacationplanner.controller;

import com.mycompany.vacationplanner.model.Subdivision;
import com.mycompany.vacationplanner.service.SubdivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String getStartPage() {
        return "start";
    }

    @RequestMapping("/test")
    public String getTestPage() {
        return "test";
    }

    @RequestMapping("/posts_page")
    public String getPostsPage() {
        return "posts_page";
    }

}
