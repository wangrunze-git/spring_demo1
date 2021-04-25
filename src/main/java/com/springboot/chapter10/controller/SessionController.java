package com.springboot.chapter10.controller;

import com.springboot.chapter8.pojo.User;
import com.springboot.chapter8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

//@SessionAttributes指定数据模型名称或属性类型，保存到Session中
@SessionAttributes(names = {"user"}, types = Long.class)
@Controller
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private UserService userService = null;

    @GetMapping("/test")
    //@SessionAttribute从HttpSession中取出数据，填充控制器方法参数
    public String test(Long id, Model model){
        //根据类型保存到Session中
        model.addAttribute("id_new", id);
        User user = userService.getUser(id);
        //根据名称保存到Session中
        model.addAttribute("user", user);
        return "session/test";
    }

    @GetMapping("/test1")
    //@SessionAttribute从HttpSession中取出数据，填充控制器方法参数
    public String test1(@SessionAttribute("id_new") Long id, Model model){
        //根据类型保存到Session中
        model.addAttribute("id_new", id);
        User user = userService.getUser(id);
        //根据名称保存到Session中
        model.addAttribute("user", user);
        return "session/test";
    }

}
