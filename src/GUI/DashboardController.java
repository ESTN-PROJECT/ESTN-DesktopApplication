/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.UtilisateurController;
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
    @FXML
    private Button BtnToUserUpdate;
    @FXML
    private Hyperlink ShowTeams;
    @FXML
    private Hyperlink ShowScrims;
    @FXML
    private Hyperlink Events;
    @FXML
    private Hyperlink News;
    @FXML
    private Hyperlink Articles;
    @FXML
    private Hyperlink Forum;
    @FXML
    private Hyperlink GoToDiscussion;
    @FXML
    private Hyperlink Categories;
    @FXML
    private Hyperlink Products;
    @FXML
    private Hyperlink ToGames;

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

    @FXML
    private void ToUserUpdate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserUpdate.fxml"));
        Stage window = (Stage) Logout.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    private void searchUser(ActionEvent event) {
        List<Utilisateur> searchusername = uc.RechercheByUsername(search.getText());
        UserList.getItems().clear();

        UserList.getItems().removeAll(users);
        UserList.getItems().addAll(searchusername);

    }

    @FXML
    private void search(MouseEvent event) {
    }

    @FXML
    private void ShowTeams(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Equipe.fxml"));
        Stage window = (Stage) ShowTeams.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void ShowScrims(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scrimm.fxml"));
        Stage window = (Stage) ShowScrims.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLEvenement.fxml"));
        Stage window = (Stage) Events.getScene().getWindow();
        window.setScene(new Scene(root));
    }

   

    @FXML
    private void GoToArticles(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ArticleFXML.fxml"));
        Stage window = (Stage) Articles.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToForum(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ForumFXML.fxml"));
        Stage window = (Stage) Forum.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToNews(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ActualiteFXML.fxml"));
        Stage window = (Stage) News.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToDiscussion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DiscussionList.fxml"));
        Stage window = (Stage) News.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToCategories(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
        Stage window = (Stage) News.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToProducts(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        Stage window = (Stage) News.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void ToGames(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Stage window = (Stage) News.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
