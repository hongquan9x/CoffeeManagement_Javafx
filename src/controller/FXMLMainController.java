/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author redar
 */
public class FXMLMainController implements Initializable, FXMLSearchKhachHangController.SearchKHDelegate {

    private BanDAO banDAO;
    private GoiMonDAO gmDAO;
    private HoaDonDAO hdDAO;
    private KhachHangDAO khDAO;
    private MonDAO monDAO;
    private NhanVienDAO nvDAO;
    private BaoCaoDAO bcDAO;
    private TonKhoDAO tkDAO;

    @FXML
    private JFXButton btnThemBan;
    @FXML
    private JFXButton btnXoaBan;
    @FXML
    private JFXButton btnSuaBan;
    @FXML
    private JFXButton btnPay;
    @FXML
    private JFXButton btnReport;
    @FXML
    private JFXButton btnLogout;
    private FlowPane flowPaneBan;
    @FXML
    private Label lblBantrong;
    @FXML
    private Label lblBanPV;
    @FXML
    private Label lblTime;
    @FXML
    private ScrollPane scrollPaneBan;
    @FXML
    private Label lblTenKH;
    @FXML
    private Label lblUserLogined;
    private NhanVien nv;
    private FXMLLoginPageController login;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXButton btnSearchKH;
    @FXML
    private JFXComboBox<String> cbbMon;
    @FXML
    private TableView<Mon> tbvMon;
    @FXML
    private TableColumn<Mon, String> clTenMon;
    @FXML
    private TableColumn<Mon, Integer> clDVT;
    @FXML
    private TableColumn<Mon, Float> clDonGIa;

