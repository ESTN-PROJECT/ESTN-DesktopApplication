/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.mailer.MailerController;
import Models.Utilisateur;
import Services.UtilisateurController;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
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
public class AuthController implements Initializable {

    Utilisateur ines;
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

    @FXML
    private Hyperlink not_registered;
    @FXML
    private Hyperlink BtnForgetPassword;
    @FXML
    private ImageView BtnLoginWithFb;
    Preferences preferences;
    UtilisateurController uc = new UtilisateurController();

    public void DisplayImage() {

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
            window.setScene(new Scene(dashboard));
        } else if (uc.Login(label_username.getText(), label_password.getText()) != true) {

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
    void GoBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ChoiceOfRole.fxml"));
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    private void LoginWithFb(MouseEvent event) {

        String REDIRECT_URI = "https://www.facebook.com/";
        String MY_ACCESS_TOKEN = "EAAXMICMfLyIBAPaZBroGhuCjJKf04T3BU06Ek8Y3MERmaFGbrKCJBCqanaqY1FGM36XaBrhJs1UTzTNtDmo00tmO0D21Ecv0dzG3sli6CfSYVJkap1M1iexBtx3hGZAGTvO1UqeGYCOdvciXFWNr395hfWaD98WaLA4cxqy9tLDMjt5CuI4nkQkx6zTt1e8doeY8PiBPTgbsryMRZBlJsMvf8C5eTFAyFWToxZAeuaZAYJdOQLAGe";
        String MY_APP_ID = "1631813283819298";
        String MY_APP_SECRET = "b3d3a1abe992f13f77dfdf86dafe5312";

        FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, Version.LATEST);
        User me = facebookClient.fetchObject("me", User.class);
        String name = me.getName();
        label_username.setText(name);
    }

    @FXML
    private void ForgetPassword(ActionEvent event) throws Exception {
        UtilisateurController uc = new UtilisateurController();
        String to = uc.selectEmail(label_username.getText());
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("enable_start_tls", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

       
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
                    m.setSubject("Password recuperation");
                    m.setText("Your password is " + uc.selectPassword(label_username.getText()));

                    //send mail
                    Transport.send(m);

                    System.out.println("Message sent!");

                } catch (MessagingException e) {
                    e.printStackTrace();
                }

        label_username.clear();
        label_password.clear();

    }

    private static Message prepareMessage(Session session, String myAccountEmail) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress("rbenslimaine@gmail.com"));
            message.setSubject("TN Camp ");
            message.setText("Vous êtes inscrit(e).");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(MailerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
