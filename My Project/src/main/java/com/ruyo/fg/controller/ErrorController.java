package com.ruyo.fg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("404")
    private String get404ErrorPage() {
        return "404";
    }

    @GetMapping("401")
    private String get401ErrorPage() {
        return "401";
    }

    @GetMapping("500")
    private String get500ErrorPage() {
        return "500";
    }
}
