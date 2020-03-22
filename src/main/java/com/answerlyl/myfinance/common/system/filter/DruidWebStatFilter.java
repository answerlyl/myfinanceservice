package com.answerlyl.myfinance.common.system.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 描述:
 *
 * @Author liyl21
 * @Date 2020/2/1 11:07
 **/
@WebFilter(
        filterName="DruidWebStatFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions",value="*.js,*.jpg,*.png,*.gif,*.ico,*.css,/druid/*")//配置本过滤器放行的请求后缀
        }
)
public class DruidWebStatFilter  extends WebStatFilter {

}
