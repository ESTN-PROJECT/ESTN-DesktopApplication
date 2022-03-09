/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.MyDB;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import Models.Categorie;
import Services.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author DALI CHARFEDDINE
 */
public class CategorieController implements Initializable {

    Alert b = new Alert(AlertType.NONE);

    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String query = null;
    Categorie Categorie = null;

    @FXML
    private TextField textq;
    @FXML
    private TextField txtname;

    @FXML
    private ListView<Categorie> listcat;

    private int a;
    @FXML
    private Button prod;
    @FXML
    private TextField Search;
    private Button acc;
    @FXML
    private Button ajou;
    @FXML
    private Label erreur_q;
    @FXML
    private Label erreur_name;
    private boolean verificationUsernom;
    private boolean verificationUserPhone;
    private boolean verificationcat;
    private boolean verificationdesc;
    @FXML
    private Button GoDashboard;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        refresh();

    }

    private void refresh() {
        ServiceCategorie pr = new ServiceCategorie();
        List<Categorie> cate = pr.afficher();
        listcat.getItems().clear();
        listcat.getItems().addAll(cate);
    }

    private void executeQuery(String query) {
        Connection cnx = MyDB.getInstance().getcnx();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private ServiceCategorie ca = new ServiceCategorie();
    private List<Categorie> cat = ca.afficher();

    @FXML
    private void getADDcat(ActionEvent event) {
        if (verifUserChampsajouter()) {
            String nom_categorie = txtname.getText();
            int quantite = parseInt(textq.getText());

            ServiceCategorie ca = new ServiceCategorie();
            Categorie c = new Categorie(quantite, nom_categorie);
            ca.ajouter(c);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "CATEGORIE AJOUTER", ButtonType.OK);
            alert.show();
            refresh();
        }

    }

    @FXML
    private void Modifier(ActionEvent event) {
        ServiceCategorie pr = new ServiceCategorie();
        Categorie ca = listcat.getSelectionModel().getSelectedItem();
        Categorie c = new Categorie();
        c.setId_categorie(ca.getId_categorie());
        c.setNom_categorie(txtname.getText());
        c.setQuantite(Integer.parseInt(textq.getText()));

//        System.out.println(c);
        pr.modifier(c);
        txtname.setText("");
        textq.setText("");
        listcat.getItems().addAll(pr.afficher());
        refresh();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "CATEGORIE MODIFIEE", ButtonType.OK);
        alert.show();
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        b.setAlertType(Alert.AlertType.CONFIRMATION);
        b.setTitle("Confirmation");
        b.setHeaderText(null);
        b.setContentText("Are you sure to delete this Categorie");

        Optional<ButtonType> action = b.showAndWait();

        if (action.get() == ButtonType.OK) {
            ServiceCategorie pr = new ServiceCategorie();
            Categorie c = new Categorie();

            c.setId_categorie(listcat.getItems().get(a).getId_categorie());
            pr.Supprimer(c.getId_categorie());

            refresh();
        } else {
            refresh();

        }
    }

    @FXML
    private void tri(ActionEvent event) {
        ServiceCategorie ca = new ServiceCategorie();
        List<Categorie> trie = ca.Tri();
        listcat.getItems().clear();
        listcat.getItems().addAll(trie);
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        Stage window = (Stage) prod.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void Search(ActionEvent event) {
        ServiceCategorie ca = new ServiceCategorie();
        List<Categorie> nom = ca.Recherche(Search.getText());
        listcat.getItems().clear();
        listcat.getItems().removeAll(Categorie);
        listcat.getItems().addAll(nom);
    }

    private void GOBACK(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ACCEUIL.fxml"));
        Stage window = (Stage) acc.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML

    private void index(MouseEvent event) {
        Categorie ps = listcat.getSelectionModel().getSelectedItem();

        txtname.setText(ps.getNom_categorie());
        textq.setText(String.valueOf(ps.getQuantite()));

    }

    public boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        txtname.setStyle(styledefault);
        textq.setStyle(styledefault);

        if (txtname.getText().equals("")) {
            textq.setStyle(style);
            verif = 1;
        }
//        if (dateC.getValue().equals("")) {
//            dateC.setStyle(style);
//            verif = 1;
//        }
        if (textq.getText().equals("")) {
            textq.setStyle(style);
            verif = 1;
        }

        if (verif == 0) {
            return true;
        }

        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;

    }

    public boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int x = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @FXML
    private void testq(KeyEvent event) {
        if (textq.getText().trim().length() != 0) {
            int nbChar = 0;
            for (int i = 1; i < textq.getText().trim().length(); i++) {
                char ch = textq.getText().charAt(i);

                if (Character.isLetter(ch)) {

                    nbChar++;

                }
                //System.out.println(nbChar);
            }

            if (isNumeric(textq.getText())) {
                erreur_q.setText("");

                verificationUserPhone = true;
            } else {
                erreur_q.setText("  vous devez entrer un chiffre");
                verificationUserPhone = false;

            }

        } else {
            erreur_q.setText("Il faut donner la quantité");
            verificationUserPhone = false;
        }

    }

    @FXML
    private void testnom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < txtname.getText().trim().length(); i++) {
            char ch = txtname.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txtname.getText().trim().length() >= 3) {
            erreur_name.setText("");

            verificationUsernom = true;
        } else {
            erreur_name.setText("Il faut au  moins 3 caracteres");
            verificationUsernom = false;
        }
    }

    @FXML
    private void GoDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage window = (Stage) GoDashboard.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Stat.fxml"));
        Stage window = (Stage) stat.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
