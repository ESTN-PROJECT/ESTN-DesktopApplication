/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.UtilisateurController;
import Models.Coach;
import Models.Member;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpController implements Initializable {

    private Button back;
    @FXML
    private Button GoBack;
    @FXML
    private TextField lb_username;
    @FXML
    private PasswordField lb_password;
    @FXML
    private DatePicker date_naiss;
    @FXML
    private TextField lb_city;
    @FXML
    private TextField lb_rank;
    @FXML
    private Hyperlink to_login;
    @FXML
    private Button BtnSignUp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /*
    @FXML
    private void SignUp(ActionEvent event) {
        UtilisateurController uc = new UtilisateurController();
        if(lb_coach.isSelected()){
        //uc.SignUpCoach(Coach.getInstance(lb_username.getText(), date_naiss., lb_city.getTypeSelector(), lb_password.getText(), lb_rank.getTypeSelector(), "hehehe", 50.2f, " "));
        }
        if(lb_member.isSelected()){
        //uc.SignUpMember(u);
        }
        
    }
     */

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChoiceOfRole.fxml"));
        Stage window = (Stage) GoBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void SignUpMember(ActionEvent event) {
       UtilisateurController uc= new UtilisateurController();
       Date date_naiss= Date.valueOf(this.date_naiss.getValue());
      
        uc.SignUpMember(Member.getInstance(lb_username.getText(), Date.valueOf(this.date_naiss.getValue()), lb_city.getText(), lb_password.getText(), lb_rank.getText(), " "));
        
    }

    @FXML
    private void ToLogin(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("auth.fxml"));
        Stage window = (Stage) GoBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
