/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.MyDB;
import Models.Equipe;
import Models.Scrimms;
import Services.ServiceEquipe;
import Services.ServiceScrimms;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class ScrimmController implements Initializable {

    
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Scrimms scrimms = null;
    private boolean maj;
    ObservableList<Scrimms> ScrimmsList = FXCollections.observableArrayList();
    ServiceEquipe sss= new ServiceEquipe();
    ObservableList<String> list = FXCollections.observableArrayList(sss.affichernp());

    @FXML
    private ImageView logo;
    @FXML
    private Button BackFromS;
    @FXML
    private Label lb_id;
    @FXML
    private ListView<Scrimms> listscrimms;
    @FXML
    private Button btndeleteS;
    @FXML
    private Button btnupdateS;
    @FXML
    private Button btnCreateS;
    @FXML
    private Button btntriS;
    @FXML
    private TextField searchbarS;
    @FXML
    private Button btnShow;
    @FXML
    private ComboBox<String> comboboxeq2;
    private TextField fieldeq1;
    private TextField fieldeq2;
    @FXML
    private TextField fieldgame;
    @FXML
    private ComboBox<String> comboboxeq1;

    /**
     * Initializes the controller class.
     */
    
    
    private void executeQuery(String query) {
       Connection cnx = MyDB.getInstance().getcnx();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    ObservableList<Scrimms> scrimmsList = FXCollections.observableArrayList();
    private ServiceScrimms ss = new ServiceScrimms();
    private List<Scrimms> scrim = ss.afficher();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lb_id.setVisible(false);
        GetCode();
        refresh();
        comboboxeq1.setItems(list);
        comboboxeq2.setItems(list);
    }    
    private void refresh() {
        ServiceScrimms ss = new ServiceScrimms();
         List<Scrimms> scrimms = ss.afficher();
        listscrimms.getItems().clear();
        listscrimms.getItems().addAll(scrimms);
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage window = (Stage) BackFromS.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void CreateS(ActionEvent event) {
        String nom_eq1 =comboboxeq1.getValue();     
        String nom_eq2 =comboboxeq2.getValue();  
        String Game=fieldgame.getText();
        
      ServiceScrimms ss = new  ServiceScrimms();
      Scrimms S = new Scrimms(nom_eq1,nom_eq2,Game);
      ss.ajouter(S);
      refresh();
    }
    
    @FXML
    private void DeleteS(ActionEvent event) {
        int SelectedId = listscrimms.getSelectionModel().getSelectedIndex();

        ServiceScrimms ss = new ServiceScrimms();
        Scrimms S = new Scrimms();
        S.setId(listscrimms.getItems().get(listscrimms.getSelectionModel().getSelectedIndex()).getId());
        ss.deleteScrimms(S.getId());
        listscrimms.getItems().remove(listscrimms.getSelectionModel().getSelectedIndex());
        refresh();
    }

    @FXML
    private void UpdateS(ActionEvent event) {
        ServiceScrimms scrimm = new ServiceScrimms();
        Scrimms S=listscrimms.getSelectionModel().getSelectedItem();
        Scrimms SS = new Scrimms();
        SS.setId(Integer.parseInt(lb_id.getText()));
        
        SS.setNom_eq1(comboboxeq1.getValue());
        SS.setNom_eq2(comboboxeq2.getValue());
        SS.setGame(fieldgame.getText());

        scrimm.update(SS);       
        refresh();
        comboboxeq1.setValue("");        
        comboboxeq2.setValue ("");
        fieldgame.setText("");      
        listscrimms.getItems().clear();
        listscrimms.getItems().addAll(scrimm.afficher());
        
        
    }
    
    
    @FXML
    private void Show(ActionEvent event) {
        ServiceScrimms xx = new ServiceScrimms();
         List<Scrimms> z = xx.afficher();
        listscrimms.getItems().clear();
        listscrimms.getItems().addAll(z);
    }
    
    private void GetCode() {
        listscrimms.setOnMouseClicked((MouseEvent arg0) -> {
            int a = listscrimms.getSelectionModel().getSelectedIndex();
        });
    }

    

    @FXML
    private void TriS(ActionEvent event) {
        ServiceScrimms ss = new ServiceScrimms();
         List<Scrimms> scrimmTri = ss.Tri();
        listscrimms.getItems().clear();
        listscrimms.getItems().addAll(scrimmTri);
    }
    
    @FXML
    private void Recherche(ActionEvent event) {
        List<Scrimms> searchscrimm = ss.RechercheByGame(searchbarS.getText());
        listscrimms.getItems().clear();

        listscrimms.getItems().removeAll(scrimms);
        listscrimms.getItems().addAll(searchscrimm);
    }
    
    
    private void index(MouseEvent event) {
        Scrimms S=listscrimms.getSelectionModel().getSelectedItem();
         
        fieldeq1.setText(S.getNom_eq1());
        fieldeq2.setText(S.getNom_eq2());
        fieldgame.setText(S.getGame());
        lb_id.setText(S.getId()+"");
    }
}
