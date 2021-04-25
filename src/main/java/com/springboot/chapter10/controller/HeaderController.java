package com.springboot.chapter10.controller;

import com.springboot.chapter8.pojo.User;
import com.springboot.chapter8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/header")
public class HeaderController {

    @Autowired
    UserService userService = null;

    @GetMapping("/header/page")
    public String headerPage(){
        return "header";
    }

    @PostMapping("/header/user")
    @ResponseBody
    //通过@RequestHeader接收请求头参数
    public User headerUser(@RequestHeader("id") Long id){
        User user = userService.getUser(id);
        return user;
    }

}
