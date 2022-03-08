/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class MainController implements Initializable {

    @FXML
    private Button btnTeams;
    @FXML
    private Button btnScrimms;
    @FXML
    private Button btnTeamsF;
    @FXML
    private Button btnScrimmsF;
    @FXML
    private Button btnMedia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }    

    @FXML
    private void Teams(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Equipe.fxml"));
        Stage window = (Stage) btnTeams.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    @FXML
    private void Scrimms(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scrimm.fxml"));
        Stage window = (Stage) btnScrimms.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void TeamsF(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EquipeFront.fxml"));
        Stage window = (Stage) btnTeamsF.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void ScrimmsF(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scrimm.fxml"));
        Stage window = (Stage) btnScrimmsF.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToMedia(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Music.fxml"));
        Stage window = (Stage) btnMedia.getScene().getWindow();
        window.setScene(new Scene(root));
       
    }

    

    
    
    
   
    
}
