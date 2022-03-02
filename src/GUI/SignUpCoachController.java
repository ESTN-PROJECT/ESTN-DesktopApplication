/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.UtilisateurController;
import Models.Coach;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpCoachController implements Initializable {

    @FXML
    private Button goBack;
    @FXML
    private Button BtnSignUp;
    @FXML
    private TextField lb_username;
    @FXML
    private PasswordField lb_password;
    @FXML
    private DatePicker lb_dateNaiss;
    @FXML
    private TextField lb_city;
    @FXML
    private TextField lb_rank;
    @FXML
    private TextArea lb_description;
    @FXML
    private TextField lb_cout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ChoiceOfRole.fxml"));
        Stage window = (Stage) goBack.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    private void SignUpCoach(ActionEvent event) {
        UtilisateurController uc= new UtilisateurController();
       Date date_naiss= java.sql.Date.valueOf(this.lb_dateNaiss.getValue());
        uc.SignUpCoach(Coach.getInstance(lb_username.getText(), java.sql.Date.valueOf(this.lb_dateNaiss.getValue()) , lb_city.getText(), lb_password.getText(), lb_rank.getText(), lb_description.getText(), lb_cout.getText(), " "));
        lb_username.setText("");
        lb_password.setText("");
        lb_city.setText("");
        lb_rank.setText("");
        lb_description.setText("");
        lb_cout.setText("");
        
        
    }

}
