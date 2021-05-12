/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ConnectDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author redar
 */
public class BaoCaoDAO {

    private final ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public BaoCaoDAO() {
        cn = new ConnectDB();
    }

    public ObservableList<BaoCao> getListReportByDate(Date from, Date to) throws SQLException {
        ObservableList<BaoCao> listReport = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "select mahd, thoigian, tennv, tenmon, dvt, soluong from chitiethoadon "
                      + "join hoadon using (mahd) join mon using (mamon) join loaimon using (maloai)"
                      + "join khachhang using (makh) "
                      + "join nhanvien using (manv) where thoigian between ? and ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            BaoCao report;
            while (rs.next()) {
                report = new BaoCao(rs.getInt("mahd"), rs.getTimestamp("thoigian"), rs.getString("tennv"), rs.getString("tenmon"),
                                    rs.getString("dvt"), rs.getInt("soluong"));
                listReport.add(report);
            }
        } catch (SQLException ex) {
        } finally {
            cn.closeConnect();
        }
        return listReport;
    }
    
    public ObservableList<BaoCao> getListReport() throws SQLException {
        ObservableList<BaoCao> listReport = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "select mahd, thoigian, tennv, tenmon, dvt, soluong from chitiethoadon "
                      + "join hoadon using (mahd) join mon using (mamon) join loaimon using (maloai)"
                      + "join khachhang using (makh) "
                      + "join nhanvien using (manv)";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            BaoCao report;
            while (rs.next()) {
                report = new BaoCao(rs.getInt("mahd"), rs.getTimestamp("thoigian"), rs.getString("tennv"), rs.getString("tenmon"),
                                    rs.getString("dvt"), rs.getInt("soluong"));
                listReport.add(report);
            }
        } catch (SQLException ex) {
        } finally {
            cn.closeConnect();
        }
        return listReport;
    }
}
