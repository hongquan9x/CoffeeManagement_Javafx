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
public class TonKhoDAO {

    private final ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public TonKhoDAO() {
        cn = new ConnectDB();
    }

    public ObservableList<TonKho> getTonKho() throws SQLException {
        ObservableList<TonKho> tkList = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "select mamon, tenmon, maloai, dvt, dongia, soluongton from mon join loaimon using (maloai)";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            TonKho tk;
            while (rs.next()) {
                tk = new TonKho(rs.getString("mamon"), rs.getString("tenmon"), rs.getString("maloai"), rs.getString("dvt"),
                          rs.getInt("dongia"), rs.getInt("soluongton"));
                tkList.add(tk);
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return tkList;
    }
}
