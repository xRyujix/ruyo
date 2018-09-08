package com.ruyo.fg.model;


public class User {

  private int id;
  private String username;
  private String password;
  private String email;
  private boolean isEnable;
  private boolean isDelete;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isEnable() {
    return isEnable;
  }

  public void setEnable(boolean enable) {
    isEnable = enable;
  }

  public boolean isDelete() {
    return isDelete;
  }

  public void setDelete(boolean delete) {
    isDelete = delete;
  }
}