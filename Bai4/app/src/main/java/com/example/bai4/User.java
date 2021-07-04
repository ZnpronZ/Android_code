package com.example.bai4;

public class User {
    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Float getDiemToan() {
        return DiemToan;
    }

    public void setDiemToan(Float diemToan) {
        DiemToan = diemToan;
    }

    public Float getDiemLy() {
        return DiemLy;
    }

    public void setDiemLy(Float diemLy) {
        DiemLy = diemLy;
    }

    public Float getDiemHoa() {
        return DiemHoa;
    }

    public void setDiemHoa(Float diemHoa) {
        DiemHoa = diemHoa;
    }



    private String MaSV;
    private String Name;
    private Float DiemToan;
    private Float DiemLy;
    private Float DiemHoa;

    public User(String sadsad12, String phi, int i, int i1, int i2) {

    }

    public User(String MaSV, String Name, Float DiemToan, Float DiemLy,Float DiemHoa,Float TongDiem) {
        this.MaSV=MaSV;
        this.Name = Name;
        this.DiemToan = DiemToan;
        this.DiemLy = DiemLy;
        this.DiemHoa= DiemHoa;
    }
}
