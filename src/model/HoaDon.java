/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;



/**
 *
 * @author redar
 */
public class HoaDon {
    private String mahd;
    private Timestamp thoigian;
    private String makh;
    private String manv;
    private int maban;
    private int giamgia;

    public HoaDon() {
    }

    public HoaDon(String mahd, Timestamp thoigian, String makh, String manv, int maban, int giamgia) {
        this.mahd = mahd;
        this.thoigian = thoigian;
        this.makh = makh;
        this.manv = manv;
        this.maban = maban;
        this.giamgia = giamgia;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public Timestamp getThoigian() {
        return thoigian;
    }

    public void setThoigian(Timestamp thoigian) {
        this.thoigian = thoigian;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    

  
    
}
