package com.rdc.contractmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidInformation {

  /**
   * 主键
   */
  private Integer id;
  /**
   * 合同编号
   */
  private String contractId;
  /**
   * 中标日期
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date bidDate;
  /**
   * 中标金额
   */
  private Double bidAmount;
  /**
   * 交易机构
   */
  private String bidInstitution;

}
