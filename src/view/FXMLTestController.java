/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author redar
 */
public class FXMLTestController implements Initializable {

    @FXML
    private Pane paneContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleShowSecondPane(ActionEvent event) throws IOException {
        Pane newPane  = FXMLLoader.load(getClass().getResource("/view/FXMLPaneNeedToShow.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }

    @FXML
    private void handleShowThirdPane(ActionEvent event) throws IOException {
        Pane newPane  = FXMLLoader.load(getClass().getResource("/view/FXMLThirdPane.fxml"));
        paneContainer.getChildren().clear();
        paneContainer.getChildren().add(newPane);
    }
    
}
