/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author redar
 */
public class FXMLSignupController implements Initializable {

    @FXML
    private JFXButton btnLogin;
    @FXML
    private ImageView btnBack;
    @FXML
    private JFXButton btnLogin1;
    @FXML
    private AnchorPane anchorRoot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleMouseExited2(MouseEvent event) {
    }

    @FXML
    private void handleMouseEntered2(MouseEvent event) {
    }

    @FXML
    private void handleBack(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLLoginPage.fxml"));
        Scene scene = btnBack.getScene();
        
        root.translateXProperty().set(scene.getWidth());
        
        StackPane parentCon = (StackPane) scene.getRoot();  
        parentCon.getChildren().add(root);
        
        Timeline timeline = new Timeline();
        
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        
        timeline.setOnFinished(t -> {
            parentCon.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }
    
}
