package com.my.springcloud.controller;

import com.my.springcloud.dto.ResumeDTO;
import com.my.springcloud.util.ResultVoUtil;
import com.my.springcloud.util.WordUtil;
import com.my.springcloud.vo.ResultVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
@RequestMapping("/job")
@Slf4j
@RestController
public class JobController {


    @Value("${fileStoreRootPath}")
    String filePath;
    /**
     *  图文+表格-个人简介
     *
     *  生成个人简历
     * @return
     */
    @PostMapping("/resume")
    public ResultVo<Map<String, String>> createResume(@RequestBody  ResumeDTO resumeDTO){
        String finalFilePath="";

        try {
            Map<String,Object> dataMap = new HashMap<String,Object>();
            dataMap.put("name", resumeDTO.getName());
            dataMap.put("sex", resumeDTO.getSex());
            dataMap.put("age", resumeDTO.getAge());
            dataMap.put("education", resumeDTO.getEducation());
            dataMap.put("mobile", resumeDTO.getMobile());
            dataMap.put("major", resumeDTO.getMajor());
            dataMap.put("assessment", resumeDTO.getAssessment());
            dataMap.put("certificate", resumeDTO.getCertificate());
            dataMap.put("workedyears", resumeDTO.getWorkedYears());
            dataMap.put("projectExp", resumeDTO.getProjectExp());
            dataMap.put("trainExp", resumeDTO.getTrainExp());
            //图片
            dataMap.put("image", WordUtil.getImageBase(resumeDTO.getImage()));

            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");
            //指定模板路径的第二种方式,我的路径是D:/      还有其他方式

            System.out.println("filePath==="+filePath);
            configuration.setDirectoryForTemplateLoading(new File(filePath));

            // 输出文档路径及名称
            finalFilePath=filePath + resumeDTO.getId() + "个人简历.doc";
            File outFile = new File(finalFilePath);

            //以utf-8的编码读取ftl文件
            Template t =  configuration.getTemplate("m6.ftl","utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"),10240);
            t.process(dataMap, out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<>();
        map.put("filePath", resumeDTO.getId() + "个人简历.doc");

        return ResultVoUtil.success(map);
    }
}
