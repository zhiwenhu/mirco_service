package com.my.springcloud.service;

import com.my.springcloud.dto.TemplateDTO;
import com.my.springcloud.entity.Template;
import com.my.springcloud.repository.TemplateRepository;
import com.my.springcloud.util.KeyUtil;
import com.my.springcloud.util.TemplateConvert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TemplateService {
    @Resource
    TemplateRepository templateRepository;

    //新建
    public Template add(Template template) {
        template.setId(KeyUtil.getId());
        template.setCreateTime(new Date());
        return templateRepository.save(template);
    }

    //修改
    public Template update(Template template) {
        return templateRepository.save(template);
    }

    //删除
    public void delete(String id) {
        templateRepository.deleteById(id);
    }

    //查询
    public Template selectById(String id) {
        return templateRepository.findById(id).get();
    }

    /**
     * page：设置当前第几页
     * limit：设置一页显示多少行数据
     * Integer.parseInt(page) 表示把String类型的page变量，转成Integerl类型
     */
    public Page<TemplateDTO> getAll(String page, String limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(limit), sort);
        Page<Template> page1 = templateRepository.findAll(pageable);

        return TemplateConvert.convertToDTO(page1);
    }

    /**
     * page：设置当前第几页
     * limit：设置一页显示多少行数据
     * Integer.parseInt(page) 表示把String类型的page变量，转成Integerl类型
     */
    public Page<TemplateDTO> getByNameAndType(String page, String limit, String name, String type) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(limit), sort);
        Page<Template> page1 = templateRepository.findByNameAndType(pageable, name, Integer.parseInt(type));
        return TemplateConvert.convertToDTO(page1);
    }

}
