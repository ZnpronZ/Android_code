package com.example.baitap2;

public class User {
    private int id;
    private String Name;
    private String Description ;
    private String imgUrl;
    private boolean status;

    public User()
    {

    }
    public User(int id, String name, String description, String imgUrl, boolean status) {
        this.id = id;
        Name = name;
        Description = description;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
