package com.answerlyl.myfinance.common.consts;

import com.answerlyl.myfinance.common.utils.PropertyConstants;
import lombok.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @Author liyl21
 * @Date 2020/1/30 17:23
 **/
public class FinanceConsts {

    public static volatile Map<String,Integer> INIT_DESC = new HashMap();
    public static volatile String SORT = "sort";
    public static volatile String BELONG = "belong";
    public static volatile String FINTYPE = "finType";

    static {
        INIT_DESC.put(SORT,10);
        INIT_DESC.put(BELONG,11);
        INIT_DESC.put(FINTYPE,12);
    }

    public static volatile Map<String,Integer> INIT_PARTY_DIST = new HashMap();
    public static volatile String PARTY_ORDER = "partyOrder";
    public static volatile String SEX = "sex";

    static {
        INIT_PARTY_DIST.put(PARTY_ORDER,16);
        INIT_PARTY_DIST.put(SEX,15);
    }
    /**
     * 签名秘钥,可以换成 秘钥 注入
     */
    public static volatile String SECRET = "LIYANLAIANSWERL5O2V0EJINGWEN";//注意:本参数需要长一点，不然后面剪切的时候很可能长度为0，就会报错


    /**
     * 密码偏移量
     */
    public static volatile String SALT = "LoveYourLife";


    /**
     * 签发地
     */
    public static volatile String ISSUER  = "answerlyl.com";
    /**
     * 过期时间(毫秒)7天7*24*60*60*1000
     */
    //public static final long TTLMILLIS = 3600*1000*60;
    public static volatile long TTL_MILLIS = 604800000;

    /**
     * 剩余多长时间以后进行刷新token(毫秒)1天
     */
    public static volatile long SURPLUS_TIME = 86400000;

    public static volatile String YES = "Y";
    public static volatile String NO = "N";


    /**
     * 是否启用token
     */

    public static volatile String IS_USE_TOKEN = PropertyConstants.getPropertiesKey("sys.isEnableToken");

}
