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
public class GoiMonDAO {
    private final ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public GoiMonDAO(){
        cn = new ConnectDB();
    }
    
    public ObservableList<GoiMon> getListOrder(int mahd) throws SQLException{
        ObservableList<GoiMon> listOrder = FXCollections.observableArrayList();
        try{
            cn.openConnect();
            String sql = "select tenmon, dvt, dongia, soluong from chitiethoadon join hoadon using (mahd) join mon using (mamon) where mahd = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, mahd);
            rs = ps.executeQuery();
            GoiMon order;
            while (rs.next()){
                order = new GoiMon(rs.getString("tenmon"), rs.getString("dvt"), 
                                   rs.getFloat("dongia"), rs.getInt("soluong"));
                listOrder.add(order);
            }
        }catch(SQLException ex){
        }finally{
            cn.closeConnect();
        }
        return listOrder;
    }
    
    
}
