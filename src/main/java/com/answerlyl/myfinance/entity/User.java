package com.answerlyl.myfinance.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-03
 */
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    /**
     * 用户Id
     */
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TableField("USER_CODE")
    private String userCode;

    /**
     * 密码
     */
    @TableField("PWD")
    private String pwd;

    @TableField("STATUS_CD")
    private String statusCd;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", userCode=" + userCode +
        ", pwd=" + pwd +
        ", statusCd=" + statusCd +
        "}";
    }
}
