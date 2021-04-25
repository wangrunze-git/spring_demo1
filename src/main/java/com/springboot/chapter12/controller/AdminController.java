package com.springboot.chapter12.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("page1")
    public String page1(){
        return "admin_page1";
    }

    @RequestMapping("page2")
    public String page2(){
        return "admin_page2";
    }

}
