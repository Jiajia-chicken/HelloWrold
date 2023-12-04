package com.rdc.contractmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rdc.contractmanagement.entity.Contract;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ContractMapper extends BaseMapper<Contract> {
}
