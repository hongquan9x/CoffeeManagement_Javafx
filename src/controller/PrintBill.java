/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author redar
 */
public class PrintBill extends JFrame {

    private final ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PrintBill() throws SQLException {
        cn = new ConnectDB();
    }

    public void showReport() throws SQLException, ClassNotFoundException, IOException {
        Connection conn = cn.Connect();
        try {

            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\redar\\OneDrive\\Documents\\Java\\JavaFX\\FirstApp\\src\\controller\\Bills.jrxml");
            JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            JRViewer viewer = new JRViewer(JasperPrint);
            viewer.setOpaque(true);
            viewer.setVisible(true);
            this.add(viewer);
            this.setSize(1000, 1000);
            this.setVisible(true);

        } catch (JRException e) {
        }
    }
}
