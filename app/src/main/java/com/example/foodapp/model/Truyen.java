package com.example.foodapp.model;

public class Truyen {
    private int ID;
    private String PostName;
    private String txtPostcontent;
    private String Anh;
    private int ID_TK;

    public Truyen(int ID, String tenTruyen, String noiDung, String anh, int ID_TK) {
        this.ID = ID;
        PostName = tenTruyen;
        txtPostcontent = noiDung;
        Anh = anh;
        this.ID_TK = ID_TK;
    }

//    public Truyen(String tenTruyen, String noiDung, String anh, int ID_TK) {
//        PostName = tenTruyen;
//        txtPostcontent = noiDung;
//        Anh = anh;
//        this.ID_TK = ID_TK;
//    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTruyen() {
        return PostName;
    }

    public void setTenTruyen(String tenTruyen) {
        PostName = tenTruyen;
    }

    public String getNoiDung() {
        return txtPostcontent;
    }

    public void setNoiDung(String noiDung) {
        txtPostcontent = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}

