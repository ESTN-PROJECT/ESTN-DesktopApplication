/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Models.Actualite;
import Models.Discussion;
import Services.ActualiteCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ActualiteController implements Initializable {

    ActualiteCRUD service=new ActualiteCRUD();

    
    @FXML
    private DatePicker date;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_annuler;
    @FXML
    private TextField description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        if(description.getText().equals("") || date.getValue()==null){
            Alert a = new Alert(Alert.AlertType.ERROR, "Champs vide", ButtonType.CLOSE);
            a.show();
        }
        else
        {
       java.sql.Date date_converted = java.sql.Date.valueOf(date.getValue());
        Actualite a=new Actualite(date_converted,description.getText());
        service.ajouterActualite(a);
        affnotif();
         try {
                           Parent root = FXMLLoader.load(getClass().getResource("ActualiteList.fxml"));
                            Stage myWindow = (Stage) description.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ListActualiteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
    }
    @FXML
    private void annuler(ActionEvent event) {
        date.setValue(LocalDate.now());
        description.setText("");
    }
    
      public void affnotif(){
        Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Article ajouté avec succé").graphic(null).hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
   
}

