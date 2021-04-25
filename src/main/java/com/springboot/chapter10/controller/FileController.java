package com.springboot.chapter10.controller;

import com.mongodb.client.result.DeleteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    /**
     * 打开文件上传请求页面
     * @return 指向JSP的字符串
     */
    @GetMapping("/upload/page")
    public String uploadPage(){
        return "/file/upload";
    }

    //使用HttpServletRequest作为参数
    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String, Object> uploadRequest(HttpServletRequest request){
        boolean flag = false;
        MultipartHttpServletRequest mreq = null;
        //强制转换为MultipartHttpServletRequest接口对象
        if (request instanceof MultipartHttpServletRequest) {
            mreq = ((MultipartHttpServletRequest) request);
        }else {
            return dealResultMap(false, "上传失败");
        }
        //获取MultipartFile文件信息
        MultipartFile mf = mreq.getFile("file");
        //获取文件名称
        String fileName = mf.getOriginalFilename();
        File file = new File(fileName);
        try {
            mf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(false, "上传成功");
    }

    //使用Spring MVC的MultipartFile类作为参数
    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String, Object> uploadMultipartFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadPart(Part file){
        //获取提交文件名称
        String fileName = file.getSubmittedFileName();
        try {
            file.write(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }

    //处理上传文件结果
    private Map<String, Object> dealResultMap(boolean success, String msg) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }
}






















