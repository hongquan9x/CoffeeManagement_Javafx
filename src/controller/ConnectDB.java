/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author redar
 */
public class ConnectDB {
    Connection cn;
    
    public ConnectDB(){
       cn = null;
    }
    
    public Connection Connect() throws SQLException, ClassNotFoundException{
        System.setProperty("com.mysql.jdbc.Driver","");
        //Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlycafe?characterEncoding=UTF-8","root","");
        return conn;
    }
    
    public boolean openConnect() throws SQLException{
        if (cn == null){
            System.setProperty("com.mysql.jdbc.Driver","");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlycafe?characterEncoding=UTF-8","root","");
            return true;
        }
        return false;
    }
    
    public void closeConnect() throws SQLException{
        if (cn != null){
            cn.close();
            cn = null;
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
}
