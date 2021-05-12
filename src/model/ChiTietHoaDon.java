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
public class ChiTietHoaDon {
    private int mahd;
    private String mamon;
    private int soluong;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int mahd, String mamon, int soluong) {
        this.mahd = mahd;
        this.mamon = mamon;
        this.soluong = soluong;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getMamon() {
        return mamon;
    }

    public void setMamon(String mamon) {
        this.mamon = mamon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
