package com.answerlyl.myfinance.common.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @Author liyl21
 * @Date 2020/3/6 17:33
 **/
@Component
@Aspect
public class AppAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(public * com.answerlyl.myfinance.service.IFinanceService.*(..)))")
    public void AppAspect(){
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("AppAspect()")
    public void doAfterReturningGame(){
        System.out.println("返回通知：经纪人为球星表现疯狂鼓掌！");
    }

}
