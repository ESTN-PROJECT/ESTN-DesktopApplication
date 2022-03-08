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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import pidev.entities.Actualite;
import pidev.entities.Article;
import pidev.entities.Discussion;
import pidev.entities.Forum;
import pidev.services.ArticleCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ListDarticleController implements Initializable {

    ArticleCRUD ds=new ArticleCRUD();
                private ObservableList<Article> ArticleData = FXCollections.observableArrayList();

    private TableView<Article> table;
    private TableColumn<Article, String> Titre;
    private TableColumn<Article, String> Contenu;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_supprimer;
    private Label label;
    @FXML
    private ListView<Article> listview;
    @FXML
    private Pane pan;
    @FXML
    private TextField Titre_edite;
    @FXML
    private TextField contenu_edite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         List<Article> list= new ArrayList<Article>();
       list= ds.displayArticle();
       ArticleData.addAll(list);
       listview.setItems(ArticleData);
       pan.setVisible(false);
      /* table.setItems(ArticleData);
        Titre.setCellValueFactory(
            new PropertyValueFactory<>("titre")
        );
         Contenu.setCellValueFactory(
            new PropertyValueFactory<>("contenu")
        );
        */
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         try {
                           Parent root = FXMLLoader.load(getClass().getResource("Article.fxml"));
                            Stage myWindow = (Stage) listview.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                        }

    }

    @FXML
    private void update(ActionEvent event) {
     Article a = listview.getSelectionModel().getSelectedItem();
     a.setTitre(Titre_edite.getText());
     a.setContenu(contenu_edite.getText());
      ds.updateArticle(a);
      resetTableData();
        
        /*    Article a = table.getSelectionModel().getSelectedItem();
        TextInputDialog dialog= new TextInputDialog();
        dialog.setTitle("mise à jour");
        dialog.setContentText(a.getContenu().toString());
       

        Optional<String> option = dialog.showAndWait();
if (option.get() == null) {
			this.label.setText("No selection!");
		} else if (option.isPresent()) {
			a.setContenu(dialog.getEditor().getText());
                        ds.updateArticle(a);
       
    }*/
//resetTableData();
    }

    @FXML
    private void supprimer(ActionEvent event) {
                    Article a = listview.getSelectionModel().getSelectedItem();
            ds.suppArticle(a);
            resetTableData();
    }
           public void resetTableData()
    {
        List<Article> list = new ArrayList<>();
        
        list = ds.displayArticle();
        ObservableList<Article> data = FXCollections.observableArrayList(list);
        listview.setItems(data);
    }

    @FXML
    private void test(MouseEvent event) {
        Article a =listview.getSelectionModel().getSelectedItem();
        Titre_edite.setText(a.getContenu());
        contenu_edite.setText(a.getContenu());
        pan.setVisible(true);
    }
    
}
