package com.my.springcloud.controller;

import com.my.springcloud.common.Result;
import com.my.springcloud.service.DictTypeService;
import com.my.springcloud.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("dict")
@Api("字典Api")
public class DictController {
    @Resource // 默认按名称匹配
    DictTypeService dictTypeService;

    @ApiOperation(value = "根据字典ID查询行业")
    @GetMapping("/showIndustry/{id}")
    public Result showIndustry(@PathVariable("id") String id) {
        log.info(id);
        return ResultUtil.success(dictTypeService.getDictType(id));
    }

    @ApiOperation(value = "根据字典ID查询技能")
    @GetMapping("/showSkillByLimit/{id}/{number}/{limit}")
    public Result showSkillByLimit(@PathVariable("id") String id, @PathVariable("number") String number, @PathVariable("limit") String limit) {
        log.info(id + " " + number + " " + limit);
        return ResultUtil.success( dictTypeService.showSkillByLimit(id, number, limit));
    }

    @ApiOperation(value = "根据字典ID查询技能")
    @GetMapping("/showAllSkill/{id}")
    public Result showAllSkill(@PathVariable("id") String id) {
        log.info(id);
        return ResultUtil.success(dictTypeService.showAllSkill(id));
    }
}
