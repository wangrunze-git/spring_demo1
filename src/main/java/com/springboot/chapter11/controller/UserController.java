package com.springboot.chapter11.controller;

import com.github.pagehelper.PageHelper;
import com.springboot.chapter11.exception.NotFoundException;
import com.springboot.chapter11.pojo.User;
import com.springboot.chapter11.vo.UserVo;
import com.springboot.chapter5.enumeration.SexEnum;
import com.springboot.chapter11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    //用户服务接口
    @Autowired
    private UserService userService = null;

    //映射JSP视图
    @GetMapping("/restful")
    public String index(){
        return "restful";
    }

    @PostMapping("/user")
    @ResponseBody
    public User insertUser(@RequestBody UserVo userVo){
        User user = this.changeToPo(userVo);
        return userService.insertUser(user);
    }

    //获取用户
    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public UserVo getUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return changeToVo(user);
    }

    //查询符合要求的用户
    @GetMapping("/users/{userName}/{note}/{start}/{limit}")
    @ResponseBody
    public List<UserVo> findUsers(
            @PathVariable("userName") String userName, @PathVariable("note") String note,
            @PathVariable("start") int start, @PathVariable("limit") int limit){
        //进行分页
        PageHelper.startPage(start, limit);
        List<User> userList = userService.findUsers(userName, note);
        return this.changeToVoes(userList);
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserVo userVo){
        User user = this.changeToPo(userVo);
        user.setId(id);
        userService.updateUser(user);
        return user;
    }

    @PatchMapping("/user/{id}/{userName}")
    @ResponseBody
    public ResultVo changeUserName(@PathVariable("id") Long id, @PathVariable("userName") String userName){
        int result = userService.updateUserName(id, userName);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败。");
        return resultVo;
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResultVo deleteUser(@PathVariable("id") Long id){
        int result = userService.deleteUser(id);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败");
        return resultVo;
    }

    @PatchMapping("/user/name")
    @ResponseBody
    public ResultVo changeUserName2(Long id, String userName){
        int result = userService.updateUserName(id, userName);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户名【" + id + "】失败。");
        return resultVo;
    }

    //映射JSP视图
    @GetMapping("/user/name")
    public String changeUserName(){
        return "change_user_name";
    }

    @GetMapping(value = "/user/exp/{id}",
    //产生JSON数据集
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    //响应成功
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserVo getUserForExp(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        //如果找不到用户，则抛出异常，进入控制器通知
        if (user == null){
            throw new NotFoundException(1L, "找不到用户【" + id + "】信息");
        }
        UserVo userVo = changeToVo(user);
        return userVo;
    }

    //转换VO为PO
    private User changeToPo(UserVo userVo){
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setSex(SexEnum.getEnumById(userVo.getSexCode()));
        user.setNote(userVo.getNote());
        return user;
    }

    //转换PO为VO
    private UserVo changeToVo(User user){
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUserName(user.getUserName());
        userVo.setSexCode(user.getSex().getId());
        userVo.setNote(user.getNote());
        return userVo;
    }

    //将PO列表转换为VO列表
    private List<UserVo> changeToVoes(List<User> poList){
        ArrayList<UserVo> voList = new ArrayList<>();
        for (User user : poList) {
            UserVo userVo = changeToVo(user);
            voList.add(userVo);
        }
        return voList;
    }

    //结果VO
    public class ResultVo{
        private Boolean success = null;
        private String message = null;

        public ResultVo(){
        }

        public ResultVo(Boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
