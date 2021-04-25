package com.springboot.chapter12.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anonymous")
public class AnonymousController {

    @RequestMapping("/page1")
    public String page(){
        return "anonymous_page1";
    }
}
