package com.answerlyl.myfinance.controller;


import com.answerlyl.myfinance.common.annotation.PassToken;
import com.answerlyl.myfinance.common.consts.FinanceConsts;
import com.answerlyl.myfinance.common.exception.GlobalException;
import com.answerlyl.myfinance.common.result.CodeMsg;
import com.answerlyl.myfinance.common.result.Result;
import com.answerlyl.myfinance.common.utils.TokenUtil;
import com.answerlyl.myfinance.entity.User;
import com.answerlyl.myfinance.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-03
 */
@RestController
@RequestMapping("/myfinance")
public class LoginController {

    @Autowired
    private IUserService userService;

    @PassToken
    @RequestMapping("/checkUser")
    public Result login(@RequestBody User user, HttpServletResponse response){

        String userCode = user.getUserCode();
        String pwd = user.getPwd();
        if(StringUtils.isBlank(user.getUserCode())){
            throw new GlobalException(CodeMsg.USER_CODE_NULL);
        }
        if(StringUtils.isBlank(user.getPwd())){
            throw new GlobalException(CodeMsg.PWD_NULL);
        }

        User dbUser = userService.qryUser(userCode,pwd);
        User userRsp = new User();
        if(dbUser != null ){

            userRsp.setUserId(dbUser.getUserId());
            userRsp.setUserCode(dbUser.getUserCode());

            String jwtToken = TokenUtil.createJwtToken(String.valueOf(dbUser.getUserId()), dbUser.getUserCode());
            response.setHeader("Access-Control-Expose-Headers","refresh,token");
            response.setHeader("refresh",FinanceConsts.YES);
            response.setHeader("token",jwtToken);

        }else{
            throw new GlobalException(CodeMsg.USER_INVALID);
        }


        return Result.success(userRsp);



    }



}

