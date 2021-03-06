package com.springboot.chapter10.controller.advice.test;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/advice")
public class AdviceController {

    @GetMapping("/test")
    //因为日期格式被控制器通知限定，所以无法再给出
    public String test(Date date, ModelMap modelMap){
        //从数据模型中获取数据
        System.out.println(modelMap.get("project_name"));
        //打印日期参数
        //抛出异常，这样流转到控制器异常通知
        throw new RuntimeException("异常了，跳转到控制器通知的异常信息里");

    }
}
