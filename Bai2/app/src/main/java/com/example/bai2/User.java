package com.example.bai2;

public class User {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    private String Name;
    private String PhoneNum;
    private String imgUrl;

    public User() {

    }

    public User(int id, String Name, String PhoneNum, String imgUrl) {
        this.id = id;
        this.Name = Name;
        this.PhoneNum = PhoneNum;
        this.imgUrl = imgUrl;
    }
}
