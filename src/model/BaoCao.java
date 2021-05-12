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
public class BaoCao {
    private int mahd;
    private Timestamp thoigian;
    private String tennv;
    private String tenmon;
    private String dvt;
    private int soluong;

    public BaoCao() {
    }

    public BaoCao(int mahd, Timestamp thoigian, String tennv, String tenmon, String dvt, int soluong) {
        this.mahd = mahd;
        this.thoigian = thoigian;
        this.tennv = tennv;
        this.tenmon = tenmon;
        this.dvt = dvt;
        this.soluong = soluong;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public Timestamp getThoigian() {
        return thoigian;
    }

    public void setThoigian(Timestamp thoigian) {
        this.thoigian = thoigian;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
    
}
