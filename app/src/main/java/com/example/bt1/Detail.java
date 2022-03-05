package com.example.bt1;

public class Detail {
    String ten,ngay,gio,idCate,ghiChu;
    boolean isChecked;
    public Detail(String idCate, String ten,String ngay, String gio, String ghiChu) {
        this.isChecked=false;
        this.ghiChu=ghiChu;
        this.idCate = idCate;
        this.ten = ten;
        this.ngay=ngay;
        this.gio=gio;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getIdCate() {
        return idCate;
    }

    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }


    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
