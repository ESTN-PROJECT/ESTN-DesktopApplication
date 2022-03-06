/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DALI CHARFEDDINE
 */
public class ACCEUILController implements Initializable {

    @FXML
    private Button cat;
    @FXML
    private Button produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cat(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
        Stage window = (Stage) cat.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void produit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        Stage window = (Stage) cat.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    
    
}
