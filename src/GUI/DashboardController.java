/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.UtilisateurController;
import Models.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DashboardController implements Initializable {

    @FXML
    private ListView<Utilisateur> UserList;
    @FXML
    private Hyperlink ShowUsers;
    private UtilisateurController uc = new UtilisateurController();
    private List<Utilisateur> users = uc.afficher();
    @FXML
    private ImageView DeleteUser;
    @FXML
    private ImageView Trier;
    @FXML
    private TextField search;
    private int a;
    @FXML
    private ImageView Logout;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Show(ActionEvent event) {
        UserList.getItems().clear();

        UserList.getItems().addAll(users);
    }

    @FXML
    private void Trier(MouseEvent event) {
        List<Utilisateur> userTrie = uc.TriParUsername();
        UserList.getItems().removeAll(users);
        UserList.getItems().addAll(userTrie);

    }

    @FXML
    private void search(ActionEvent event) {
        List<Utilisateur> searchusername = uc.RechercheByUsername(search.getText());
        UserList.getItems().clear();

        UserList.getItems().removeAll(users);
        UserList.getItems().addAll(searchusername);

    }

    private void refresh() {
        UtilisateurController uc = new UtilisateurController();
        List<Utilisateur> users = uc.afficher();
        UserList.getItems().clear();
        UserList.getItems().addAll(users);
    }

    private void GetCode() {
        UserList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                a = UserList.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    private void DeleteUser(MouseEvent event) {
        int SelectedId = UserList.getSelectionModel().getSelectedIndex();

        UtilisateurController uc = new UtilisateurController();
        Utilisateur u = new Utilisateur();
        u.setId(UserList.getItems().get(UserList.getSelectionModel().getSelectedIndex()).getId());
        uc.DeleteUser(u.getId());
        UserList.getItems().remove(UserList.getSelectionModel().getSelectedIndex());
        UserList.getItems().clear();
        UserList.getItems().addAll(users);
        refresh();
    }

    @FXML
    private void Logout(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("auth.fxml"));
        Stage window = (Stage) Logout.getScene().getWindow();
        window.setScene(new Scene(root));

    }

}
