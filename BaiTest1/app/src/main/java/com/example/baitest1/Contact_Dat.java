package com.example.baitest1;

public class Contact_Dat {
    private int Id;
    private String Name;
    private String Phone;

    public Contact_Dat(int id, String name, String phone) {
        Id = id;
        Name = name;
        Phone = phone;
    }

    public  Contact_Dat()
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
