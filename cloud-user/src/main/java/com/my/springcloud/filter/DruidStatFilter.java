package com.my.springcloud.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * druid过滤器
 */
@WebFilter(filterName = "druidWebStatFilter",urlPatterns = "/*",
        initParams = {@WebInitParam(name = "exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"), // 忽略资源
                @WebInitParam(name = "session-stat-enable", value = "true"), // 开启session统计功能
                @WebInitParam(name = "session-stat-max-count", value = "20"),})  // session的最大个数,默认100
public class DruidStatFilter extends WebStatFilter {
}
