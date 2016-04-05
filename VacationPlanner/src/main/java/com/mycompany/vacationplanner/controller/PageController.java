package com.mycompany.vacationplanner.controller;

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

    @RequestMapping("/calendar_periods_page")
    public String getCalendarPeriodsPage() {
        return "calendar_periods_page";
    }

}
