/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.UtilisateurController;
import Database.MyDB;
import Models.Coach;
import Models.Member;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class UserUpdateController implements Initializable {

    Connection cnx;
    ObservableList options = FXCollections.observableArrayList();
    @FXML
    private TextField lb_username;
    @FXML
    private PasswordField lb_password;
    @FXML
    private TextField lb_city;
    @FXML
    private TextField lb_rank;
    @FXML
    private DatePicker date_naiss;
    @FXML
    private TextField Lb_email;
    @FXML
    private TextArea lb_description;
    @FXML
    private TextField lb_cout;
    @FXML
    private Button BtnUpdate;
    @FXML
    private ComboBox<String> ListOfId;
    @FXML
    private Button GoBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombo();
    }

    @FXML
    private void UpdateUser(ActionEvent event) {

        UtilisateurController uc = new UtilisateurController();
        int id = Integer.parseInt((String) ListOfId.getValue());
        if (uc.VerifyIfMember(id) == false) {

            lb_description.setVisible(true);
            lb_cout.setVisible(true);
            Coach u =Coach.getInstance(lb_username.getText(), java.sql.Date.valueOf(this.date_naiss.getValue()), lb_city.getText(), lb_password.getText(), lb_rank.getText(), lb_description.getText(), lb_cout.getText(), " ", Lb_email.getText());

            uc.UpdateCoach(u, id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Coach updated!");
            alert.show();
        } else {
            lb_description.setVisible(false);
            lb_cout.setVisible(false);
            Member u =Member.getInstance(lb_username.getText(), java.sql.Date.valueOf(this.date_naiss.getValue()), lb_city.getText(), lb_password.getText(), lb_rank.getText(), " ", Lb_email.getText());

            uc.UpdateMember(u, id);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Member updated!");
            alert.show();
        }
    }

    @FXML
    private void getValues(ActionEvent event) {
        UtilisateurController uc = new UtilisateurController();

        try {

            int value = Integer.parseInt(ListOfId.getValue());
            System.out.println(value);
            String querry = "select * from `utilisateur` where `id`= " + value;
            Statement stm = cnx.createStatement();

            ResultSet rs = stm.executeQuery(querry);
            if (uc.VerifyIfMember(Integer.parseInt((String) ListOfId.getValue())) == false) {
                lb_description.setVisible(true);
                lb_cout.setVisible(true);
                while (rs.next()) {
                    Lb_email.setText(rs.getString("email"));
                    lb_username.setText(rs.getString("username"));
                    lb_password.setText(rs.getString("password"));
                    lb_city.setText(rs.getString("ville"));
                    lb_rank.setText(rs.getString("rank"));

                }
            } else {
                lb_description.setVisible(false);
                lb_cout.setVisible(false);
                while (rs.next()) {
                    Lb_email.setText(rs.getString("email"));
                    lb_username.setText(rs.getString("username"));
                    lb_password.setText(rs.getString("password"));
                    lb_city.setText(rs.getString("ville"));
                    lb_rank.setText(rs.getString("rank"));
                    lb_description.setText(rs.getString("description"));
                    lb_cout.setText(rs.getString("cout"));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void fillCombo() {
        try {
            cnx = MyDB.getInstance().getcnx();
            String querry = "SELECT * FROM `utilisateur`";
            PreparedStatement ps = cnx.prepareStatement(querry);
            ResultSet rs = ps.executeQuery(querry);
            while (rs.next()) {
                options.add(rs.getString("id"));

            }
            ListOfId.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(UserUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage window = (Stage) GoBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
