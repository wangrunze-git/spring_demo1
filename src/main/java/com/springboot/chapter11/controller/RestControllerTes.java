package com.springboot.chapter11.controller;


import com.springboot.chapter11.pojo.User;
import com.springboot.chapter11.vo.UserVo;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

public class RestControllerTes {
    //获取用户
    public static UserVo getUser(Long id){
        //创建一个RestTemplate对象
        RestTemplate restTmpl = new RestTemplate();
        //消费服务，第一个参数为url，第二个是返回类型，第三个是uri路径参数
        UserVo userVo = restTmpl.getForObject("http://localhost:8080/user/{id}", UserVo.class, id);
        //打印用户名称
        System.out.println(userVo.getUserName());
        return userVo;
    }

    //RestTemplate使用多参数的HTTP GET请求
    public static List<UserVo> findUser(String userName, String note, int start, int limit){
        RestTemplate restTmpl = new RestTemplate();
        //使用Map封装多个参数，以提高可读性
        HashMap<String, Object> params = new HashMap<>();
        params.put("userName", "user");
        params.put("note", "note");
        params.put("start", start);
        params.put("limit", limit);
        //Map中的key和URI中的参数一一对应
        String url = "http://localhost:8080/users/{userName}/{note}/{start}/{limit}";
        //请求后端
        ResponseEntity<List> responseEntity = restTmpl.getForEntity(url, List.class, params);
        List<UserVo> userVoes = responseEntity.getBody();
        return userVoes;
    }

    //通过POST请求传递JSON请求体
    //新增用户
    public static User insertUser(UserVo newUserVo){
        //请求头
        HttpHeaders headers = new HttpHeaders();
        //设置请求内容为JSON类型
        headers.setContentType(MediaType.APPLICATION_JSON);
        //创建请求实体对象
        HttpEntity<UserVo> request = new HttpEntity<>(newUserVo, headers);
        RestTemplate restTemplate = new RestTemplate();
        //请求时传递请求实体对象，并返回回填id的用户
        User user = restTemplate.postForObject("http://localhost:8080/user", request, User.class);
        System.out.println(user.getId());
        return user;
    }

    //使用RestTemplate执行DELETE请求
    public static void deleteUser(Long id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/user/{id}", id);
    }

    //获取服务器响应头属性和HTTP状态码
    public User insertUserEntity(UserVo newUserVo){
        //请求头
        HttpHeaders headers = new HttpHeaders();
        //请求类型
        headers.setContentType(MediaType.APPLICATION_JSON);
        //绑定请求体和头
        HttpEntity<UserVo> request = new HttpEntity<>(newUserVo, headers);
        RestTemplate restTmpl = new RestTemplate();
        //请求服务器
        ResponseEntity<User> userEntity = restTmpl.postForEntity("http://localhost:8080/user2/entity", request, User.class);
        //获取响应体
        User user = userEntity.getBody();
        //获取响应头
        HttpHeaders respHeaders = userEntity.getHeaders();
        //获取响应属性
        List<String> success = respHeaders.get("success");
        //响应的HTTP状态码
        int status = userEntity.getStatusCodeValue();
        System.out.println(user.getId());
        return user;
    }

    //RestTemplate的exchange方法
    public static User useExchange(UserVo newUserVo, Long id){
        //请求头
        HttpHeaders headers = new HttpHeaders();
        //请求类型
        headers.setContentType(MediaType.APPLICATION_JSON);
        //绑定请求体和头
        HttpEntity<UserVo> request = new HttpEntity<>(newUserVo, headers);
        RestTemplate restTmpl = new RestTemplate();
        String url = "http://localhost:8080/user2/entity";
        //请求服务器
        ResponseEntity<User> userEntity = restTmpl.exchange(url, HttpMethod.POST, request, User.class);
        //获取响应体
        User user = userEntity.getBody();
        //获取响应头
        HttpHeaders respHeaders = userEntity.getHeaders();
        //响应头属性
        List<String> success = respHeaders.get("success");
        //响应的HTTP状态码
        int status = userEntity.getStatusCodeValue();
        System.out.println(user.getId());
        //修改URL获取资源
        url = "http://localhost:8080/user/{id}";
        //传递URL地址参数
        ResponseEntity<UserVo> userVoEntity = restTmpl.exchange(url, HttpMethod.GET, null, UserVo.class, id);
        //获取响应体
        UserVo userVo = userVoEntity.getBody();
        System.out.println(userVo);
        return user;
    }

}
