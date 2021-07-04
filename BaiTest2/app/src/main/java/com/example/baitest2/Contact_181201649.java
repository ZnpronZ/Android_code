package com.example.baitest2;

public class Contact_181201649 {
    private int Id;
    private String Name;
    private String Phone;

    public Contact_181201649(int id, String name, String phone) {
        Id = id;
        Name = name;
        Phone = phone;
    }

    public Contact_181201649()
    {

    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
