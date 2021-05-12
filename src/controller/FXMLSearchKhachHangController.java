/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.KhachHang;
import model.KhachHangDAO;

/**
 * FXML Controller class
 *
 * @author redar
 */
public class FXMLSearchKhachHangController implements Initializable {
    
    public interface SearchKHDelegate{
        void onSearch(KhachHang kh);
    }
    
    public static SearchKHDelegate listener;
    

    @FXML
    private TableView<KhachHang> tbvKhachHang;
    @FXML
    private TableColumn<KhachHang, String> clMaKH;
    @FXML
    private TableColumn<KhachHang, String> clTenKH;
    @FXML
    private TableColumn<KhachHang, String> clDiaChiKH;
    @FXML
    private TableColumn<KhachHang, String> clSDTKH;
    @FXML
    private TableColumn<KhachHang, Float> clDiemTichLuy;
    @FXML
    private JFXButton btnHuy;
    @FXML
    private JFXButton btnOKKH;
    private KhachHangDAO khDAO;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        khDAO = new KhachHangDAO();
        try {
            setTBVKhachHang();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSearchKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
       //FXMLSearchKhachHangController controller = new FXMLSearchKhachHangController();
  

    }
    
    public void setTBVKhachHang() throws SQLException{       
        ObservableList<KhachHang> khList = FXCollections.observableArrayList();
        khList = khDAO.getKH();
        clMaKH.setCellValueFactory(new PropertyValueFactory<>("makh"));
        clTenKH.setCellValueFactory(new PropertyValueFactory<>("tenkh"));
        clDiaChiKH.setCellValueFactory(new PropertyValueFactory<>("diachi"));
        clSDTKH.setCellValueFactory(new PropertyValueFactory<>("sodt"));
        clDiemTichLuy.setCellValueFactory(new PropertyValueFactory<>("diemtichluy"));
        tbvKhachHang.setItems(khList);
    }
    
    @FXML
    private void handleHuy(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnOKKH.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleOKKH(ActionEvent event) throws IOException {
        KhachHang kh = tbvKhachHang.getSelectionModel().getSelectedItem();
        if (listener!= null){
            listener.onSearch(kh);
            
        }
        Stage stage = (Stage) btnOKKH.getScene().getWindow();
        stage.close();
    }
    
}
