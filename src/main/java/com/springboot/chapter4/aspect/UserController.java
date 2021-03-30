package com.springboot.chapter4.aspect;

import com.springboot.chapter3.pojo.User;
import com.springboot.chapter4.aspect.server.UserService;
import com.springboot.chapter4.aspect.server.impl.UserServiceImpl;
import com.springboot.chapter4.aspect.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//定义控制器
@Controller
@RequestMapping("/user")
public class UserController {

    //注入用户服务
    @Autowired
    private UserService userService = null;

    //定义请求
    @RequestMapping("/print")
    //转换为JSON
    @ResponseBody
    public User PrintUser(Long id, String userName, String note){
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        //强制转换
        UserValidator userValidator = (UserValidator)userService;
        //验证用户是否为空
        if (userValidator.validate(user)){
            userService.printUser(user);//若user==null,则执行afterThrowing方法
        }
        return user;
    }

    @RequestMapping("/manyAspects")
    @ResponseBody
    public String manyAspects(){
        userService.manyAspects();
        return "manyAspects";
    }

}
