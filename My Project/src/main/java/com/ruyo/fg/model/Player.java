package com.ruyo.fg.model;

public class Player {

    public enum Status {
        ENABLED_AND_NOTDELETED,
        NOTENABLED_AND_NOTDELETED,
        NOTENABLED_AND_DELETED,
        DEFAULT
    }

    private int id;
    private String firstname;
    private String lastname;
    private String age;
    private String email;
    private String nationality;
    private boolean isEnable;
    private boolean isDelete;

    public Player() {
    }

    public Player(String firstname, String lastname, String age, String email, String nationality) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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
