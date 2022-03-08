/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev.entities.Article;
import pidev.services.ArticleCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ArticleController implements Initializable {
    ArticleCRUD service = new ArticleCRUD();

    @FXML
    private TextField titre;
    @FXML
    private TextArea contenu;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_vider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        if(titre.getText().equals("") || contenu.getText().equals("")){
         Alert a = new Alert(Alert.AlertType.ERROR, "Champs vide", ButtonType.CLOSE);
            a.show();
    }
        else{
        Article a = new Article(titre.getText(), contenu.getText());
        service.ajouterArticle(a);
       affnotif();
       
       
       
         try {
                           Parent root = FXMLLoader.load(getClass().getResource("List d'article.fxml"));
                            Stage myWindow = (Stage) contenu.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ListDarticleController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    }
    @FXML
    private void vider(ActionEvent event) {
       titre.setText("");
       contenu.setText("");
    }
      public void affnotif(){
        Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Article ajouté avec succé").graphic(null).hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
