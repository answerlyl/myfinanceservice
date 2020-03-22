package com.answerlyl.myfinance.common.system.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 描述:
 * 地址：http://localhost:9000/service/druid/
 * 和context-path有关
 *
 * @Author liyl21
 * @Date 2020/2/1 11:08
 **/
@WebServlet(urlPatterns="/druid/*",initParams = {
        @WebInitParam(name="allow",value = "127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name = "loginUsername",value = "root"),//用户名
        @WebInitParam(name = "loginPassword",value = "admin"),//密码
        @WebInitParam(name = "resetEnable",value = "true")// 允许HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet implements Servlet {

}
