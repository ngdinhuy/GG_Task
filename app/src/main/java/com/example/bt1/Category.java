package com.example.bt1;

public class Category {
    int ID;
    String ten;

    public Category(int ID, String ten) {
        this.ID = ID;
        this.ten = ten;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
