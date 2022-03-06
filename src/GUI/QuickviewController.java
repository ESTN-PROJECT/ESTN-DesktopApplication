/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.R;
import models.Produit;
import service.ServiceProduit;

public class QuickviewController implements Initializable {

    private Produit p1;
    ServiceProduit service = new ServiceProduit();

    @FXML
    private ImageView idimage_view;
   
    @FXML
    private Label nom;
    @FXML
    private Label categorie;
    @FXML
    private Label description;
    @FXML
    private Label Prix;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            p1 = service.GetProdbyid(Produit.getId_courant());
        } catch (SQLException ex) {
            Logger.getLogger(QuickviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nom.setText(p1.getNom());
     Prix.setText(Float.toString(p1.getPrix()));
       
     String a = p1.getPhoto();
        File file = new File(a);
         System.out.println(p1.getPhoto());
        Image image1 = new Image(file.toURI().toString());
        idimage_view.setImage(image1);
      

        description.setText(p1.getDescription());

        categorie.setText(p1.getCategorie());

    }
}
