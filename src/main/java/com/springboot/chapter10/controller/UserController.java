package com.springboot.chapter10.controller;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.springboot.chapter10.view.PdfExportService;
import com.springboot.chapter10.view.PdfView;
import com.springboot.chapter8.pojo.User;
import com.springboot.chapter8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService = null;

    //导出接口
    @GetMapping("/export/pdf")
    public ModelAndView exportPdf(String userName, String note){
        //查询用户信息列表
        List<User> userList = userService.findUser(userName, note, 0, 2);
        //定义PDF视图
        View view = new PdfView(exportService());
        ModelAndView mv = new ModelAndView();
        //设置视图
        mv.setView(view);
        //加入数据模型
        mv.addObject("userList", userList);
        return mv;
    }

    //导出PDF自定义
    @SuppressWarnings("unchecked")
    private PdfExportService exportService() {
        //使用Lambda表达式定义自定义导出
        return (model, document, writer, request, response) -> {
            try {
                //A4纸张
                document.setPageSize(PageSize.A4);
                //标题
                document.addTitle("用户信息");
                //换行
                document.add(new Chunk("\n"));
                //表格，3列
                PdfPTable table = new PdfPTable(3);
                //单元格
                PdfPCell cell = null;
                //字体，定义为蓝色加粗
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                //标题
                cell = new PdfPCell(new Paragraph("id", f8));
                //居中对齐
                cell.setHorizontalAlignment(1);
                //将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("user_name", f8));
                //居中对齐
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                //获取数据模型中的用户列表
                List<User> userList = (List<User>) model.get("userList");
                for (User user : userList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(user.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(user.getUserName()));
                    table.addCell(cell);
                    String note = user.getNote() == null ? "" : user.getNote();
                    cell = new PdfPCell(new Paragraph(note));
                    table.addCell(cell);
                }
                //在文档中加入表格
                document.add(table);
            }catch (DocumentException e){
                e.printStackTrace();
            }
        };
    }


}
