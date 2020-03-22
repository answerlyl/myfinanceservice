package com.answerlyl.myfinance.common.system.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 描述:druid新增类
 * DruidConfig、DruidWebStatFilter、DruidStatViewServlet
 * 在MyFinanceServiceApplication增加了@ServletComponentScan
 *
 * @Author liyl21
 * @Date 2020/2/1 11:04
 **/
@Configuration//标识该类被纳入spring容器中实例化并管理
@ServletComponentScan //用于扫描所有的Servlet、filter、listener
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")//加载时读取指定的配置信息,前缀为spring.datasource.druid
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
}
