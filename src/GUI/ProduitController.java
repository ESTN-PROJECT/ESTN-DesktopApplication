/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import Database.MyDB;
import java.awt.Insets;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.search;
import models.Produit;
import service.ServiceCategorie;
import service.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class ProduitController implements Initializable {
    private ServiceCategorie cr = new ServiceCategorie(); 
    
ObservableList<String> list=FXCollections.observableList(cr.affichernp());
    Alert b = new Alert(AlertType.NONE);
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Produit produit = null;

    public ListView<Produit> listprod;
    @FXML
    private Button goback;
    @FXML
    private Button GOBACK;
    @FXML
    private TextField Search;
    @FXML
    private TextField addNom;
    @FXML
    private TextField addPrix;
    @FXML
    private TextField addCategorie;
    @FXML
    private TextField addDescription;

    private int a;
    private boolean verificationUsernom;
    private boolean verificationUserPhone;
    private boolean verificationcat;
    private boolean verificationdesc;
    @FXML
    private Label erreur_nom;
    @FXML
    private Label erreur_prix;
    @FXML
    private Label erreur_cat;
    @FXML
    private Label erreur_desc;
    @FXML
    private ComboBox<String> combo;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        combo.setItems(list);
        refresh();

    }

    private void refresh() {
        ServiceProduit pr = new ServiceProduit();
        List<Produit> cate = pr.afficher();
        listprod.getItems().clear();
        listprod.getItems().addAll(cate);
    }

    private void executeQuery(String query) {
        Connection cnx = MyDB.getInstance().getCnx();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private ServiceProduit p = new ServiceProduit();
    private List<Produit> prod = p.afficher();

    @FXML
    private void getADDprod(ActionEvent event) {
        if (verifUserChampsajouter()) {
            String nom = addNom.getText();
            float prix = parseFloat(addPrix.getText());
            String description = addDescription.getText();
            
            ServiceProduit ca = new ServiceProduit();
            Produit c = new Produit(nom, prix, description,combo.getValue());
            ca.ajouter(c);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "PRODUIT AJOUTER", ButtonType.OK);
            alert.show();
            refresh();

        }
    }

    private boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        addNom.setStyle(styledefault);
        addPrix.setStyle(styledefault);
        addCategorie.setStyle(styledefault);

        if (addNom.getText().equals("")) {
            addPrix.setStyle(style);
            addCategorie.getText().equals("");
            verif = 1;
        }
//        if (dateC.getValue().equals("")) {
//            dateC.setStyle(style);
//            verif = 1;
//        }
        if (addNom.getText().equals("")) {
            addPrix.setStyle(style);
            addCategorie.getText().equals("");
            verif = 1;
        }

        if (verif == 0) {
            return true;
        }

        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;

    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ACCEUIL.fxml"));
        Stage window = (Stage) goback.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void GOBACK(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
        Stage window = (Stage) GOBACK.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void index(MouseEvent event) {
        Produit ps = listprod.getSelectionModel().getSelectedItem();

        addNom.setText(ps.getNom());
        addPrix.setText(String.valueOf(ps.getPrix()));
        
        addDescription.setText(ps.getDescription());

    }

    @FXML
    private void tri(ActionEvent event) {
        ServiceProduit ca = new ServiceProduit();
        List<Produit> trie = ca.Tri();
        listprod.getItems().clear();
        listprod.getItems().addAll(trie);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        b.setAlertType(Alert.AlertType.CONFIRMATION);
        b.setTitle("Confirmation");
        b.setHeaderText(null);
        b.setContentText("Are you sure to delete this PRODUCT");

        Optional<ButtonType> action = b.showAndWait();

        if (action.get() == ButtonType.OK) {
            ServiceProduit pr = new ServiceProduit();
            Produit c = new Produit();

            c.setId(listprod.getItems().get(a).getId());
            pr.Supprimer(c.getId());

            refresh();
        } else {
            refresh();

        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
        ServiceProduit pr = new ServiceProduit();
         Produit c = new Produit();
          c.setNom(addNom.getText());
         c.setPrix(Float.parseFloat(addPrix.getText()));
         c.setCategorie(combo.getValue());
           c.setDescription(addDescription.getText());
         

        c.setId(listprod.getItems().get(a).getId());

        pr.modifer(c);
        refresh();
        addNom.setText("");
        addPrix.setText("");
        addDescription.setText("");
        addCategorie.setText("");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "CATEGORIE MODIFIEE", ButtonType.OK);
        alert.show();
    }

    @FXML
    private void Search(ActionEvent event) {
        ServiceProduit ca = new ServiceProduit();
        List<Produit> nom = ca.Recherche(Search.getText());
        listprod.getItems().clear();
        listprod.getItems().removeAll(produit);
        listprod.getItems().addAll(nom);
    }

    @FXML
    private void testnom(KeyEvent event) {

        int nbNonChar = 0;
        for (int i = 1; i < addNom.getText().trim().length(); i++) {
            char ch = addNom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && addNom.getText().trim().length() >= 3) {
            erreur_nom.setText("");

            verificationUsernom = true;
        } else {
            erreur_nom.setText("Il faut au  moins 3 caracteres");
            verificationUsernom = false;

        }
        
    }
public boolean isNumeric(String str){
        if(str==null){
            return false;
        }
        try
        {
            float x=Float.parseFloat(str);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    @FXML
    private void testprix(KeyEvent event) {
         if (addPrix.getText().trim().length() != 0) {
            int nbChar = 0;
            for (int i = 1; i < addPrix.getText().trim().length(); i++) {
                char ch = addPrix.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                //System.out.println(nbChar);
            }

            if (isNumeric(addPrix.getText())) {
                erreur_prix.setText("");
                 
                verificationUserPhone = true;
            } else {           
                erreur_prix.setText("Vous devez entrer un chiffre ");
                verificationUserPhone = false;

            }

        } else {
            erreur_prix.setText("Il faut donner un prix");
            verificationUserPhone = false;
        }
        
    }

    @FXML
    private void testcat(KeyEvent event) {
              int nbNonChar = 0;
        for (int i = 1; i < addCategorie.getText().trim().length(); i++) {
            char ch = addCategorie.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && addCategorie.getText().trim().length() >= 3) {
            erreur_cat.setText("");

            verificationUsernom = true;
        } else {
            erreur_cat.setText("Il faut au  moins 3 caracteres");
            verificationUsernom = false;
    }
    }
    @FXML
    private void testdesc(KeyEvent event) {
         int nbNonChar = 0;
        for (int i = 1; i < addDescription.getText().trim().length(); i++) {
            char ch = addDescription.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && addDescription.getText().trim().length() >= 10) {
            erreur_desc.setText("");

            verificationUsernom = true;
        } else {
            erreur_desc.setText("Il faut au  moins 10 caracteres");
            verificationUsernom = false;

            }
        
    }

    @FXML
    private void combo(ActionEvent event) {
        
    }
    

    
        
        
    }


