package com.my.springcloud.dto;

import com.my.springcloud.entity.Template;
import lombok.Data;

@Data
public class TemplateDTO extends Template {
    private String statusMsg;
    private String TypeMsg;
}
