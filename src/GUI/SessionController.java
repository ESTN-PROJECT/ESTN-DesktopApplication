/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.JeuControllers;
import Controllers.SessionControllers;
import Model.Jeu;
import Model.Session;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class SessionController implements Initializable {

    @FXML
    private ListView<?> listSession;
    @FXML
    private Button Trier;
    @FXML
    private TextField DescriptionS;
    @FXML
    private ChoiceBox<String> choixDuree;
    private String [] dure ={"1h","2h","3h","4h"};
    @FXML
    private TextField Telephone;
    @FXML
    private DatePicker DateSession;
    @FXML
    private ChoiceBox<String> choixMembre;
     private String [] membre ={"Bilel","Ines","Aziz","Dali"};
     
     
     
     
     String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Session session = null;

    ObservableList<Session> list = FXCollections.observableArrayList();
    
    
    private int a;
    
    
    private SessionControllers s = new SessionControllers ();
    private List<Session> sessions = s.afficher();
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GetCode();
        refresh();
        choixDuree.getItems().addAll(dure);
        choixMembre.getItems().addAll(membre);
    }    

    @FXML
    private void index(MouseEvent event) {
       Session s =(Session) listSession.getSelectionModel().getSelectedItem();
         DateSession.set();
        choixDuree.setValue(s.getDuree());
        choixMembre.setValue(s.getNom_membre());
        Telephone.setText(parseInt(s.getTéléphone_coach()));
        DescriptionS.setText(s.getDescription()); 
    }

    @FXML
    private void TrierJeu(ActionEvent event) {
    }

    @FXML
    private void SupprimerSession(ActionEvent event) {
        SessionControllers session = new SessionControllers();
        Session s = new Session();
        s.setId(listSession.getItems().get(a).getId());
        session.DeleteJeu(s.getId());
        refresh();
    }

    @FXML
    private void quit(ActionEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clean() {
        DateSession.setValue(null);
         choixDuree.setValue(null);
         choixMembre.setValue(null);
         Telephone.setText(null);
         DescriptionS.setText(null);
    }

    @FXML
    private void ModifierSession(ActionEvent event) {
        
        
    }

    @FXML
    private void AddSession(ActionEvent event) {
        Date date=Date.valueOf(this.DateSession.getValue());
        String duree = choixDuree.getValue();
        String nom_membre = choixMembre.getValue();
        int téléphone_coach = parseInt(Telephone.getText());
        String description = DescriptionS.getText();
         
        SessionControllers session = new SessionControllers();
        Session s = new Session(téléphone_coach, date, description, duree, nom_membre);
        session.ajouter(s);
        refresh();
        clean();
         
    }

    private void refresh() {
SessionControllers s = new SessionControllers();
         List<Session> session = s.afficher();
        listSession.getItems().clear();
        listSession.getItems().addAll(session);   
    }

    private void GetCode() {
        listSession.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                a = listSession.getSelectionModel().getSelectedIndex();
            }
        });
    
    }
    
    
}
