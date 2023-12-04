package com.rdc.contractmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletionInformation {

  /**
   * 主键
   */
  private Integer id;
  /**
   * 合同编号
   */
  private String contractId;
  /**
   *开工日期
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date commencementDate;
  /**
   * 竣工日期
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date completionDate;
  /**
   * 规模（平方米）
   */
  private Double completionScale;
  /**
   * 工程造价
   */
  private Double completionCost;
  /**
   * 地上层数
   */
  private Integer upLayers;
  /**
   * 地下层数
   */
  private Integer underLayers;

}
