package com.springboot.chapter10.view;



import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 各个控制器只需要实现这个接口，就能够自定义其导出PDF的逻辑。接着就是继承AbstractPdfView的非抽象类
 */
public interface PdfExportService {public void make(Map<String, Object> model,
                                                    Document document, PdfWriter writer, HttpServletRequest request,
                                                    HttpServletResponse response);
}
