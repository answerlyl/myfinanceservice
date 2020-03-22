package com.answerlyl.myfinance.common.utils;

import com.answerlyl.myfinance.common.consts.FinanceConsts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * 描述:
 *
 * @Author liyl21
 * @Date 2020/2/3 15:58
 **/
public class TokenUtil {



    /**
     * 生成token
     *
     * @param id 一般传入userName
     * @return
     */
    public static String createJwtToken(String id,String subject) {
        return createJwtToken(id, FinanceConsts.ISSUER, subject, FinanceConsts.TTL_MILLIS);
    }
    public static String createJwtToken(String id) {
        return createJwtToken(id, FinanceConsts.ISSUER, "", FinanceConsts.TTL_MILLIS);
    }

    /**
     * 生成Token
     *
     * @param id        编号
     * @param issuer    该JWT的签发者，是否使用是可选的
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param ttlMillis 签发时间 （有效时间，过期会报错）
     * @return token String
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(FinanceConsts.SECRET);
        String str=signatureAlgorithm.getJcaName();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, str);

        // 让我们设置JWT声明
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            //过期时间
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // 构建JWT并将其序列化为一个紧凑的url安全字符串
        return builder.compact();

    }

    /**
     * Token解析方法
     * @param jwt Token
     * @return
     */
    public static Claims parseJWT(String jwt) {
        // 如果这行代码不是签名的JWS(如预期)，那么它将抛出异常
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(FinanceConsts.SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * token是否有效
     * @param token
     * @return
     */
    public static boolean isEffectiveToken(String token){
        boolean isEffective = false;
        try{
            Claims claims = parseJWT(token);
            isEffective = true;
        }catch (Exception e){

        }
        return isEffective;
    }

    /**
     * token是否有效
     * @param token
     * @return
     */
    public static Integer getUserIdFromToken(String token){

        Integer resId = null;
        try{
            Claims claims = parseJWT(token);
            String id = claims.getId();
            return Integer.valueOf(id);
        }catch (Exception e){
            return resId;
        }
    }

    /**
     * 是否需要刷新token
     * @param token
     * @return
     */
    public static boolean isNeedRefreshToken(String token){
        boolean isRefresh = false;
        try{
            Claims claims = parseJWT(token);
            Date expDate = claims.getExpiration();
            long expTime = expDate.getTime();

            // 当前时间时间
            long nowMillis = System.currentTimeMillis();
            //判断当前时间是否在最大过期时间和需要刷新的时间段
            long range = expTime - nowMillis;
            if(range > 0 && range <= FinanceConsts.SURPLUS_TIME){
                isRefresh = true;
            }
        }catch (Exception e){

        }
        return isRefresh;
    }


    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());


        String token = TokenUtil.createJwtToken("2","answerlyl");


        System.out.println(token);

        token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyIiwiaWF0IjoxNTgwNzE4MTA0LCJzdWIiOiJhbnN3ZXJseWwiLCJpc3MiOiJhbnN3ZXJseWwuY29tIiwiZXhwIjoxNTgwNzE4MTEwfQ.f4-5M7mPoNkcS2b4EpmuDoSWE7EtyA23ldUPPdbOlN4";
        token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyIiwiaWF0IjoxNTgwNzE3MDE1LCJzdWIiOiJsdHoiLCJpc3MiOiJhbnN3ZXJseWwuY29tIiwiZXhwIjoxNTgwOTMzMDE1fQ.08vve8XGpfdgZ9yDAFpc7yWQIrmtLcI0SHqLCOdeeSc";

        Claims claims = TokenUtil.parseJWT(token);
        System.out.println(claims);
        Date exp = new Date(1580833000l);
        claims.setExpiration(exp);

        System.out.println(claims);


        //{jti=2, iat=1580717015, sub=ltz, iss=answerlyl.com, exp=1580933015}
        //{jti=2, iat=1580717015, sub=ltz, iss=answerlyl.com, exp=1580832}
    }
}
