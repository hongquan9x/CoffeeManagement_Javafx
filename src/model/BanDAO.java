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
import model.Ban;

/**
 *
 * @author redar
 */
public class BanDAO {
    private final ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public BanDAO(){
        cn = new ConnectDB();
    }
    
    public ObservableList<Ban> getBan() throws SQLException{
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        try{
            cn.openConnect();
            String sql = "select * from ban";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            Ban b;
            while (rs.next()){
                b = new Ban(rs.getString("maban"), rs.getString("tenban"), rs.getInt("trangthai"));
                listBan.add(b);
            }
        }catch(SQLException ex){
        }finally{
            cn.closeConnect();
        }
        return listBan;
    }
    
    public int countBanTrong() throws SQLException{
        int banTrong = 0;
        try{
            cn.openConnect();
            String sql = "select count(*) from ban where trangthai = 0";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                banTrong = rs.getInt("count(*)");
            }
        }catch(SQLException e){
        }finally{
            cn.closeConnect();
        }
        return banTrong;      
    }
    public int countBanPV() throws SQLException{
        int banPV = 0;
        try{
            cn.openConnect();
            String sql = "select count(*) from ban where trangthai = 1";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                banPV = rs.getInt("count(*)");
            }
        }catch(SQLException e){
        }finally{
            cn.closeConnect();
        }
        return banPV;      
    }
    
    public void trongBan(int maban) throws SQLException{
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        try{
            cn.openConnect();
            String sql = "update ban set trangthai = 0 where maban = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, maban);
            ps.executeUpdate();
        }catch(SQLException e){
        }finally{
           cn.closeConnect();
        }
    }
    
    public void datBan(int maban) throws SQLException{
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        try{
            cn.openConnect();
            String sql = "update ban set trangthai = 1 where maban = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, maban);
            ps.executeUpdate();
        }catch(SQLException e){            
        }finally{
            cn.closeConnect();
        }
    }
}
