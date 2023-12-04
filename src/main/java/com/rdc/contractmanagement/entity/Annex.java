package com.rdc.contractmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Annex {

  /**
   * 主键
   */
  private Integer id;
  /**
   * 合同编号
   */
  private String contractId;
  /**
   * 文件路径
   */
  private String file;
  /**
   * 文件类型id，合同文件1，获奖文件2，中标文件3,补充协议4
   */
  private Integer type;

}
