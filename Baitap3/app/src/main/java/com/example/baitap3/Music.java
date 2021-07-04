package com.example.baitap3;

public class Music {
    private String Name ;
    private int Id;
    private String Singer;
    private double Time;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public Music(String name, int id, String singer, double time) {
        Name = name;
        Id = id;
        Singer = singer;
        Time = time;
    }

    public Music()
    {

    }
    public double getTime() {
        return Time;
    }

    public void setTime(double time) {
        Time = time;
    }
}
