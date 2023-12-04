package com.rdc.contractmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rdc.contractmanagement.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
