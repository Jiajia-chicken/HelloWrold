package com.rdc.contractmanagement.entity;


public class Permission {

  private long id;
  private long permissionId;
  private String permissionContent;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(long permissionId) {
    this.permissionId = permissionId;
  }


  public String getPermissionContent() {
    return permissionContent;
  }

  public void setPermissionContent(String permissionContent) {
    this.permissionContent = permissionContent;
  }

}
