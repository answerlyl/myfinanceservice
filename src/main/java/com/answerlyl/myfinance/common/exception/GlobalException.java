package com.answerlyl.myfinance.common.exception;


import com.answerlyl.myfinance.common.result.CodeMsg;

/**
 * @author LYL
 * @desp
 * @date 2018/12/28
 */
public class GlobalException extends RuntimeException {

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

}
