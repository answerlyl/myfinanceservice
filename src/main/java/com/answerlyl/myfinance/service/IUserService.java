package com.answerlyl.myfinance.service;

import com.answerlyl.myfinance.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-03
 */
public interface IUserService extends IService<User> {

    /**
     * 通过用户名密码查询用户
     * @param userCode
     * @param dbPwd
     * @return
     */
    User qryUser(String userCode, String dbPwd);
}
