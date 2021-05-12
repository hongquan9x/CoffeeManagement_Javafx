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
import java.sql.Timestamp;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author redar
 */
public class HoaDonDAO {

    private ConnectDB cn;
    private PreparedStatement ps;
    private ResultSet rs;

    public HoaDonDAO() {
        cn = new ConnectDB();
    }

    public void insertHD(HoaDon hd) throws SQLException {
        try {
            cn.openConnect();
            String sql = "insert into hoadon (thoigian, manv, makh, maban, giamgia) values (?,?,?,?,?)";
            ps = cn.getCn().prepareCall(sql);
            //java.util.Date d = hd.getThoigian();
            //java.sql.Date sd = new java.sql.Date(d.getTime());
            ps.setTimestamp(1, hd.getThoigian());
            ps.setString(2, hd.getManv());
            ps.setString(3, hd.getMakh());
            ps.setInt(4, hd.getMaban());
            ps.setInt(5, hd.getGiamgia());
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

    }

    public void insertCTHD(ChiTietHoaDon cthd) throws SQLException {
        try {
            cn.openConnect();
            String sql = "insert into chitiethoadon values(?,?,?)";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, cthd.getMahd());
            ps.setString(2, cthd.getMamon());
            ps.setInt(3, cthd.getSoluong());
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
    }

    public ObservableList<HoaDon> selectAll() throws SQLException {
        ObservableList<HoaDon> hdList = FXCollections.observableArrayList();
        try {
            cn.openConnect();
            String sql = "select * from hoadon";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            HoaDon hd;
            while (rs.next()) {
                hd = new HoaDon(rs.getString("mahd"), rs.getTimestamp("thoigian"),
                          rs.getString("makh"), rs.getString("manv"), rs.getInt("maban"), rs.getInt("giamgia"));
                hdList.add(hd);
            }
        } catch (SQLException ex) {
        } finally {
            cn.closeConnect();
        }
        return hdList;
    }

    public int getMaHD(int maban) throws SQLException {
        int mahd = 0;
        try {
            cn.openConnect();
            String sql = "select mahd from hoadon where maban = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, maban);
            rs = ps.executeQuery();
            while (rs.next()) {
                mahd = rs.getInt("mahd");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return mahd;
    }

    public String getMaMon(int maban) throws SQLException {
        String mamon = null;
        try {
            cn.openConnect();
            String sql = "select mamon from hoadon where maban = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, maban);
            ps.executeQuery();
            while (rs.next()) {
                mamon = rs.getString("mahd");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return mamon;
    }

    public void updateCTHD(int mahd, String mamon, int soluong) throws SQLException {
        try {
            cn.openConnect();
            String sql = "update chitiethoadon set soluong = ? where mamon = ? and mahd = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, soluong);
            ps.setString(2, mamon);
            ps.setInt(3, mahd);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
    }

    public boolean searchMaMon(String mamon, int mahd) throws SQLException {
        boolean existed = false;
        try {
            cn.openConnect();
            String sql = "select mamon from chitiethoadon where mahd = ? and mamon = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, mahd);
            ps.setString(2, mamon);
            rs = ps.executeQuery();
            if (rs.next()) {
                existed = true;
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
        return existed;
    }

    public Timestamp getTG(int mahd) throws SQLException {
        Timestamp tg = null;
        try {
            cn.openConnect();
            String sql = "select thoigian from hoadon where mahd = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, mahd);
            rs = ps.executeQuery();
            if (rs.next()) {
                tg = rs.getTimestamp("thoigian");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return tg;
    }

    public float totalToPay(int mahd) throws SQLException {
        float total = 0;
        try {
            cn.openConnect();
            String sql = "select (sum(soluong*dongia)*(100-giamgia)/100) as total from chitiethoadon"
                      + " join hoadon using (mahd) join mon using (mamon) where mahd = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, mahd);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getFloat("total");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return total;
    }

    public void applyDiscount(int giamgia, int mahd) throws SQLException {
        try {
            cn.openConnect();
            String sql = "update hoadon set giamgia = ? where mahd = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, giamgia);
            ps.setInt(2, mahd);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
    }

    public int checkDiscount(int mahd) throws SQLException {
        int giamgia = 0;
        try {
            cn.openConnect();
            String sql = "select giamgia from hoadon where mahd = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setInt(1, mahd);
            rs = ps.executeQuery();
            if (rs.next()) {
                giamgia = rs.getInt("giamgia");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }
        return giamgia;
    }

    public float totalByLoaiMon(String maloai) throws SQLException {
        float total = 0;
        try {
            cn.openConnect();
            String sql = "select (sum(soluong*dongia)*(100-giamgia)/100) as total "
                      + "from chitiethoadon join hoadon using (mahd) "
                      + "join mon using (mamon) "
                      + "join loaimon using (maloai) where tenloai = ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setString(1, maloai);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getFloat("total");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return total;
    }

    public float grandTotal() throws SQLException {
        float total = 0;
        try {
            cn.openConnect();
            String sql = "select (sum(soluong*dongia*(100-giamgia)/100)) as total "
                      + "from chitiethoadon join hoadon using (mahd) "
                      + "join mon using (mamon) "
                      + "join loaimon using (maloai) ";
            ps = cn.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getFloat("total");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return total;
    }

    public float grandTotalByDate(Date from, Date to) throws SQLException {
        float total = 0;
        try {
            cn.openConnect();
            String sql = "select (sum(soluong*dongia*(100-giamgia)/100)) as total "
                      + "from chitiethoadon join hoadon using (mahd) "
                      + "join mon using (mamon) "
                      + "join loaimon using (maloai) "
                      + "where thoigian between ? and ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setDate(1, from);
            ps.setDate(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getFloat("total");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return total;
    }

    public float totalByLoaiMonAndDate(String maloai, Date from, Date to) throws SQLException {
        float total = 0;
        try {
            cn.openConnect();
            String sql = "select (sum(soluong*dongia)*(100-giamgia)/100) as total "
                      + "from chitiethoadon join hoadon using (mahd) "
                      + "join mon using (mamon) "
                      + "join loaimon using (maloai) where tenloai = ? and thoigian between ? and ?";
            ps = cn.getCn().prepareCall(sql);
            ps.setString(1, maloai);
            ps.setDate(2, from);
            ps.setDate(3, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getFloat("total");
            }
        } catch (SQLException e) {
        } finally {
            cn.closeConnect();
        }

        return total;
    }
}
