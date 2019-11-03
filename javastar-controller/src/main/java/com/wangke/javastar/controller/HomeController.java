package com.wangke.javastar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping(value = "/getData")
    public String getData() {
        return "this is a test!";
    }
}
