package com.rdc.contractmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rdc.contractmanagement.bean.CommonResult;
import com.rdc.contractmanagement.entity.User;

import javax.swing.*;

public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @return
     */
    CommonResult login(User user);

    /**
     * 超级管理员新增用户
     * @return
     */
    CommonResult regist(User user);

    /**
     * 超级管理员获取用户信息
     * @return
     */
    CommonResult getUsers(Long page);

    /**
     * 编辑用户信息
     * @param user
     * @return
     */
    CommonResult editUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    CommonResult del(Integer id);
}
