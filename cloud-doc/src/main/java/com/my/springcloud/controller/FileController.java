package com.my.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController {
    //@Value 获取配置文件里面的属性值，并且赋值给下面的属性

    @Value("${fileUploadPath}")
    String filePath;

    /**
     * @param req
     * @param multiReq
     * @throws IOException
     */
    @PostMapping("/templteUpload")
    public void testUploadFile(HttpServletRequest req, MultipartHttpServletRequest multiReq, HttpServletResponse response) throws IOException, ServletException {
        MultipartFile multipartFile = multiReq.getFile("file");
        String filename = multipartFile.getOriginalFilename();
        System.out.println("文件名字：" + multipartFile.getOriginalFilename());
        File file = new File(filePath + filename);
        multipartFile.transferTo(file);
        String mname = req.getParameter("mname");
        System.out.println("模板名字：" + mname);
        response.sendRedirect("/report/template.html");
    }

}