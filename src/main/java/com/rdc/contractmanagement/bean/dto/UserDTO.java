package com.rdc.contractmanagement.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 账号
     */
    private String account;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 权限等级
     */
    private Integer permissionLevel;
    /**
     * token
     */
    private String token;
}
