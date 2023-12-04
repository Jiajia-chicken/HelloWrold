package com.rdc.contractmanagement.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rdc.contractmanagement.Utils.JWTUtil;
import com.rdc.contractmanagement.bean.CommonResult;
import com.rdc.contractmanagement.bean.dto.UserDTO;
import com.rdc.contractmanagement.bean.vo.PageVO;
import com.rdc.contractmanagement.entity.User;
import com.rdc.contractmanagement.mapper.UserMapper;
import com.rdc.contractmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User > implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public CommonResult login(User user) {
        //查询是否有该用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account",user.getAccount());
        User loginUser = userMapper.selectOne(userQueryWrapper);
        if(loginUser==null){
            //不存在该用户
            return CommonResult.fail("该用户不存在");
        }

        String password = DigestUtil.md5Hex(user.getPassword());
        if(!(password.equals(loginUser.getPassword()))){
            //密码错误
            return CommonResult.fail("密码错误");
        }
        //存在该用户
        String token = JWTUtil.createToken(loginUser);
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(loginUser,userDTO);
        userDTO.setToken(token);
        return CommonResult.success("登陆成功",userDTO);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtil.md5Hex("123456"));
    }

    @Override
    public CommonResult regist(User user) {
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        if(1==userMapper.insert(user)){
            return CommonResult.success("创建成功");
        }
        return CommonResult.fail("创建失败");
    }

    @Override
    public CommonResult getUsers(Long page) {
        Page<User> userPage = new Page<>(page,10);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id","account","user_name","permission_level");
        Page<User> pageData = userMapper.selectPage(userPage, userQueryWrapper);
        return CommonResult.success(new PageVO(pageData.getRecords(),pageData.getPages(),pageData.getSize(),pageData.getTotal()));
    }

    @Override
    public CommonResult editUser(User user) {
        int result = 0;
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id",user.getId());
        if(user.getPassword()==""){
            result = userMapper.update(user, userUpdateWrapper);
        }else {
            user.setPassword(DigestUtil.md5Hex(user.getPassword()));
            result = userMapper.update(user, userUpdateWrapper);
        }
        if(result<1){
            return CommonResult.fail("编辑失败");
        }
        return CommonResult.success("编辑成功");
    }

    @Override
    public CommonResult del(Integer id) {
        int result = userMapper.deleteById(id);
        if(result<1){
            return CommonResult.fail("删除失败");
        }
        return CommonResult.success("删除成功");
    }


}
