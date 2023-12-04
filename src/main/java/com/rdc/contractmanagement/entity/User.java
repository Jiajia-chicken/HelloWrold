package com.rdc.contractmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  /**
   * 主键
   */
  private Integer id;
  /**
   * 账号
   */
  private String account;
  /**
   * 密码
   */
  private String password;
  /**
   * 用户名
   */
  private String userName;
  /**
   * 权限等级
   */
  private Integer permissionLevel;

}
