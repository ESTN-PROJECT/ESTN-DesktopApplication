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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ChoiceOfRoleController implements Initializable {

    @FXML
    private Hyperlink GoToCoachSignUp;
    @FXML
    private Hyperlink GoToMemberSignUp;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void GoToSignUpCoach(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpCoach.fxml"));
        Stage window = (Stage) GoToCoachSignUp.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoToSignUpMember(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpMember.fxml"));
        Stage window = (Stage) GoToMemberSignUp.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("auth.fxml"));
        Stage window = (Stage) GoToMemberSignUp.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
