package com.example.bai2_cuavinh;

public class ThietBi {
    private int ID;
    private String Name;
    private String Description;
    private String Image;
    private boolean Status;

    public ThietBi(int ID, String name, String description, String image, boolean status) {
        this.ID = ID;
        Name = name;
        Description = description;
        Image = image;
        Status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
