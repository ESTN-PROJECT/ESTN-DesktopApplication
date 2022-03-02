/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.UtilisateurController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AuthController implements Initializable {

    private Scene scene;
    @FXML
    private TextField label_username;
    @FXML
    private PasswordField label_password;
    @FXML
    private Button login;
    @FXML
    private ImageView logo;
    @FXML
    private Button back;
    Image img = new Image(getClass().getResourceAsStream("large.png"));
    @FXML
    private Hyperlink not_registered;

    public void DisplayImage() {
        logo.setImage(img);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void btn_login(ActionEvent event) throws IOException {
        UtilisateurController uc = new UtilisateurController();
        if ("admin".equals(label_username.getText()) && "admin".equals(label_password.getText())) {

            Parent dashboard;
            dashboard = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Stage window = (Stage) back.getScene().getWindow();
            window.setScene(new Scene(dashboard)); }

        else if (uc.Login(label_username.getText(), label_password.getText()) != true) {
            
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Try again!");
            errorAlert.showAndWait();
            
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage window = (Stage) back.getScene().getWindow();
            window.setScene(new Scene(root));
        }
        }

        @FXML
        void GoBack
        (ActionEvent event) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("ChoiceOfRole.fxml"));
            Stage window = (Stage) back.getScene().getWindow();
            window.setScene(new Scene(root));

        }

    }
