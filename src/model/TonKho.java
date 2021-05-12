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
public class TonKho {
    private String mamon;
    private String tenmon;
    private String maloai;
    private String dvt;
    private int dongia;
    private int soluongton;

    public TonKho() {
    }

    public TonKho(String mamon, String tenmon, String maloai, String dvt, int dongia, int soluongton) {
        this.mamon = mamon;
        this.tenmon = tenmon;
        this.maloai = maloai;
        this.dvt = dvt;
        this.dongia = dongia;
        this.soluongton = soluongton;
    }

    public String getMamon() {
        return mamon;
    }

    public void setMamon(String mamon) {
        this.mamon = mamon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }
    
    
}
