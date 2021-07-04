package com.example.baitap1;

public class Contact {
    private Integer Id;
    private String Name;
    private String PhoneNumber;
    private Boolean Status;

    public  Contact()
    {

    }

    public Contact(Integer id, String name, String phoneNumber, Boolean status) {
        Id = id;
        Name = name;
        PhoneNumber = phoneNumber;
        Status = status;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
