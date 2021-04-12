package com.springboot.chapter10.controller;

import com.springboot.chapter10.pojo.ValidatorPojo;
import com.springboot.chapter10.validator.UserValidator;
import com.springboot.chapter8.pojo.User;
import com.springboot.chapter8.service.UserService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/my")
@Controller
public class MyController {

    /**
     * 在无注解下获取参数，要求参数名称和HTTP请求参数名称一致
     * @param intVal
     * @param longVal
     * @param str
     * @return
     */
    @GetMapping("/no/annotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String str){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("str", str);
        return paramsMap;
    }

    /**
     * 通过注解@RequestParam获取参数
     * @param intVal -- 整数
     * @param longVal -- 长整形
     * @param strVal -- 字符串
     * @return 响应JSON数据集
     */
    @RequestMapping("/annotation")
    @ResponseBody
    public Map<String, Object> requestParam(@RequestParam("int_val") Integer intVal,
            @RequestParam("long_val") Long longVal,@RequestParam(value = "str_val", required = false) String strVal){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("intVal", intVal);
        paramsMap.put("longVal", longVal);
        paramsMap.put("strVal", strVal);
        return paramsMap;
    }

    @GetMapping("/requestArray")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArr, Long[] longArr, String[] strArr){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("intArr", intArr);
        paramsMap.put("longArr", longArr);
        paramsMap.put("strArr", strArr);
        return paramsMap;
    }

    /**
     * 打开请求页面
     * @return 字符串，指向页面
     */
    @GetMapping("/add")
    public String add(){
        return "user/add";
    }

    @PostMapping("/insert")
    @ResponseBody
    public User insert(@RequestBody User user){
        String string = user.toString();
        System.out.println(string);
        user.setId(11L);
        return user;
    }

    //{...}代表占位符，还可以配置参数名称
    @GetMapping("/{id}")
    @ResponseBody
    //@PathVariable通过名称获取参数
    public User get(@PathVariable("id") Long id){
        User user = new User();
        user.setId(id);
        user.setUserName("userName");
        return user;
    }

    //映射JSP页面
    @GetMapping("/format/form")
    public String showFormat(){
        return "/format/formatter";
    }

    //获取提交参数
    @PostMapping("/format/commit")
    @ResponseBody
    public Map<String, Object> format(
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date date,
            @NumberFormat(pattern = "#,###.##") Double number){
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("date", date);
        dataMap.put("number", number);
        return dataMap;
    }

    @GetMapping("/converter")
    @ResponseBody
    public User getUserByConverter(User user){
        return user;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> list(List<User> userList){
        return userList;
    }

    @GetMapping("/valid/page")
    public String validPage(){
        return "/validator/pojo";
    }

    /**
     * 解析验证参数错误
     * @param vp -- 需要验证的POJO，使用注解@Valid表示验证
     * @param errors -- 错误信息，它由Spring MVC通过验证POJO后自动填充
     * @return
     */
    @RequestMapping(value = "/valid/validate")
    @ResponseBody
    public Map<String, Object> validate(@Valid @RequestBody ValidatorPojo vp, Errors errors){
        HashMap<String, Object> errMap = new HashMap<>();
        //获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            String key = null;
            String msg = null;
            //字段错误
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                key = fe.getField();//获取错误验证字段名
            }else {
                //非字段错误
                key = oe.getObjectName();//获取验证对象名称
            }
            //错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key, msg);
        }
        return errMap;
    }

    /**
     * 调用控制器前先执行这个方法
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        //绑定验证器
        binder.setValidator(new UserValidator());
        //定义日期参数格式，参数不再需要注解@DateTimeFormat， Boolean表示是否允许为空
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
    }

    @GetMapping("/validator")
    @ResponseBody
    public Map<String, Object> validator(@Valid User user, Errors Errors, Date date){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("date", date);
        //判断是否存在错误
        if (Errors.hasErrors()) {
            //获取全部错误
            List<ObjectError> oes = Errors.getAllErrors();
            for (ObjectError oe : oes) {
                //判断是否字段错误
                if (oe instanceof FieldError) {
                    //字段错误
                    FieldError fe = (FieldError) oe;
                    map.put(fe.getField(), fe.getDefaultMessage());
                }else {
                    //对象错误
                    map.put(oe.getObjectName(), oe.getDefaultMessage());
                }
            }
        }
        return map;
    }
}
