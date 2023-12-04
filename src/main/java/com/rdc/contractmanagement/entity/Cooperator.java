package com.rdc.contractmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cooperator {

  /**
   * 主键
   */
  private Integer id;
  /**
   * 合同编号
   */
  private String contractId;
  /**
   * 甲方单位
   */
  private String employer;
  /**
   * 单位类型id
   */
  private Integer employerTid;
  /**
   * 单位类型
   */
  private String employerType;
  /**
   * 经手人（甲方联系人）
   */
  private String contact;
  /**
   * 联系电话
   */
  private String contactPhone;
  /**
   * 项目名称
   */
  private String projectName;
  /**
   * 项目类型id
   */
  private Integer projectTid;
  /**
   * 项目类型
   */
  private String projectType;
  /**
   * 项目地址
   */
  private String projectAddress;
  /**
   * 项目规模
   */
  private Double projectScale;
  /**
   * 工程造价
   */
  private Double projectCost;
  /**
   * 项目负责人
   */
  private String projectPrincipal;



}
