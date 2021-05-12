/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author redar
 */
public class MonDAO {
    private final ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public MonDAO(){
        cn = new ConnectDB();
    }
    
    public ObservableList<Mon> getDSMon() throws SQLException{
        ObservableList<Mon> mList = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "Select * from mon";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            Mon m;
            while (rs.next()) {
                m = new Mon(rs.getString("mamon"), rs.getString("tenmon"), rs.getString("maloai"), rs.getString("dvt"),
                          rs.getFloat("dongia"), rs.getInt("soluongton"));
                mList.add(m);
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
        return mList;        
    }
    
    public ObservableList<LoaiMon> getLoaiMon() throws SQLException{
        ObservableList<LoaiMon> lmList = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "Select * from loaimon";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            LoaiMon lm;
            while (rs.next()) {
                lm = new LoaiMon(rs.getString("maloai"), rs.getString("tenloai"));
                lmList.add(lm);
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
        return lmList;
    }
    
    public ObservableList<Mon> getMonbyLoaiMon (String loaimon) throws SQLException{
        ObservableList<Mon> mList = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "select * from mon join loaimon using (maloai) where tenloai = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setString(1, loaimon);
            rs = ps.executeQuery();
            Mon m;
            while (rs.next()) {
                m = new Mon(rs.getString("mamon"), rs.getString("tenmon"), rs.getString("maloai"), rs.getString("dvt"),
                          rs.getFloat("dongia"), rs.getInt("soluongton"));
                mList.add(m);
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
        return mList;
    }
    
    public ObservableList<String> tenMon() throws SQLException{
         ObservableList<String> mList = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "select tenloai from loaimon";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            String m = null;
            while (rs.next()) {
                m = rs.getString("tenloai");
                mList.add(m);
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
        return mList;
    }
}