    private String selectedCBB;
    @FXML
    private Label lblGoiMon;
    @FXML
    private Spinner<Integer> spnSoLuong;
    @FXML
    private Label lblBanTT;
    @FXML
    private JFXButton btnHuyMon;
    @FXML
    private JFXButton btnThemMon;
    @FXML
    private JFXButton btnHuyBan;
    @FXML
    private JFXButton btnDatBan;
    @FXML
    private Label lblBanSelected;
    private final ToggleGroup group = new ToggleGroup();
    private static int index;
    @FXML
    private Label lblThoiGianVao;
    @FXML
    private TableView<GoiMon> tbvMonTT;
    @FXML
    private TableColumn<GoiMon, String> clTenMonTT;
    @FXML
    private TableColumn<GoiMon, String> clDVTTT;
    @FXML
    private TableColumn<GoiMon, Float> clDonGIaTT;
    @FXML
    private TableColumn<GoiMon, Integer> clSoLuongTT;
    @FXML
    private TextField tfSDTKhachHang;
    private ObservableList<GoiMon> gmList = FXCollections.observableArrayList();
    @FXML
    private TextField tfGiamGia;
    @FXML
    private Label lbTotal;
    @FXML
    private JFXButton btnGiamGia;
    @FXML
    private Label lblThongBao;
    @FXML
    private PieChart pieChartLoaiMon;
    @FXML
    private Label lblGrandTotal;
    @FXML
    private ToggleGroup tgGender;
    @FXML
    private JFXButton btnBaoCao;
    @FXML
    private JFXDatePicker dpFromDate;
    @FXML
    private JFXDatePicker dpToDate;
    @FXML
    private Tab tabBanHang;
    @FXML
    private Tab tabQLNS;
    @FXML
    private Tab tabQLHH;
    @FXML
    private Tab tabBaoCao;
    @FXML
    private TableView<BaoCao> tbvBaoCao;
    @FXML
    private TableColumn<BaoCao, Integer> clMaHDBC;
    @FXML
    private TableColumn<BaoCao, Timestamp> clNgayThangBC;
    @FXML
    private TableColumn<BaoCao, String> clNhanVienBC;
    @FXML
    private TableColumn<BaoCao, String> clMonBC;
    @FXML
    private TableColumn<BaoCao, String> clDVTBC;
    @FXML
    private TableColumn<BaoCao, Integer> clSoLuongBC;
    @FXML
    private TableView<NhanVien> tbvNhanVien;
    @FXML
    private TableColumn<NhanVien, String> clMaNV;
    @FXML
    private TableColumn<NhanVien, String> clTenNV;
    @FXML
    private TableColumn<NhanVien, String> clNgaySinhNV;
    @FXML
    private TableColumn<NhanVien, String> clDiaChiNV;
    @FXML
    private TableColumn<NhanVien, String> clSDTNV;
    private TableView<TonKho> tbvTonKho;
    private TableColumn<TonKho, String> clMaMonTK;
    private TableColumn<TonKho, String> clTenMonTK;
    private TableColumn<TonKho, String> clMaLoaiTK;
    private TableColumn<TonKho, String> clDVTTK;
    private TableColumn<TonKho, Integer> clDonGiaTK;
    private TableColumn<TonKho, Integer> clSLTTK;
    @FXML
    private JFXButton btnPrintBill;
    @FXML
    private JFXButton btnThemNV;
    @FXML
    private JFXButton btnXoaNV;
    @FXML
    private JFXButton btnSuaNV;
    @FXML
    private JFXButton btnXoaMon;
    @FXML
    private JFXButton btnSuaMon;
    @FXML
    private JFXButton btnLoaiMon;
    @FXML
    private JFXButton btnXoaLoaiMon;
    @FXML
    private JFXButton btnSuaLoaiMon;
    @FXML
    private JFXButton btnBCTonKho;
    @FXML
    private Label lblGoiMon1;
    @FXML
    private Spinner<?> spnSoLuong1;
    @FXML
    private TextField tfSDTKhachHang1;
    @FXML
    private JFXButton btnSearchKH1;
    @FXML
    private FlowPane flowPaneTable;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gmDAO = new GoiMonDAO();
        khDAO = new KhachHangDAO();
        hdDAO = new HoaDonDAO();
        banDAO = new BanDAO();
        nvDAO = new NhanVienDAO();
        monDAO = new MonDAO();
        bcDAO = new BaoCaoDAO();
        tkDAO = new TonKhoDAO();
        //fill table
        try {
            printTable();
            displayMon();
            lblBantrong.setText("" + banDAO.countBanTrong());
            lblBanPV.setText("" + banDAO.countBanPV());
            setPolicy();
            displayPieChart();
            setTBVNhanVien();
//            setTBVTonKho();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //show current time
        showTime();
        //align tableview
        btnDatBan.setVisible(false);
        btnHuyBan.setVisible(false);
        clTenMon.setStyle("-fx-alignment: CENTER-LEFT;");
        clTenMonTT.setStyle("-fx-alignment: CENTER-LEFT;");
        clSoLuongBC.setStyle("-fx-alignment: CENTER;");
        clDVTBC.setStyle("-fx-alignment: CENTER;");
//      clDonGiaTK.setStyle("-fx-alignment: CENTER;");
//      clSLTTK.setStyle("-fx-alignment: CENTER;");
        //display Spinner
        setValueSpinner();
        lblGoiMon.setMinWidth(Region.USE_PREF_SIZE);
        lblGoiMon.setVisible(false);
        spnSoLuong.setVisible(false);
        //GiamGia force numeric only        
        tfGiamGia.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfGiamGia.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    public void printTable() throws SQLException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        listBan = banDAO.getBan();
        group.getToggles().clear();
        ToggleButton[] b = new ToggleButton[listBan.size()];
        index = group.getToggles().indexOf(group.getSelectedToggle());
        btnPay.setDisable(true);
        for (int i = 0; i < listBan.size(); i++) {
            final int k = i;
            b[i] = new ToggleButton(listBan.get(i).getTenban());
            b[i].setMinWidth(130);
            Image image = new Image(getClass().getResourceAsStream("/image/chair.png"));
            b[i].setGraphic(new ImageView(image));

            b[i].setStyle("-fx-font: 16 arial; -fx-background-color: #FFFFFF; -fx-border-color:#3140E7; -fx-border-radius: 5px;"
                      + "-fx-border-style: dotted; -fx-border-width: 2px;");
            if (listBan.get(i).getTrangthai() == 1) {
                b[i].setStyle("-fx-font: 16 arial; -fx-background-color: #b6e7c9; -fx-border-color:#3140E7; -fx-border-radius: 5px;");
            }
            b[i].setToggleGroup(group);
            //setEffect
            DropShadow shadow = new DropShadow();
            b[i].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                b[k].setEffect(shadow);
            });

