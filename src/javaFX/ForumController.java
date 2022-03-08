/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFX;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import pidev.entities.Forum;
import pidev.services.ForumCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ForumController implements Initializable {

    ForumCRUD service= new ForumCRUD();
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_vider;
    @FXML
    private TextArea sujet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        if(sujet.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs vide", ButtonType.CLOSE);
            a.show();
        }
        else {
            
        
        Forum forum=new Forum(sujet.getText());
        service.ajouterForum(forum);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Forum ajouter", ButtonType.OK);
        alert.show();
         try {
            Parent root = FXMLLoader.load(getClass().getResource("ListOfForum.fxml"));
            Stage myWindow = (Stage) sujet.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ListOfForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }

    @FXML
    private void reset(ActionEvent event) {
        sujet.setText("");
    }
    
}
