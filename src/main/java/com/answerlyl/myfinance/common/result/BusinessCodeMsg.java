package com.answerlyl.myfinance.common.result;

/**
 * @author LYL
 * @desp
 * @date 2019/4/19
 */
public class BusinessCodeMsg extends CodeMsg {

    public static CodeMsg BUSINESS_BIND_ERROR = new BusinessCodeMsg(700100,"天气接口调用频繁,请稍后再试: %s");
    public static CodeMsg BUSINESS_AMOUNT_IS_NOT_NULL = new BusinessCodeMsg(700200,"金额不能为空");
    public static CodeMsg BUSINESS_AMOUNT_IS_NOT_FLOAT = new BusinessCodeMsg(700300,"金额不正确");
    public static CodeMsg SAVE_FAILD = new BusinessCodeMsg(700400,"保存失败");
    public static CodeMsg MONTH_IS_NULL = new BusinessCodeMsg(700401,"月份不能为空");
    public static CodeMsg PARAMS_IS_NULL = new BusinessCodeMsg(700402,"请求参数不能为空: %s");


    public BusinessCodeMsg(int code, String msg) {
        super(code, msg);
    }
}
