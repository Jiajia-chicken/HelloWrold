package com.rdc.contractmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

  /**
   * 主键
   */
  private Integer id;
  /**
   * 合同编号
   */
  private String contractId;
  /**
   * 合同类型id
   */
  private Integer typeId;
  /**
   * 合同类型
   */
  private String contractType;
  /**
   * 合同取得方式id
   */
  private Integer accessId;
  /**
   * 合同取得方式
   */
  private String contractAccess;
  /**
   * 部门id
   */
  private Integer departmentId;
  /**
   * 部门
   */
  private String department;
  /**
   * 合同名称
   */
  private String contractName;
  /**
   * 经办人
   */
  private String operator;
  /**
   * 合同金额
   */
  private Double contractAmount;
  /**
   * 完成金额
   */
  private Double finishAmount;
  /**
   * 未完成金额
   */
  private Double unfinishAmount;
  /**
   * 预估价
   */
  private Double settlementPrice;
  /**
   * 结算价
   */
  private Double preValuation;
  /**
   * 签订日期
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date signedTime;
  /**
   * 合同开始日期
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date  beginTime;
  /**
   * 合同结束日期
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date endTime;
  /**
   * 获奖文本
   */
  private String awardText;
  /**
   * 备注
   */
  private String remark;
  /**
   * 创建日期
   */
  private Date createTime;
  /**
   * 更新日期
   */
  private Date updateTime;



}
