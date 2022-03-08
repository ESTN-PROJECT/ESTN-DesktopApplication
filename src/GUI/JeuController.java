/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.JeuControllers;
import Model.Jeu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class JeuController implements Initializable {

    @FXML
    private ListView<Jeu> listJeu;
    @FXML
    private Button Trier;
   
    @FXML
    private TextField Description;
    
    @FXML
    private ChoiceBox<String> choixNom;
     private String [] Jeu ={"GTA","FIFA22","NBA22","FORTNITE","COD","LOL"};
    @FXML
     private ChoiceBox<String> choixCategorie;
     private String [] Categ ={"ACTION","AVENTURE","SPORT","SIMULATION","ROLE"};
     
    
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Jeu jeu = null;

    ObservableList<Jeu> JeuList = FXCollections.observableArrayList();
    
    
    private int a;
    
    
    private JeuControllers j = new JeuControllers ();
    private List<Jeu> jeux = j.afficher();
   
//    private List<Jeu> jeuxTrier = j.trierjeu();
    @FXML
    private ImageView img;
    private File Current_file;
    private String file_image;
   
  
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GetCode();
        refresh();
        
  
        choixNom.getItems().addAll(Jeu);
       
         choixCategorie.getItems().addAll(Categ);
    }    

//    private void ShowJeu(ActionEvent event) {
//         listJeu.getItems().addAll(jeux);
//    }

    @FXML
    private void AddJeu(ActionEvent event) {
       

        String nom = choixNom.getValue();
        String categorie = choixCategorie.getValue();
        String description = Description.getText();
     file_image= "src/assets/"+file_image;
            String Image=file_image;
         if (choixNom.getValue()==null  || choixCategorie.getValue()==null || description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();}
         else {
        JeuControllers jeu = new JeuControllers();
        Jeu j = new Jeu(nom, categorie, description, Image);
        jeu.ajouter(j);
        refresh();
        clean();
         }
        
    }

    @FXML
    private void TrierJeu(ActionEvent event) {
        JeuControllers jeu = new JeuControllers();
        List<Jeu> JeuTrie = jeu.Tri();
        listJeu.getItems().clear();
        listJeu.getItems().addAll(JeuTrie);


    }

    @FXML
    private void SupprimerJeu(ActionEvent event) {
    
        JeuControllers jeu = new JeuControllers();
        Jeu j = new Jeu();
        j.setId(listJeu.getItems().get(a).getId());
        jeu.DeleteJeu(j.getId());
        refresh();
        clean();

    }
    

    @FXML
    private void ModifierJeu(ActionEvent event) {
        JeuControllers jeu = new JeuControllers();
        Jeu j=listJeu.getSelectionModel().getSelectedItem();
        Jeu jj = new Jeu();
        
        j.setNom(choixNom.getValue());
        j.setCategorie(choixCategorie.getValue());
       j.setId(listJeu.getItems().get(a).getId());
        j.setDescription(Description.getText());
        jeu.modifer(j);
        refresh();
        choixNom.setValue("");
        choixCategorie.setValue("");
        Description.setText("");
        
        listJeu.getItems().clear();
        listJeu.getItems().addAll(jeu.afficher());
        clean();
    }

    

    private void GetCode() {

        listJeu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                a = listJeu.getSelectionModel().getSelectedIndex();
            }
        });
    }

    private void refresh() {
JeuControllers j = new JeuControllers();
         List<Jeu> jeu = j.afficher();
        listJeu.getItems().clear();
        listJeu.getItems().addAll(jeu);
    }

    @FXML
    private void clean() {
         choixNom.setValue(null);
         choixCategorie.setValue(null);
         Description.setText(null);
         
    }

    @FXML
    private void quit(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void index(MouseEvent event) {
         Jeu j=listJeu.getSelectionModel().getSelectedItem();
         
        choixNom.setValue(j.getNom());
        choixCategorie.setValue(j.getCategorie());
        Description.setText(j.getDescription());
     
              
    }
    
    
    @FXML
    private void over(DragEvent event) {
         Dragboard board = event.getDragboard();
        if (board.hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        
    }

    @FXML
    private void drag(DragEvent event) throws FileNotFoundException {
          Dragboard board = event.getDragboard();
        List<File> phil = board.getFiles();
        FileInputStream fis;
        fis = new FileInputStream(phil.get(0));
        Image image = new Image(fis);
        File selectedFile = phil.get(0);
        if (selectedFile != null) {

            String test = selectedFile.getAbsolutePath();
            System.out.println(test);

            Current_file = selectedFile.getAbsoluteFile();
            file_image = Current_file.getName();
            Jeu j = new Jeu();
            j.setImage(selectedFile.getName());
           img.setImage(image);
        }
        }

    
   

    
    
    
}
