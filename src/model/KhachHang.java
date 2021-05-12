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
public class KhachHang {
    private String makh;
    private String tenkh;
    private String diachi;
    private String sodt;
    private float diemtichluy;

    public KhachHang() {
    }

    public KhachHang(String makh, String tenkh, String diachi, String sodt, float diemtichluy) {
        this.makh = makh;
        this.tenkh = tenkh;
        this.diachi = diachi;
        this.sodt = sodt;
        this.diemtichluy = diemtichluy;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public float getDiemtichluy() {
        return diemtichluy;
    }

    public void setDiemtichluy(float diemtichluy) {
        this.diemtichluy = diemtichluy;
    }
    
    
}
