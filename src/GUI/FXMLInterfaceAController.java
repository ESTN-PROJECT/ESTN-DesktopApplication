/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author AZIZ
 */
public class FXMLInterfaceAController implements Initializable {

    @FXML
    private Button interface_participation_events;
    @FXML
    private Button interface_admin_events;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    private void events(ActionEvent event) throws IOException {
      
        
        
    }
    
    /*
    @FXML
    private void GoToEvents(ActionEvent event) throws IOException {
           try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/FXMLEvenement.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLInterfaceAController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */

    @FXML
    private void GoToParticiaptions(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLParticipation.fxml"));
        Stage window = (Stage) interface_participation_events.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));
        Stage window = (Stage) interface_admin_events.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
