package com.rdc.contractmanagement.entity;


public class Collection {

  private long id;
  private long contractId;
  private String contractName;
  private java.sql.Date creditingDate;
  private long creditingAmount;
  private String operator;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getContractId() {
    return contractId;
  }

  public void setContractId(long contractId) {
    this.contractId = contractId;
  }


  public String getContractName() {
    return contractName;
  }

  public void setContractName(String contractName) {
    this.contractName = contractName;
  }


  public java.sql.Date getCreditingDate() {
    return creditingDate;
  }

  public void setCreditingDate(java.sql.Date creditingDate) {
    this.creditingDate = creditingDate;
  }


  public long getCreditingAmount() {
    return creditingAmount;
  }

  public void setCreditingAmount(long creditingAmount) {
    this.creditingAmount = creditingAmount;
  }


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
