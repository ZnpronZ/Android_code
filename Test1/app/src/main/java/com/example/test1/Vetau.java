package com.example.test1;

public class Vetau implements Comparable<Vetau> {
    private int Ma;
    private String Gadi;
    private String Gaden;
    private double dongia;
    private boolean khuhoi;

    public Vetau(int ma, String gadi, String gaden, double dongia, boolean khuhoi) {
        Ma = ma;
        Gadi = gadi;
        Gaden = gaden;
        this.dongia = dongia;
        this.khuhoi = khuhoi;
    }

    public Vetau(String gadi, String gaden, double dongia, boolean khuhoi) {
        Gadi = gadi;
        Gaden = gaden;
        this.dongia = dongia;
        this.khuhoi = khuhoi;
    }
    public Vetau(){

    }

    public int getMa() {
        return Ma;
    }

    public void setMa(int ma) {
        Ma = ma;
    }

    public String getGadi() {
        return Gadi;
    }

    public void setGadi(String gadi) {
        Gadi = gadi;
    }

    public String getGaden() {
        return Gaden;
    }

    public void setGaden(String gaden) {
        Gaden = gaden;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public boolean isKhuhoi() {
        return khuhoi;
    }

    public void setKhuhoi(boolean khuhoi) {
        this.khuhoi = khuhoi;
    }
    public double getGiaTien(){
        if(khuhoi == true)
            return dongia*2*0.95;

        return dongia;




    }

    @Override
    public int compareTo(Vetau o) {
        if(this.getGiaTien()<o.getGiaTien()){
            return 1;
        }else if(this.getGiaTien()>o.getGiaTien()){
            return -1;
        }
        return 0;
    }
}
