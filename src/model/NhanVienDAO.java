/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author redar
 */
public class NhanVienDAO {

    private final ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public NhanVienDAO() {
        cn = new ConnectDB();
    }

    public ObservableList<NhanVien> getNhanVien() throws SQLException {
        ObservableList<NhanVien> nvList = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "Select * from nhanvien";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            NhanVien nv;
            while (rs.next()) {
                nv = new NhanVien(rs.getString("manv"), rs.getString("tennv"), rs.getDate("ngaysinh"), rs.getString("diachi"),
                          rs.getString("sodt"), rs.getString("gioitinh"), rs.getString("username"), rs.getString("pass"), rs.getInt("roll"));
                nvList.add(nv);
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return nvList;
    }

    public boolean checkLogin(NhanVien nv) throws SQLException {
        boolean check = false;
        try {
            cn.openConnect();
            String sql = "select * from nhanvien where username = ? and pass = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setString(1, nv.getUsername());
            ps.setString(2, nv.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
        return check;

    }

    public int getRoll(NhanVien nv) throws SQLException {
        int roll = 0;
        try {
            cn.openConnect();
            String sql = "select roll from nhanvien where username = ? and pass = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setString(1, nv.getUsername());
            ps.setString(2, nv.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                roll = rs.getInt("roll");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
        return roll;
    }
    
    public String getName(NhanVien nv) throws SQLException{
        String name = null;
        try{
            cn.openConnect();
            String sql = "select tennv from nhanvien where username = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setString(1, nv.getUsername());
            rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("tennv");
            }
        }catch(SQLException e){
        }finally{
            cn.closeConnect();
        }
        return name;
        
    }
    
    public String getMaNVbyUsername(String username) throws SQLException{
        String manv = null;
        try{
            cn.openConnect();
            String sql = "select manv from nhanvien where username = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while(rs.next()){
                manv = rs.getString("manv");
            }
        }catch(SQLException e){
        }finally{
            cn.closeConnect();
        }
        return manv;
    }
}
