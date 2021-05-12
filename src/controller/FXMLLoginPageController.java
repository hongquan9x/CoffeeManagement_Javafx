/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.LoginSuccessfully;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.NhanVien;
import model.NhanVienDAO;

/**
 * FXML Controller class
 *
 * @author redar
 */
public class FXMLLoginPageController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    private FontAwesomeIcon iconUsername;
    private FontAwesomeIcon iconPassword;
    double x = 0, y = 0;
    @FXML
    private AnchorPane anchor;
    @FXML
    private HBox hbxUser;
    @FXML
    private HBox hbxPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnSingup;
    @FXML
    private StackPane parentContainer;
    private NhanVienDAO nvDAO;
    public LoginSuccessfully loginInfor;

    /**
     * Initializes the controller class.
     */
    public FXMLLoginPageController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animation();
        //tfFocus();
    }

    public void animation() {
        Duration duration = Duration.millis(2500);
        //Create new translate transition
        TranslateTransition transition = new TranslateTransition(duration, hbxUser);
        //Move in X axis by +200
        transition.setByX(0);
        //Move in Y axis by +100
        transition.setByY(50);
        //Go back to previous position after 2.5 seconds
        transition.setAutoReverse(true);
        //Repeat animation twice
        transition.setCycleCount(1);
        transition.play();

        TranslateTransition tran2 = new TranslateTransition(duration, hbxPassword);
        tran2.setByX(0);
        tran2.setByY(50);
        tran2.setAutoReverse(true);
        tran2.setCycleCount(1);
        tran2.play();

    }

    @FXML
    private void handleMouseEntered2(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        btnLogin.setEffect(shadow);
    }

    @FXML
    private void handleMouseEntered(MouseEvent event) {
        DropShadow shadow = new DropShadow();
        btnSingup.setEffect(shadow);
    }

    @FXML
    private void handleMouseExited2(MouseEvent event) {
        btnLogin.setEffect(null);
    }

    @FXML
    private void handleMouseExited(MouseEvent event) {
        btnSingup.setEffect(null);
    }

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        LoginSuccessfully.roll = -1;
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLMain.fxml"));
        Scene scene = btnSingup.getScene();
        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchor);
        });
        timeline.play();
    }

    @FXML
    private void handleLogin(ActionEvent event) throws SQLException, IOException {
        nvDAO = new NhanVienDAO();

        String username = tfUsername.getText();
        String password = tfPassword.getText();
        NhanVien nv = new NhanVien();
        nv.setUsername(username);
        nv.setPassword(password);
        if (nvDAO.checkLogin(nv) == true) {
            LoginSuccessfully.username = username;
            LoginSuccessfully.roll = (nvDAO.getRoll(nv));

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Thông tin đăng nhập");
            al.setHeaderText("Đăng nhập thành công");
            if (nvDAO.getRoll(nv) == 0) {
                al.setContentText("Bạn đang đăng nhập với quyền ADMIN");
            } else {
                al.setContentText("Bạn đang đăng nhập với quyền USER");
            }
            al.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLMain.fxml"));
            Scene scene = btnLogin.getScene();
            root.translateYProperty().set(scene.getHeight());

            parentContainer.getChildren().add(root);

            Timeline timeline = new Timeline();

            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);

            timeline.setOnFinished(t -> {
                parentContainer.setPrefSize(1131, 863);
                parentContainer.getChildren().remove(anchor);
            });
            timeline.play();
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Thông tin đăng nhập");
            al.setHeaderText("Lỗi đăng nhập");
            al.setContentText("Đăng nhập thất bại vui lòng thử lại hoặc bấm vào lấy lại mật khẩu!");
            al.showAndWait();
        }
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        tfUsername.setStyle("-fx-background-color: gold;");
    }

    @FXML
    private void onKeyReleased(KeyEvent event) {
        tfUsername.setStyle(null);
    }

    private void tfFocus() {
        tfUsername.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (newPropertyValue) {
                tfUsername.setStyle("-fx-background-color: white;");
            } else {
                tfUsername.setStyle(null);
            }
        });
        tfPassword.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (newPropertyValue) {
                tfPassword.setStyle("-fx-background-color: white;");
            } else {
                tfPassword.setStyle(null);
            }
        });
    }
}
