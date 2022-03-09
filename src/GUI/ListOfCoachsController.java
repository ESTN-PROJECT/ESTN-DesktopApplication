/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Coach;
import Models.Utilisateur;
import Services.UtilisateurController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListOfCoachsController implements Initializable {

    @FXML
    private Button go;
    @FXML
    private ListView<Coach> list;
    private UtilisateurController uc = new UtilisateurController();
    private List<Coach> users = uc.afficherCoachs();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.getItems().clear();

        list.getItems().addAll(users);
    }

    @FXML
    private void go(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage window = (Stage) go.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
