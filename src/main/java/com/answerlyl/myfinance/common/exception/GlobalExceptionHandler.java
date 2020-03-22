package com.answerlyl.myfinance.common.exception;

import com.answerlyl.myfinance.common.result.CodeMsg;
import com.answerlyl.myfinance.common.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LYL
 * @desp
 * @date 2018/12/28
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if(e instanceof GlobalException){
            GlobalException globalException = (GlobalException) e ;
            return Result.error(globalException.getCodeMsg());
        }else if(e instanceof BindException){
            BindException bindException = (BindException) e;
            List<ObjectError> errors = bindException.getAllErrors();
            ObjectError objectError = errors.get(0);
            String defaultMessage = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillAges(defaultMessage));
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
