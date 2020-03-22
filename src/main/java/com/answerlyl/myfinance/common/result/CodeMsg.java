package com.answerlyl.myfinance.common.result;

/**
 * @author LYL
 * @desp
 * @date 2018/12/28
 */
public class CodeMsg {

    private int code;
    private String msg;

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(200,"success");

    //通用异常
    public static CodeMsg TOKEN_INVALID = new CodeMsg(300100,"TOKEN失效,请重新登录");
    public static CodeMsg TOKEN_IS_NULL = new CodeMsg(300200,"TOKEN为空,请重新登录");

    //用户无效
    public static CodeMsg USER_INVALID = new CodeMsg(300300,"用户不存在,请重新登录");
    public static CodeMsg USER_CODE_NULL = new CodeMsg(300400,"用户名不能为空");
    public static CodeMsg PWD_NULL = new CodeMsg(300500,"密码不能为空");

    public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101,"参数校验异常: %s");





    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillAges(Object... args){
        int code = this.code;
        String message = String.format(this.msg , args);
        return new CodeMsg(code,message);
    }


}
