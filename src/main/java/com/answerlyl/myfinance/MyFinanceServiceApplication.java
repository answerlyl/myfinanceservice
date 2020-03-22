package com.answerlyl.myfinance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages={"com.answerlyl.myfinance"})
//配置druid时新增的
@ServletComponentScan
@MapperScan("com.answerlyl.myfinance.mapper")
public class MyFinanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFinanceServiceApplication.class, args);
	}

}
