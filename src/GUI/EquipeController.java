/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.MyDB;
import Models.Equipe;
import Services.ServiceEquipe;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class EquipeController implements Initializable {
    
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Equipe equipe = null;
    private boolean maj;
    ObservableList<Equipe> teamList = FXCollections.observableArrayList();
    
    @FXML
    private Button BackFromT;
    @FXML
    private ImageView logo;
    @FXML
    private Button CreateT;
    @FXML
    private Button DeleteT;
    @FXML
    private Button UpdateT;
    @FXML
    private TextField lb_teamname;
    @FXML
    private TextField lb_game;
    @FXML
    private TextField lb_leaderign;
    @FXML
    private TextField lb_p2;
    @FXML
    private TextField lb_p3;
    @FXML
    private TextField lb_p4;
    @FXML
    private TextField lb_p5;
    @FXML
    public  ListView<Equipe> listteams;
    private int a;
    @FXML
    private Button Show;
    @FXML
    private Button btnTri;
    @FXML
    private TextField searchbar;
    @FXML
    private Label lb_id;
    @FXML
    private Button btnsms;

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
    ObservableList<Equipe> equipeList = FXCollections.observableArrayList();
    private ServiceEquipe se = new ServiceEquipe();
    private List<Equipe> team = se.afficher();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lb_id.setVisible(false);
        GetCode();
        refresh();
    }   
    private void refresh() {
        ServiceEquipe se = new ServiceEquipe();
         List<Equipe> equipes = se.afficher();
        listteams.getItems().clear();
        listteams.getItems().addAll(equipes);
    }
    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage window = (Stage) BackFromT.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void CreateT(ActionEvent event) {
        String nom_equipe =lb_teamname.getText();     
        String team_leader_ign =lb_leaderign.getText();  
        String p2 =lb_p2.getText();  
        String p3 =lb_p3.getText(); 
        String p4 =lb_p4.getText();
        String p5 =lb_p5.getText();            
        String jeu =lb_game.getText();
        
      ServiceEquipe es = new  ServiceEquipe();
      Equipe E = new Equipe(nom_equipe,team_leader_ign,p2,p3,p4,p5,jeu);
      es.ajouter(E);
      refresh();
      
      String tilte = "Team created successfully";
            String message = lb_teamname.getText();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
    }

    @FXML
    private void DeleteT(ActionEvent event) {
        int SelectedId = listteams.getSelectionModel().getSelectedIndex();

        ServiceEquipe se = new ServiceEquipe();
        Equipe E = new Equipe();
        E.setId(listteams.getItems().get(listteams.getSelectionModel().getSelectedIndex()).getId());
        se.deleteEquipe(E.getId());
        listteams.getItems().remove(listteams.getSelectionModel().getSelectedIndex());
        refresh();
        
        String tilte = "Team deleted successfully";
            String message = lb_teamname.getText();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
    }

    @FXML
    private void UpdateT(ActionEvent event) {
      ServiceEquipe equipe = new ServiceEquipe();
        Equipe E=listteams.getSelectionModel().getSelectedItem();
        Equipe EE = new Equipe();
        EE.setId(Integer.parseInt(lb_id.getText()));
        
        EE.setNom_equipe(lb_teamname.getText());
        EE.setTeam_leader_ign(lb_leaderign.getText());
        EE.setP2(lb_p2.getText());
        EE.setP3(lb_p3.getText());
        EE.setP4(lb_p4.getText());
        EE.setP5(lb_p5.getText());
        EE.setJeu(lb_game.getText());

        equipe.update(EE);
        
        
        refresh();
        lb_teamname.setText("");
        
        lb_leaderign.setText ("");
        lb_p2.setText("");
        lb_p3.setText("");
        lb_p4.setText("");
        lb_p5.setText("");
        lb_game.setText("");
        listteams.getItems().clear();
        listteams.getItems().addAll(equipe.afficher());
        
        
        String tilte = "Team updated successfully";
            String message = lb_teamname.getText();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        
    }
    @FXML
    private void Show(ActionEvent event) {
        ServiceEquipe x = new ServiceEquipe();
         List<Equipe> t = x.afficher();
        listteams.getItems().clear();
        listteams.getItems().addAll(t);
    }
    private void GetCode() {
        listteams.setOnMouseClicked((MouseEvent arg0) -> {
            int a = listteams.getSelectionModel().getSelectedIndex();
        });
    }

    @FXML
    private void Tri(ActionEvent event) {
        ServiceEquipe se = new ServiceEquipe();
         List<Equipe> equipeTri = se.Tri();
        listteams.getItems().clear();
        listteams.getItems().addAll(equipeTri);

    }

    @FXML
    private void index(MouseEvent event) {
        Equipe E=listteams.getSelectionModel().getSelectedItem();
         
        lb_teamname.setText(E.getNom_equipe());
        lb_leaderign.setText (E.getTeam_leader_ign());
        lb_p2.setText(E.getP2());
        lb_p3.setText(E.getP3());
        lb_p4.setText(E.getP4());
        lb_p5.setText(E.getP5());
        lb_game.setText(E.getJeu());
        lb_id.setText(E.getId()+"");
    }

    @FXML
    private void Recherche(ActionEvent event) {
        List<Equipe> searchequipe = se.RechercheByJeu(searchbar.getText());
        listteams.getItems().clear();

        listteams.getItems().removeAll(equipe);
        listteams.getItems().addAll(searchequipe);

    }

    @FXML
    private void sendSMS(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SendSMS.fxml"));
        Stage window = (Stage) btnsms.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    }

