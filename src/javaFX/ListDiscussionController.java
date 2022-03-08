/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFX;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pidev.entities.Article;
import pidev.entities.Discussion;
import pidev.services.DiscussionCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ListDiscussionController implements Initializable {

    private ObservableList<Discussion> DiscussionData = FXCollections.observableArrayList();
    DiscussionCRUD ds = new DiscussionCRUD();

    private TableColumn<Discussion, String> sujet;
    private TableView<Discussion> table;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_update;

    private Label label;
    @FXML
    private Button btn_Filtrer;
    @FXML
    private Pane pan;
    @FXML
    private TextArea contenu_a_jour;
    @FXML
    private ListView<Discussion> listview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Discussion> list = new ArrayList<Discussion>();
        list = ds.displayDiscussion();
        DiscussionData.addAll(list);
        listview.setItems(DiscussionData);
  pan.setVisible(false);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Discussion.fxml"));
            Stage myWindow = (Stage) listview.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(DiscussionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {
        Discussion d = listview.getSelectionModel().getSelectedItem();
        ds.suppDisc(d);
        resetTableData();
    }

    @FXML
    private void update(ActionEvent event) {
        Discussion d = listview.getSelectionModel().getSelectedItem();
      

            d.setContenu(contenu_a_jour.getText());
            ds.updateDisscusion(d);
           resetTableData();
    }

    @FXML
    private void Filtrer(ActionEvent event) {
        int n=0;
        Discussion d = listview
                .getSelectionModel().getSelectedItem();
        
while(n<6){
        ds.Filter(d);
        n++;
}
        resetTableData();
    }

    public void resetTableData() {
        List<Discussion> list = new ArrayList<>();
        list = ds.displayDiscussion();
        ObservableList<Discussion> data = FXCollections.observableArrayList(list);
        listview.setItems(data);
    }

    @FXML
    private void charger(MouseEvent event) {
         Discussion d = listview
                .getSelectionModel().getSelectedItem();

          pan.setVisible(true);
         contenu_a_jour.setText(d.getContenu());

    }

}
