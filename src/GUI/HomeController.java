/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class HomeController implements Initializable {

    private Button back;
    @FXML
    private ImageView ProfileIcon;
    @FXML
    private Hyperlink TeamsF;
    @FXML
    private Hyperlink ScrimsF;
    @FXML
    private Hyperlink EventParticipation;
    @FXML
    private Hyperlink BtnAddDiscussion;
    @FXML
    private Hyperlink Shop;
    @FXML
    private Hyperlink games;
    @FXML
    private ImageView Logout;
    @FXML
    private Hyperlink demandeentain;
    @FXML
    private Hyperlink gocoach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("auth.fxml"));
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    private void GoToProfile(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void TeamsF(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EquipeFront.fxml"));
        Stage window = (Stage) TeamsF.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    @FXML
    private void ScrimsF(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scrimm.fxml"));
        Stage window = (Stage) ScrimsF.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToEventParticipation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLParticipation.fxml"));
        Stage window = (Stage) EventParticipation.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void AddDiscussion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DiscussionFXML.fxml"));
        Stage window = (Stage) EventParticipation.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void Shop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
        Stage window = (Stage) EventParticipation.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void games(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Stage window = (Stage) EventParticipation.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void Logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("auth.fxml"));
        Stage window = (Stage) EventParticipation.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void demandeentain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("demande.fxml"));
        Stage window = (Stage) EventParticipation.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void gocoach(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListOfCoachs.fxml"));
        Stage window = (Stage) EventParticipation.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
