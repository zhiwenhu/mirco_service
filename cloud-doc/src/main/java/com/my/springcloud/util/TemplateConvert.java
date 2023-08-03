package com.my.springcloud.util;

import com.my.springcloud.dto.TemplateDTO;
import com.my.springcloud.entity.Template;
import com.my.springcloud.enums.StatusEnum;
import com.my.springcloud.enums.TypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class TemplateConvert {

    public static Page<TemplateDTO> convertToDTO(Page<Template> page1){
        List<Template> list=page1.getContent();

        List<TemplateDTO> list2=list.stream().map(e->{
            TemplateDTO dto=new TemplateDTO();
            BeanUtils.copyProperties(e,dto);
            dto.setStatusMsg(StatusEnum.getStatusEnum(e.getStatus()).getMsg());
            dto.setTypeMsg(TypeEnum.getTypeEnum(e.getType()).getMsg());
            return dto;
        }).collect(Collectors.toList());
        Page<TemplateDTO> page2=new PageImpl(list2,page1.getPageable(),page1.getTotalElements());
        return page2;

    }
}
