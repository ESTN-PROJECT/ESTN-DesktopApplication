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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class EntrainementController implements Initializable {

    @FXML
    private Button Trier;
    private TextField DescriptionE;
    private TextField IdJeu;
    private TextField IdCoach;
    @FXML
    private ListView<Entrainement> listEntrainement;
    
    
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Entrainement entrainement = null;

    ObservableList<Entrainement> EntrainementList = FXCollections.observableArrayList();
    
    
    private int a;
    
    
    private EntrainementControllers e = new EntrainementControllers();
    private List<Entrainement> entrainements = e.afficher();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         GetCode();
        refresh();
        
    }    

//    private void ShowE(ActionEvent event) {
//         listEntrainement.getItems().addAll(entrainements);
//    }

//    private void AddE(ActionEvent event) {
//        String description = DescriptionE.getText();
//       int id_jeu = parseInt(IdJeu.getText());
//        int id_coach = parseInt(IdCoach.getText());
//        
//             
//             EntrainementControllers e = new EntrainementControllers();
//             Entrainement ee = new Entrainement(id_jeu,id_coach,description);
//             e.ajouter(ee);
//             refresh();
//         
//    }
    @FXML
    private void TrierE(ActionEvent event) {
        EntrainementControllers e = new EntrainementControllers();
        List<Entrainement> entrainementTrie = e.Tri();
        listEntrainement.getItems().clear();
        listEntrainement.getItems().addAll(entrainementTrie);

    }

    @FXML
    private void SupprimerE(ActionEvent event) {
        EntrainementControllers e = new EntrainementControllers();
        Entrainement ee = new Entrainement();
        ee.setId(listEntrainement.getItems().get(a).getId());
        e.DeleteJeu(ee.getId());
      refresh();
    }

//    private void ModifierE(ActionEvent event) {
//        EntrainementControllers entrainement = new EntrainementControllers();
//       Entrainement e=listEntrainement.getSelectionModel().getSelectedItem();
//        Entrainement ee = new Entrainement();
//        
//        e.setDescription(DescriptionE.getText());
//        e.setId_jeu(parseInt(IdJeu.getText()));
//       e.setId(listEntrainement.getItems().get(a).getId());
//        e.setId_coach(parseInt(IdCoach.getText()));
//        entrainement.modifer(e);
//        refresh();
//        DescriptionE.setText("");
//        IdJeu.setText("");
//        IdCoach.setText("");
//        
//        listEntrainement.getItems().clear();
//        listEntrainement.getItems().addAll(entrainement.afficher());
//    }
//
//    private void clean(ActionEvent event) {
//        DescriptionE.setText(null);
//         IdJeu.setText(null);
//         IdCoach.setText(null);
//    }
//
    @FXML
    private void quit(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
//
    @FXML
    private void index(MouseEvent event) {
         Entrainement e=listEntrainement.getSelectionModel().getSelectedItem();
         
//        DescriptionE.setText(e.getDescription());
//        IdJeu.setText(String.valueOf(e.getNom_jeu()));
//        IdCoach.setText(String.valueOf(e.getNom_coach()));
     
    }
//
//    private void GetCode() {
// listEntrainement.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent arg0) {
//                a = listEntrainement.getSelectionModel().getSelectedIndex();
//            }
//        });    }
//
    private void refresh() {
EntrainementControllers e = new EntrainementControllers();
         List<Entrainement> ee = e.afficher();
        listEntrainement.getItems().clear();
        listEntrainement.getItems().addAll(ee);    }


    
}
