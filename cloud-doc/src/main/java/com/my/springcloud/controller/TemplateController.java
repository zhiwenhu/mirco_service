package com.my.springcloud.controller;

import com.my.springcloud.entity.Template;
import com.my.springcloud.service.TemplateService;
import com.my.springcloud.util.ResultVoUtil;
import com.my.springcloud.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 模板控制类
 */
@RestController
@RequestMapping("template")
@Api(value = "模板接口")
public class TemplateController {
    @Resource
    TemplateService templateService;

    @ApiOperation(value = "返回所有模板")
    @GetMapping("listAll/{page}/{limit}")
    public ResultVo list(@PathVariable("page") String page, @PathVariable("limit") String limit) {
        return ResultVoUtil.success(templateService.getAll(page, limit));
    }

    @ApiOperation(value = "根据名字和类型查找")
    @GetMapping("listBy1/{page}/{limit}/{name}/{type}")
    public ResultVo listBy1(@PathVariable("page") String page, @PathVariable("limit") String limit, @PathVariable("name") String name, @PathVariable("type") String type) {
        return ResultVoUtil.success(templateService.getByNameAndType(page, limit, name, type));
    }

    @ApiOperation(value = "删除模板")
    @PostMapping("delete/{id}")
    public ResultVo delete(@PathVariable("id") String id) {
        templateService.delete(id);
        return ResultVoUtil.success();
    }

    @ApiOperation(value = "修改模板")
    @PostMapping("update")
    public ResultVo delete(@RequestBody Template template) {
        return ResultVoUtil.success(templateService.update(template));
    }


}
