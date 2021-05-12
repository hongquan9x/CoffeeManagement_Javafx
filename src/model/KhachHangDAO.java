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
public class KhachHangDAO {
    private final ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public KhachHangDAO(){
        cn = new ConnectDB();
    }
    
    public ObservableList<KhachHang> getKH() throws SQLException{
        ObservableList<KhachHang> listKH = FXCollections.observableArrayList();
        try{
            cn.openConnect();
            String sql = "select * from khachhang";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            KhachHang kh;
            while (rs.next()){
                kh = new KhachHang(rs.getString("makh"), rs.getString("tenkh"), 
                                   rs.getString("diachi"), rs.getString("sdt"), rs.getFloat("diemtichluy"));
                listKH.add(kh);
            }
        }catch(SQLException ex){
        }finally{
            cn.closeConnect();
        }
        return listKH;
    }
}
