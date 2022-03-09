/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.EntrainementControllers;
import Services.JeuControllers;

import Models.Entrainement;
import Models.Jeu;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class DemandeController implements Initializable {

    @FXML
    private TextField DescriptionD;
    @FXML
    private ChoiceBox<String> NomJeu;
    
    @FXML
    private ChoiceBox<String> NomCoach;
    private String [] coach ={"Bilel","Ines","Aziz","Dali"};
    
    
    
   @FXML
    private ChoiceBox<String> NomMembre;
   private String [] membre ={"Bilel","Ines","Aziz","Dali"};
     @FXML
    private TextField telef;
     

//@FXML
//    private ListView<Entrainement> listEntrainement;
//        
        
         String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Entrainement entrainement= null;
    
    private JeuControllers je = new JeuControllers();
    ObservableList<Entrainement> EntrainementList = FXCollections.observableArrayList();
        ObservableList<String> List_jeu = FXCollections.observableArrayList(je.affichernp());

    
    
    private int a;
    @FXML
    private Button go;
    
    
  
    
    
//    private EntrainementControllers e = new EntrainementControllers ();
//    private List<Entrainement> entrainement = e.afficher();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         GetCode();
//        refresh();
NomCoach.getItems().addAll(coach);
NomMembre.getItems().addAll(membre);
  NomJeu.setItems(List_jeu);
    }    

    @FXML
    private void AjouterDemande(ActionEvent event) {
    
       String nom_jeu =NomJeu.getValue();
        String nom_coach = NomCoach.getValue();
        String nom_membre=NomMembre.getValue();
       String tel =telef.getText();
        String description = DescriptionD.getText();
      
       
               if (NomJeu.getValue()==null  || NomCoach.getValue()==null || NomMembre.getValue()==null || description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();}
         else {
             EntrainementControllers e = new EntrainementControllers();
             Entrainement ee = new Entrainement(description, nom_jeu, nom_coach, nom_membre, tel);
             e.ajouter(ee);
                  
                   clean();
               }
           // refresh();
         
    }

    @FXML
    private void clean() {
        NomJeu.setValue(null);
         NomCoach.setValue(null);
         NomMembre.setValue(null);
         telef.setText(null);
         DescriptionD.setText(null);
    }

//    private void GetCode() {
//listEntrainement.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent arg0) {
//                a = listEntrainement.getSelectionModel().getSelectedIndex();
//            }
//        });      }
//
//    private void refresh() {
//EntrainementControllers e = new EntrainementControllers();
//         List<Entrainement> ee = e.afficher();
//        listEntrainement.getItems().clear();
//        listEntrainement.getItems().addAll(ee);      }

    @FXML
    private void quit(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage window = (Stage) go.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    
}
