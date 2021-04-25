package com.springboot.chapter10.controller;

import com.springboot.chapter8.pojo.User;
import com.springboot.chapter8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @Autowired
    UserService userService = null;

    //显示用户
    /*@GetMapping("/show")
    public String showUser(Long id, Model model){
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "data/user";
    }*/

    //参数user直接从数据模型RedirectAttributes对象中取出
    @GetMapping("/showUser")
    public String showUser(User user, Model model){
        System.out.println(user.getId());
        return "data/user";
    }

    //使用字符串指定跳转
    @GetMapping("/redirect1")
    public String redirect1(String userName, String note, RedirectAttributes ra){
        User user = new User();
        user.setNote("note_1");
        user.setUserName("user_name_1");
        //保存需要传递给重定向的对象
        ra.addFlashAttribute("user", user);
        return "redirect:/redirect/showUser";
    }

    //使用模型和视图指定跳转
    @GetMapping("/redirect2")
    public ModelAndView redirect2(String userName, String note, RedirectAttributes ra){
        User user = new User();
        user.setNote(note);
        user.setUserName(userName);
        ra.addFlashAttribute("user", user);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/redirect/show");
        return mv;
    }

}
