/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ConnectDB;
import java.sql.SQLException;

/**
 *
 * @author redar
 */
public class TestConnect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        ConnectDB test = new ConnectDB();
        if (test.openConnect()) {
            System.out.println("Connected");
        }
        test.closeConnect();
    }
    
}
