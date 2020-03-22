package com.answerlyl.myfinance.service.impl;

import com.answerlyl.myfinance.common.consts.FinanceConsts;
import com.answerlyl.myfinance.common.utils.MD5Util;
import com.answerlyl.myfinance.entity.User;
import com.answerlyl.myfinance.mapper.UserMapper;
import com.answerlyl.myfinance.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author answerlyl
 * @since 2020-02-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User qryUser(String userCode, String pwd) {
        String tmpPwd = pwd + FinanceConsts.SALT;
        String dbPwd = MD5Util.getMD5Code(tmpPwd);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_CODE",userCode).eq("PWD",dbPwd);
        return userMapper.selectOne(queryWrapper);
    }
}
