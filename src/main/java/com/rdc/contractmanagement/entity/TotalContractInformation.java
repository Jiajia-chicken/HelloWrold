package com.rdc.contractmanagement.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ColumnWidth(18)
@HeadRowHeight(37)
@ContentRowHeight(23)
public class TotalContractInformation {

  @ExcelIgnore
  private Integer id;
  @ExcelProperty(value = "合同编号",index = 0)
  private String contractId;
  @ExcelIgnore
  private String contractName;
  @ExcelProperty(value = "合同类型",index = 1)
  private String contractType;
  @ExcelIgnore
  private String contractAccess;
  @ExcelProperty(value = "项目部",index = 9)
  private String department;
  @ExcelIgnore
  private String operator;
  @ExcelIgnore
  private Double settlementPrice;
  @ExcelProperty(value = "监理费（万元）",index = 8)
  private Double contractAmount;
  @ExcelIgnore
  private Double finishAmount;
  @ExcelIgnore
  private Double unfinishAmount;
  @ExcelIgnore
  private String signedAddress;
  @com.alibaba.excel.annotation.format.DateTimeFormat("yyyy年MM月dd日")
  @ExcelProperty(value = "签订日期",index = 7)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
  private Date signedTime;
  @ExcelIgnore
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
  private Date beginTime;
  @ExcelIgnore
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
  private Date endTime;
  @ExcelIgnore
  private String awardText;
  @ExcelIgnore
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
  private Date bidDate;
  @ExcelIgnore
  private Double bidAmount;
  @ExcelIgnore
  private String bidInstitution;
  @ExcelProperty(value = "备注",index = 13)
  private String remark;
  @ExcelProperty(value = "甲方单位",index = 2)
  private String employer;
  @ExcelIgnore
  private String employerAddress;
  @ExcelIgnore
  private String contact;
  @ExcelIgnore
  private String contactPhone;
  @ExcelProperty(value = "项目名称",index = 3)
  private String projectName;
  @ExcelIgnore
  private String projectType;
  @ExcelProperty(value = "项目地址",index = 4)
  private String projectAddress;
  @ExcelProperty(value = "规模（平方米）",index = 5)
  private Double projectScale;
  @ExcelProperty(value = "工程造价（万元）",index = 6)
  private Double projectCost;
  @ExcelProperty(value = "项目总监",index = 10)
  private String projectPrincipal;
  @com.alibaba.excel.annotation.format.DateTimeFormat("yyyy年MM月dd日")
  @ExcelProperty(value = "开工日期",index = 11)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
  private Date commencementDate;
  @com.alibaba.excel.annotation.format.DateTimeFormat("yyyy年MM月dd日")
  @ExcelProperty(value = "竣工日期",index = 12)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
  private Date completionDate;
  @ExcelIgnore
  private Double completionScale;
  @ExcelIgnore
  private Double completionCost;
  @ExcelIgnore
  private Integer upLayers;
  @ExcelIgnore
  private Integer underLayers;
  @ExcelIgnore
  private Boolean isDelete;
  @ExcelIgnore
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
  private Date createTime;
  @ExcelIgnore
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
  private Date updateTime;
  
}
