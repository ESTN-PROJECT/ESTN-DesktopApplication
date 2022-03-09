/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.MyDB;
import Services.Evenement_Controller;
import Services.Participation_Controller;
import Models.Evenement;
import Models.Participation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AZIZ
 */
public class FXMLParticipationController implements Initializable {

    @FXML
    private ListView<Evenement> Event_List;
    @FXML
    private TextField add_participant_name;
    @FXML
    private TextField add_participant_lastname;
    @FXML
    private DatePicker datepicker_participation;
    @FXML
    private TextField add_event_title;
    @FXML
    private Button registration_participant;

    Evenement e = new Evenement();
    Evenement_Controller ev = new Evenement_Controller();
    Participation p = new Participation();
    Participation_Controller pv = new Participation_Controller();
    private List<Evenement> evenements = ev.afficher();
    @FXML
    private Button showevent;
    @FXML
    private Button goback;
    @FXML
    private Button Search_Event;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void Refresh() {
        Evenement_Controller ev = new Evenement_Controller();
        List<Evenement> Evenement = ev.afficher();
        Event_List.getItems().clear();
        Event_List.getItems().addAll(Evenement);

    }

    private void executeQuery(String query) {
        Connection cnx = MyDB.getInstance().getcnx();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void Registration(ActionEvent event) {
        if (verifUserChampsajouter()) {

            p.setNom_part(add_participant_name.getText());
            p.setPrenom_part(add_participant_lastname.getText());
            Date event_participation = Date.valueOf(this.datepicker_participation.getValue());
            p.setDate_participation(event_participation);
            p.setTitre_event(add_event_title.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "CONFIRMED", ButtonType.OK);
            alert.show();
            Refresh();
            pv.Inscrire_Participation(p);
        }
    }

    @FXML
    private void Show(ActionEvent event) {
        Event_List.getItems().addAll(evenements);
        Refresh();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage window = (Stage) goback.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void search(ActionEvent event) {

        List<Evenement> searchtitleevent = ev.RechercheByTitleEvent(search.getText());
        Event_List.getItems().removeAll(evenements);
        Event_List.getItems().addAll(searchtitleevent);

    }

    private boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        add_participant_name.setStyle(styledefault);
        add_participant_lastname.setStyle(styledefault);
        datepicker_participation.setStyle(styledefault);
        add_event_title.setStyle(styledefault);

        if (add_event_title.getText().equals("")) {
            add_participant_name.setStyle(style);

            verif = 1;
        }
//        if (dateC.getValue().equals("")) {
//            dateC.setStyle(style);
//            verif = 1;
//        }
        if (add_event_title.getText().equals("")) {

            add_participant_name.getText().equals("");
            verif = 1;
        }

        if (verif == 0) {
            return true;
        }

        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;

    }
}