            b[i].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                b[k].setEffect(null);
            });
            //handle Action
            String text = listBan.get(i).getTenban().toUpperCase();
            int mahd = hdDAO.getMaHD(Integer.parseInt(listBan.get(i).getMaban()));
            ObservableList<GoiMon> gmlist = FXCollections.observableArrayList();
            gmList = gmDAO.getListOrder(mahd);
            final ObservableList gm = gmlist;
            final Timestamp tg = hdDAO.getTG(mahd);
            final int index1 = group.getToggles().indexOf(group.getSelectedToggle());
            b[i].setOnAction((ActionEvent event) -> {
                if (b[k].isSelected()) {
                    ObservableList<GoiMon> gmlist1 = FXCollections.observableArrayList();
                    ObservableList<Ban> listBan1 = FXCollections.observableArrayList();
                    DecimalFormat format = new DecimalFormat("#,###,###");
                    try {
                        listBan1 = banDAO.getBan();
                        int maban = Integer.parseInt(listBan1.get(k).getMaban());
                        int mahoadon = hdDAO.getMaHD(maban);
                        gmlist1 = gmDAO.getListOrder(mahoadon);
                        float total = hdDAO.totalToPay(mahoadon);
                        int giamgia = hdDAO.checkDiscount(mahoadon);
                        if (giamgia != 0) {
                            lblThongBao.setText("Bàn này đã được áp dụng giảm giá " + giamgia + " % !!!");
                        } else {
                            lblThongBao.setText(null);
                        }
                        lbTotal.setText("" + format.format(total));
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lblBanSelected.setVisible(true);
                    lblBanTT.setText(text);
                    lblBanSelected.setText(text);
                    btnDatBan.setVisible(true);
                    btnHuyBan.setVisible(true);
                    tbvMonTT.setVisible(true);
                    setTBVHoaDon();
                    tbvMonTT.setItems(gmlist1);
                    if (tg == null) {
                        lblThoiGianVao.setText(null);
                    } else {
                        lblThoiGianVao.setText(simpleDateFormat.format(tg));
                    }
                    if (listBan1.get(k).getTrangthai() == 1) {
                        btnPay.setDisable(false);
                    } else {
                        btnPay.setDisable(true);
                        tbvMonTT.setVisible(false);
                        lblThoiGianVao.setText(null);
                        lbTotal.setText(null);
                        lblThongBao.setText(null);
                    }

                } else {
                    lblBanTT.setText("Chọn bàn?");
                    lblBanSelected.setVisible(false);
                    btnDatBan.setVisible(false);
                    btnHuyBan.setVisible(false);
                    tbvMonTT.setVisible(false);
                    lblThoiGianVao.setText(null);
                    lbTotal.setText(null);
                    lblThongBao.setText(null);
                    btnPay.setDisable(true);
                }
            });
        }

        flowPaneBan.getChildren().clear();
        for (int i = 0; i < listBan.size(); i++) {
            flowPaneBan.getChildren().add(b[i]);
        }

    }

    public void setPolicy() throws SQLException {
        nv = new NhanVien();
        String tennv = LoginSuccessfully.username;
        nv.setUsername(tennv);
        if (LoginSuccessfully.username == null) {
            lblUserLogined.setText("Phiên dùng thử");
        } else {
            lblUserLogined.setText(nvDAO.getName(nv));
        }
        if (LoginSuccessfully.roll == 0) {
            tabBaoCao.setDisable(false);
            tabQLHH.setDisable(false);
            tabQLNS.setDisable(false);
        } else {
            tabBaoCao.setDisable(true);
            tabQLHH.setDisable(true);
            tabQLNS.setDisable(true);
        }
    }

    public void showTime() {
        Thread timerThread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                });
            }
        });
        timerThread.start();
    }

    @FXML
    private void handleMouseExited(MouseEvent event) {
        ((Button) event.getTarget()).setEffect(null);
    }

    @FXML
    private void handleMouseEntered(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        ((Button) event.getTarget()).setEffect(shadow);
    }

    @FXML
    private void handleThemBan(ActionEvent event) {
    }

    @FXML
    private void handleXoaBan(ActionEvent event) {
    }

    @FXML
    private void handleSuaBan(ActionEvent event) {
    }

    @FXML
    private void handlePay(ActionEvent event) throws SQLException {
        banDAO = new BanDAO();
        DecimalFormat format = new DecimalFormat("#,###,###");
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        listBan = banDAO.getBan();
        final int index = group.getToggles().indexOf(group.getSelectedToggle());
        int mahd = hdDAO.getMaHD(Integer.parseInt(listBan.get(index).getMaban()));
        float total = hdDAO.totalToPay(mahd);
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Thanh toán");
        al.setHeaderText("Xác nhận thanh toán");
        al.setContentText("Thanh toán cho " + listBan.get(index).getTenban() + " với số tiền " + format.format(total) + " VNĐ?");
        Optional<ButtonType> result = al.showAndWait();
        if (result.get() == ButtonType.OK) {
            banDAO.trongBan(Integer.parseInt(listBan.get(index).getMaban()));
            printTable();
            btnDatBan.setVisible(false);
            btnHuyBan.setVisible(false);
            try {
                lblBantrong.setText("" + banDAO.countBanTrong());
                lblBanPV.setText("" + banDAO.countBanPV());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblThoiGianVao.setText(null);
            tbvMonTT.setVisible(false);
            tbvMonTT.getItems().clear();
            lblThongBao.setText(null);
            lbTotal.setText(null);
            displayPieChart();
        }
    }

    @FXML
    private void handleReport(ActionEvent event) {
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        LoginSuccessfully.username = null;
        LoginSuccessfully.roll = -1;
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLLoginPage.fxml"));
        Scene scene = btnLogout.getScene();
        root.translateXProperty().set(scene.getWidth());
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchor);
        });
        timeline.play();
    }

    private void displayMon() throws SQLException {
        ObservableList<String> loaiMon = FXCollections.observableArrayList();
        ObservableList<LoaiMon> lm = monDAO.getLoaiMon();
        for (LoaiMon loaiMon1 : lm) {
            loaiMon.add(loaiMon1.getTenloai());
        }
        cbbMon.getItems().add("Tất cả");
        cbbMon.getItems().addAll(loaiMon);
        cbbMon.getSelectionModel().selectFirst();
        ObservableList<Mon> listMon = monDAO.getDSMon();
        setDataTableView();
        tbvMon.setItems(listMon);
    }

    @FXML
    private void cbbOnAction(ActionEvent event) throws SQLException {
        selectedCBB = cbbMon.getValue();
        if (selectedCBB.equals("Tất cả")) {
            ObservableList<Mon> listMon = monDAO.getDSMon();
            setDataTableView();
            tbvMon.setItems(listMon);
        } else {
            ObservableList<Mon> listMonByLoai = monDAO.getMonbyLoaiMon(selectedCBB);
            setTBVHoaDon();
            tbvMon.setItems(listMonByLoai);
        }
    }

    public void setDataTableView() {
        //Table View Mon
        tbvMon.getItems().clear();
        DecimalFormat format = new DecimalFormat("#,###,###");
        clTenMon.setCellValueFactory(new PropertyValueFactory<>("tenmon"));
        clDVT.setCellValueFactory(new PropertyValueFactory<>("dvt"));
        clDonGIa.setCellValueFactory(new PropertyValueFactory<>("dongia"));
        clDonGIa.setCellFactory(tablecell -> new TableCell<Mon, Float>() {
            @Override
            protected void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(price));
                }
            }
        });
    }

    public void setTBVHoaDon() {
        //Table View Hoa don
        DecimalFormat format = new DecimalFormat("#,###,###");
        //tbvMonTT.getItems().clear();
        clTenMonTT.setCellValueFactory(new PropertyValueFactory<>("tenmon"));
        clDVTTT.setCellValueFactory(new PropertyValueFactory<>("dvt"));
        clDonGIaTT.setCellValueFactory(new PropertyValueFactory<>("dongia"));
        clSoLuongTT.setCellValueFactory(new PropertyValueFactory<>("soluong"));
        clDonGIaTT.setCellFactory(tablecell -> new TableCell<GoiMon, Float>() {
            @Override
            protected void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(price));
                }
            }
        });
    }

    public void setTBVBaoCao() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        clMaHDBC.setCellValueFactory(new PropertyValueFactory<>("mahd"));
        clNgayThangBC.setCellValueFactory(new PropertyValueFactory<>("thoigian"));
        clNhanVienBC.setCellValueFactory(new PropertyValueFactory<>("tennv"));
        clMonBC.setCellValueFactory(new PropertyValueFactory<>("tenmon"));
        clDVTBC.setCellValueFactory(new PropertyValueFactory<>("dvt"));
        clSoLuongBC.setCellValueFactory(new PropertyValueFactory<>("soluong"));
        clNgayThangBC.setCellFactory(tablecell -> new TableCell<BaoCao, Timestamp>() {
            @Override
            protected void updateItem(Timestamp time, boolean empty) {
                super.updateItem(time, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(simpleDateFormat.format(time));
                }
            }
        });
    }

    public void setTBVNhanVien() throws SQLException {
        clMaNV.setCellValueFactory(new PropertyValueFactory<>("manv"));
        clTenNV.setCellValueFactory(new PropertyValueFactory<>("tennv"));
        clNgaySinhNV.setCellValueFactory(new PropertyValueFactory<>("ngaysinh"));
        clDiaChiNV.setCellValueFactory(new PropertyValueFactory<>("diachi"));
        clSDTNV.setCellValueFactory(new PropertyValueFactory<>("sodt"));
        ObservableList<NhanVien> nvList = FXCollections.observableArrayList();
        nvList = nvDAO.getNhanVien();
        tbvNhanVien.setItems(nvList);
    }

    public void setTBVTonKho() throws SQLException {
        clMaMonTK.setCellValueFactory(new PropertyValueFactory<>("mamon"));
        clTenMonTK.setCellValueFactory(new PropertyValueFactory<>("tenmon"));
        clMaLoaiTK.setCellValueFactory(new PropertyValueFactory<>("maloai"));
        clDVTTK.setCellValueFactory(new PropertyValueFactory<>("dvt"));
        clDonGiaTK.setCellValueFactory(new PropertyValueFactory<>("dongia"));
        clSLTTK.setCellValueFactory(new PropertyValueFactory<>("soluongton"));
        ObservableList<TonKho> tkList = FXCollections.observableArrayList();
        tkList = tkDAO.getTonKho();
        tbvTonKho.setItems(tkList);
    }

    @FXML
    private void tbvMouseClick(MouseEvent event) {
        Mon m = tbvMon.getSelectionModel().getSelectedItem();
        lblGoiMon.setVisible(true);
        spnSoLuong.setVisible(true);
        lblGoiMon.setText(m.getTenmon() + " " + "(" + m.getDvt() + ")");
    }

    public void setValueSpinner() {
        SpinnerValueFactory<Integer> value = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spnSoLuong.setValueFactory(value);
        spnSoLuong.setEditable(true);
        //spnSoLuong.getValueFactory().setValue(1);
    }

    @FXML
    private void handleHuyMon(ActionEvent event) {
    }

    @FXML
    private void handleThemMon(ActionEvent event) throws SQLException {
        Mon m = tbvMon.getSelectionModel().getSelectedItem();
        ChiTietHoaDon cthd = new ChiTietHoaDon();
        DecimalFormat format = new DecimalFormat("#,###,###");
        final int index = group.getToggles().indexOf(group.getSelectedToggle());
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        listBan = banDAO.getBan();
        float total = 0;
        if (index < 0) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Thông tin gọi món");
            al.setHeaderText("Lỗi");
            al.setContentText("Bạn chưa chọn bàn để gọi món!");
            al.showAndWait();
            lbTotal.setText(null);
        } else {
            int maban = Integer.parseInt(listBan.get(index).getMaban());
            final int mahd = hdDAO.getMaHD(maban);
            int soluong = spnSoLuong.getValue();
            String mamon = m.getMamon();
            if (hdDAO.searchMaMon(mamon, mahd) == true) {
                hdDAO.updateCTHD(mahd, mamon, soluong);
                gmList = gmDAO.getListOrder(mahd);
                setTBVHoaDon();
                tbvMonTT.setItems(gmList);
                total = hdDAO.totalToPay(mahd);

            } else {
                cthd.setMahd(mahd);
                cthd.setMamon(mamon);
                cthd.setSoluong(soluong);
                hdDAO.insertCTHD(cthd);
                gmList = gmDAO.getListOrder(mahd);
                setTBVHoaDon();
                tbvMonTT.setItems(gmList);
                total = hdDAO.totalToPay(mahd);
            }
            lbTotal.setText("" + format.format(total));
            spnSoLuong.getValueFactory().setValue(1);
        }
    }

    @FXML
    private void handleHuyBan(ActionEvent event) throws SQLException {
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        listBan = banDAO.getBan();
        index = group.getToggles().indexOf(group.getSelectedToggle());
        if (listBan.get(index).getTrangthai() == 1) {
            banDAO.trongBan(Integer.parseInt(listBan.get(index).getMaban()));
            printTable();
            btnDatBan.setVisible(false);
            btnHuyBan.setVisible(false);
            try {
                lblBantrong.setText("" + banDAO.countBanTrong());
                lblBanPV.setText("" + banDAO.countBanPV());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblThoiGianVao.setText(null);

        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Thông tin hủy bàn");
            al.setHeaderText("Lỗi");
            al.setContentText("Bàn đang trống không thể hủy bàn!");
            al.showAndWait();
        }
    }

    @FXML
    private void handleDatBan(ActionEvent event) throws SQLException {
        banDAO = new BanDAO();
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        listBan = banDAO.getBan();
        final int index = group.getToggles().indexOf(group.getSelectedToggle());
        if (listBan.get(index).getTrangthai() == 0) {
            banDAO.datBan(Integer.parseInt(listBan.get(index).getMaban()));
            printTable();
            btnDatBan.setVisible(false);
            btnHuyBan.setVisible(false);
            try {
                lblBantrong.setText("" + banDAO.countBanTrong());
                lblBanPV.setText("" + banDAO.countBanPV());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            lblBanTT.setText("Chọn bàn?");

        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Thông tin đặt bàn");
            al.setHeaderText("Lỗi");
            al.setContentText("Bàn đang phục vụ không thể đặt bàn!");
            al.showAndWait();
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //tring thoigian = dateFormat.format(date);
        String manv = nvDAO.getMaNVbyUsername(LoginSuccessfully.username);
        listBan = banDAO.getBan();
        int maban = 0;
        if (index < 0) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Thông tin gọi món");
            al.setHeaderText("Lỗi");
            al.setContentText("Bạn chưa chọn bàn để gọi món!");
            al.showAndWait();
        } else {
            maban = Integer.parseInt(listBan.get(index).getMaban());
        }
        HoaDon hd = new HoaDon();
        hd.setMaban(maban);
        hd.setManv(manv);
        java.util.Date date1 = new java.util.Date();
        Timestamp sqlDate = new java.sql.Timestamp(date1.getTime());
        hd.setThoigian(sqlDate);
        hd.setMakh("KH001");
        hd.setGiamgia(0);
        hdDAO.insertHD(hd);
        lblThoiGianVao.setText(dateFormat.format(sqlDate));
    }

    @FXML
    private void handleSearchKH(ActionEvent event) throws IOException {
//        Parent root3 = FXMLLoader.load(getClass().getResource("/view/FXMLSearchKhachHang.fxml"));
//        Scene scene = new Scene(root3);
//        Stage stage = new Stage();
//        if (true) {
//            stage.initModality(Modality.APPLICATION_MODAL);
//        }
//        stage.setScene(scene);
//        stage.show();
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root3 = FXMLLoader.load(getClass().getResource("/view/FXMLSearchKhachHang.fxml"));
        Scene scene = new Scene(root3);
        appStage.setScene(scene);
        appStage.show();
        FXMLSearchKhachHangController.listener = this;

    }

    @FXML
    private void handleGiamGia(ActionEvent event) throws SQLException {
        DecimalFormat format = new DecimalFormat("#,###,###");
        ObservableList<Ban> listBan = FXCollections.observableArrayList();
        listBan = banDAO.getBan();
        final int index = group.getToggles().indexOf(group.getSelectedToggle());
        int maban = Integer.parseInt(listBan.get(index).getMaban());
        int mahd = hdDAO.getMaHD(maban);
        float total = 0;
        total = hdDAO.totalToPay(mahd);
        if (tfGiamGia.getText() == null || tfGiamGia.getText().equals("")) {
            lbTotal.setText("" + format.format(total));
        } else {
            hdDAO.applyDiscount(Integer.parseInt(tfGiamGia.getText()), mahd);
            total = hdDAO.totalToPay(mahd);
            lbTotal.setText("" + format.format(total));
            lblThongBao.setText("Bàn này đã được áp dụng giảm giá " + tfGiamGia.getText() + " % !!!");
        }

    }

    public void displayPieChart() throws SQLException {
        DecimalFormat format = new DecimalFormat("#,###,###");
        float grandTotal = hdDAO.grandTotal();
        ObservableList<String> mList = monDAO.tenMon();
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        ObservableList<BaoCao> listReport = FXCollections.observableArrayList();
        listReport = bcDAO.getListReport();
        setTBVBaoCao();
        tbvBaoCao.setItems(listReport);
        for (String tenloai : mList) {
            float total = hdDAO.totalByLoaiMon(tenloai);
            PieChart.Data pd = new PieChart.Data(tenloai, total / grandTotal);
            data.add(pd);
        }
        pieChartLoaiMon.setData(data);
        lblGrandTotal.setText("" + format.format(grandTotal));

    }

    @FXML
    private void handleBaoCao(ActionEvent event) throws SQLException {
        DecimalFormat format = new DecimalFormat("#,###,###");
        LocalDate from = dpFromDate.getValue();
        LocalDate to = dpToDate.getValue();
        if (from == null || to == null) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Thông tin báo cáo");
            al.setHeaderText("Lỗi");
            al.setContentText("Chọn thời gian để xuất báo cáo!");
            al.showAndWait();
        } else {
            Instant instant = Instant.from(from.atStartOfDay(ZoneId.systemDefault()));
            Date dateFrom = Date.from(instant);
            java.sql.Date fromDate = new java.sql.Date(dateFrom.getTime());
            Instant instant2 = Instant.from(to.atStartOfDay(ZoneId.systemDefault()));
            Date dateTo = Date.from(instant2);
            java.sql.Date toDate = new java.sql.Date(dateTo.getTime());
            float grandTotal = hdDAO.grandTotalByDate(fromDate, toDate);
            ObservableList<String> mList = monDAO.tenMon();
            ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
            for (String tenloai : mList) {
                float total = hdDAO.totalByLoaiMonAndDate(tenloai, fromDate, toDate);
                PieChart.Data pd = new PieChart.Data(tenloai, total / grandTotal);
                data.add(pd);
            }
            pieChartLoaiMon.setData(data);
            lblGrandTotal.setText("" + format.format(grandTotal));
            ObservableList<BaoCao> listReport = FXCollections.observableArrayList();
            listReport = bcDAO.getListReportByDate(fromDate, toDate);
            setTBVBaoCao();
            tbvBaoCao.setItems(listReport);
        }
    }

    @FXML
    private void handlPrintBill(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        PrintBill print = new PrintBill();
        print.showReport();
    }

    @Override
    public void onSearch(KhachHang kh) {
        System.out.println(kh);
    }

    @FXML
    private void handleThemNV(ActionEvent event) {
    }

    @FXML
    private void handleXoaNV(ActionEvent event) {
    }

    @FXML
    private void handleSuaNV(ActionEvent event) {
    }

    @FXML
    private void handleXoaMon(ActionEvent event) {
    }

    @FXML
    private void handleSuaMon(ActionEvent event) {
    }

    @FXML
    private void handleThemLoaiMon(ActionEvent event) {
    }

    @FXML
    private void handleXoaLoaiMon(ActionEvent event) {
    }

    @FXML
    private void handleSuaLoaiMon(ActionEvent event) {
    }

    @FXML
    private void handleBCTonKho(ActionEvent event) {
    }

}
