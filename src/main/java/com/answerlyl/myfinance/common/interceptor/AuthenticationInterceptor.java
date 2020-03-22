package com.answerlyl.myfinance.common.interceptor;

import com.answerlyl.myfinance.common.annotation.LoginRequired;
import com.answerlyl.myfinance.common.annotation.PassToken;
import com.answerlyl.myfinance.common.consts.FinanceConsts;
import com.answerlyl.myfinance.common.exception.GlobalException;
import com.answerlyl.myfinance.common.result.CodeMsg;
import com.answerlyl.myfinance.common.utils.TokenUtil;
import com.answerlyl.myfinance.entity.User;
import com.answerlyl.myfinance.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 描述: 拦截器,校验token是否正常
 *
 * @Author liyl21
 * @Date 2020/2/3 17:47
 **/
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {



        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //如果不适用token直接跳过
        if("N".equals(FinanceConsts.IS_USE_TOKEN)){
            return true;
        }

        String token = request.getHeader("token");// 从 http 请求头中取出 token
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(LoginRequired.class)) {
//            LoginRequired userLoginToken = method.getAnnotation(LoginRequired.class);
//            if (userLoginToken.required()) {
        // 执行认证
        if (token == null) {
            throw new GlobalException(CodeMsg.TOKEN_IS_NULL);
        }
        //校验token是否有效
        boolean effectiveToken = TokenUtil.isEffectiveToken(token);
        if(!effectiveToken){
            throw new GlobalException(CodeMsg.TOKEN_INVALID);
        }
        // 获取 token 中的 user id
        Integer userId = TokenUtil.getUserIdFromToken(token);

        User user = userService.getById(userId);
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_INVALID);
        }

        boolean needRefreshToken = TokenUtil.isNeedRefreshToken(token);
        //判断是否需要刷新token
        if(needRefreshToken){
            String jwtToken = TokenUtil.createJwtToken(String.valueOf(user.getUserId()), user.getUserCode());
            response.setHeader("refresh",FinanceConsts.YES);
            response.setHeader("token",jwtToken);
        }
//                return true;
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
