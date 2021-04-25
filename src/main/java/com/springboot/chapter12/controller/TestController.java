package com.springboot.chapter12.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("page1")
    public String page1(){
        return "test1";
    }

    @RequestMapping("page2")
    public String page2(){
        return "test2";
    }
}
