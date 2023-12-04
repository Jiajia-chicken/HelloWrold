package com.rdc.contractmanagement.entity;


public class PermissionCtype {

  private long id;
  private long permissionId;
  private long ctypeId;


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


  public long getCtypeId() {
    return ctypeId;
  }

  public void setCtypeId(long ctypeId) {
    this.ctypeId = ctypeId;
  }

}
