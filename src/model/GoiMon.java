/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author redar
 */
public class GoiMon {
    private String tenmon;
    private String dvt;
    private float dongia;
    private int soluong;

    public GoiMon() {
    }

    public GoiMon(String tenmon, String dvt, float dongia, int soluong) {
        this.tenmon = tenmon;
        this.dvt = dvt;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
