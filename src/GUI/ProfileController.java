/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.MyDB;
import Models.Member;
import Models.Utilisateur;
import Services.UtilisateurController;
import com.restfb.types.User;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ProfileController implements Initializable {

    @FXML
    private TextField lb_username;
    @FXML
    private PasswordField lb_password;
    @FXML
    private TextArea lb_description;
    @FXML
    private Button BtnSignUp;
    @FXML
    private TextField lb_cout;
    @FXML
    private DatePicker lb_dateNaiss;
    @FXML
    private TextField Lb_email;
    @FXML
    private ComboBox<?> Cities;
    @FXML
    private ComboBox<?> Ranks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void fillFields() throws SQLException {
     
    }

    @FXML
    private void SignUpCoach(ActionEvent event) {
    }

}
