/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.mailer.MailerController;
import Services.UtilisateurController;
import Models.Coach;
import Models.Member;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Properties;
import java.util.ResourceBundle;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private TextField lb_city;
    private TextField lb_rank;
    @FXML
    private Hyperlink to_login;
    @FXML
    private Button BtnSignUp;
    @FXML
    private ImageView VerifyCaptcha;
    @FXML
    private Label CaptchaHere;
    @FXML
    private ImageView RegenerateCaptcha;
    @FXML
    private TextField EnteredCaptcha;
    @FXML
    private TextField Lb_email;
    ObservableList cities = FXCollections.observableArrayList("Mahdia",
            "Ariana",
            "Béja",
            "Ben Arous",
            "Bizerte",
            "Gabes",
            "Gafsa",
            "Jendouba",
            "Kairouan",
            "Kasserine",
            "Kebili",
            "La Manouba",
            "Le Kef",
            "Médenine",
            "Monastir",
            "Nabeul",
            "Sfax",
            "Sidi Bouzid",
            "Siliana",
            "Sousse",
            "Tataouine",
            "Tozeur",
            "Tunis",
            "Zaghouan");
    ObservableList ranks = FXCollections.observableArrayList("Platinium", "Gold", "Silver", "Bronze", "Iron");

    @FXML
    private ComboBox<String> Cities;
    @FXML
    private ComboBox<String> Ranks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Captcha c = new Captcha();

        CaptchaHere.setText(c.getCaptcha());

        Cities.setItems(cities);
        Ranks.setItems(ranks);
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChoiceOfRole.fxml"));
        Stage window = (Stage) GoBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void SignUpMember(ActionEvent event) {
        if (" ".equals(lb_username.getText()) || " ".equals(lb_password.getText())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Wait!");
            errorAlert.setContentText("You should fill all the champs!");
            errorAlert.showAndWait();
        } else if (CaptchaHere.getText().equals(EnteredCaptcha.getText())) {
            if (lb_password.getText().length() < 8) {
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setHeaderText("Wait!");
                err.setContentText("Your password should contain minimum of 9 alphanumeric characters");
                err.showAndWait();
            } else {

                UtilisateurController uc = new UtilisateurController();
                Date date_naiss = Date.valueOf(this.date_naiss.getValue());

                uc.SignUpMember(Member.getInstance(lb_username.getText(), Date.valueOf(this.date_naiss.getValue()), Cities.getValue(), lb_password.getText(), Ranks.getValue(), " ", Lb_email.getText()));
                String to = Lb_email.getText();
                String from = "estngaming2022@gmail.com";
                String host = "smtp.gmail.com";
                final String username = "estngaming2022@gmail.com";
                final String password = "30101999iinneess";

                //setup mail server
                Properties props = System.getProperties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

                props.put("mail.debug", "true");
                props.put("mail.store.protocol", "imap");
                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                try {

                    //create mail
                    MimeMessage m = new MimeMessage(session);
                    m.setFrom(new InternetAddress(from));
                    m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
                    m.setSubject("Welcome " + lb_username.getText() + " To ESTN!");
                    m.setText("You're welcome to ESTN as a member!");

                    //send mail
                    Transport.send(m);

                    System.out.println("Message sent!");

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                
               
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setHeaderText("Welcome!");
                errorAlert.setContentText("WE'RE GLAD THAT YOU JOINED US!");
                errorAlert.showAndWait();
                lb_username.clear();
                lb_password.clear();
                Lb_email.clear();
                Cities.getItems().clear();
                Ranks.getItems().clear();
                EnteredCaptcha.clear();

            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Try Again!");
            errorAlert.setContentText("You should try again!");
            errorAlert.showAndWait();
        }

    }

    @FXML
    private void ToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("auth.fxml"));
        Stage window = (Stage) GoBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void RegenerateCaptcha(MouseEvent event) {
        Captcha a = new Captcha();
        CaptchaHere.setText(a.getCaptcha());

    }

    @FXML
    private boolean VerifyCaptcha(MouseEvent event) {
        Captcha c = new Captcha();

        if (CaptchaHere.getText().equals(EnteredCaptcha.getText())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Good job");
            errorAlert.setContentText("You're not a robot!");
            errorAlert.showAndWait();
            return true;
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Oops");
            errorAlert.setContentText("You may be a robot!");
            errorAlert.showAndWait();
            return false;
        }
    }

    public boolean validatePassword(String password) {
        boolean hasUpperLetter = false;
        boolean hasLowerCase = false;
        boolean strong = false;

        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                char x = password.charAt(i);
                if (Character.isUpperCase(x)) {

                    hasUpperLetter = true;
                } else if (Character.isLowerCase(x)) {
                    hasLowerCase = true;
                }
                // Password strong, break the loop
                if (hasUpperLetter && hasLowerCase) {
                    strong = true;
                    break;
                }

            }
            if (strong) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
