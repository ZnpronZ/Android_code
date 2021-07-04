package com.example.baitest;

public class VeTau {

    private Integer id;
    private String GaDi;
    private String GaDen;
    private Float Gia;
    private Boolean KhuHoi=true;

    public VeTau(Integer id, String gaDi, String gaDen, Float gia, Boolean khuHoi) {
        this.id = id;
        GaDi = gaDi;
        GaDen = gaDen;
        Gia = gia;
        KhuHoi = khuHoi;
    }
    public VeTau(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGaDi() {
        return GaDi;
    }

    public void setGaDi(String gaDi) {
        GaDi = gaDi;
    }

    public String getGaDen() {
        return GaDen;
    }

    public void setGaDen(String gaDen) {
        GaDen = gaDen;
    }

    public Float getGia() {
        return Gia;
    }

    public void setGia(Float gia) {
        Gia = gia;
    }

    public Boolean getKhuHoi() {
        return KhuHoi;
    }

    public void setKhuHoi(Boolean khuHoi) {
        KhuHoi = khuHoi;
    }
}

