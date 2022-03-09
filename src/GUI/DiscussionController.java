/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextAlignment;
import Models.Discussion;
import Services.DiscussionCRUD;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class DiscussionController implements Initializable {

    @FXML
    private TextArea contenu;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_vider;
    @FXML
    private Button goback;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void vider(ActionEvent event){
        contenu.setText("");
    }
 
    @FXML
    private void ajouter(ActionEvent event) {
        if(contenu.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs vide", ButtonType.CLOSE);
            a.show();
        }
        else
        {
        Discussion d=new Discussion(contenu.getText());
        DiscussionCRUD discussionCRUD=new DiscussionCRUD();
        discussionCRUD.ajouterDiscussion(d);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Discussion ajouter", ButtonType.OK);
        alert.show();

    }}

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage window = (Stage) goback.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    
}
