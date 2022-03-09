/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Models.Discussion;
import Models.Forum;
import Services.ForumCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ListOfForumController implements Initializable {

    ForumCRUD fs = new ForumCRUD();
    private ObservableList<Forum> ForumData = FXCollections.observableArrayList();

    private TableView<Forum> table;
    private TableColumn<Forum, String> sujet;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_supp;
    private Label label;
    @FXML
    private ListView<Forum> listview;
    @FXML
    private TextField sujet_a_jour;
    @FXML
    private Pane pan;
    @FXML
    private Button GoBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Forum> list = new ArrayList<Forum>();
        list = fs.displayForum();
        ForumData.addAll(list);
        listview.setItems(ForumData);
        pan.setVisible(false);

    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
            Stage myWindow = (Stage) listview.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update(ActionEvent event) {
        Forum f = listview.getSelectionModel().getSelectedItem();
        f.setSujet(sujet_a_jour.getText());
        fs.updateForum(f);

        resetTableData();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Forum f = listview.getSelectionModel().getSelectedItem();
        fs.suppForum(f);
        resetTableData();

    }

    public void resetTableData() {
        List<Forum> list = new ArrayList<>();

        list = fs.displayForum();
        ObservableList<Forum> data = FXCollections.observableArrayList(list);
        listview.setItems(data);
    }

    @FXML
    private void charger(MouseEvent event) {
        pan.setVisible(true);
        Forum f = listview.getSelectionModel().getSelectedItem();
        sujet_a_jour.setText(f.getSujet());

    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage window = (Stage) GoBack.getScene().getWindow();
        window.setScene(new Scene(root));

    }
}
