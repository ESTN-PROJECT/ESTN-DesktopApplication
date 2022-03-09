/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.scenario.effect.impl.Renderer;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Models.Actualite;
import Models.Article;
import Models.Forum;
import Services.ActualiteCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ListActualiteController implements Initializable {

    private ObservableList<Actualite> ActualiteData = FXCollections.observableArrayList();
    ActualiteCRUD service = new ActualiteCRUD();

    @FXML
    private Button ajouter;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_supp;
    private TableView<Actualite> table;
    private TableColumn<Actualite, Date> date_ajout;
    @FXML
    private ListView<Actualite> listview;
    @FXML
    private Pane pan;
    @FXML
    private TextField description_a_jour;
    @FXML
    private DatePicker date_a_jour;
    @FXML
    private Button btnexcel;
    @FXML
    private Button GoBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Actualite> list = new ArrayList<Actualite>();
        list = service.displayActualite();
        ActualiteData.addAll(list);
        listview.setItems(ActualiteData);
        pan.setVisible(false);

    }

    @FXML
    private void btn_ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Actualite.fxml"));
            Stage myWindow = (Stage) listview.getScene().getWindow();
            Scene sc = new Scene(root);

            myWindow.setScene(sc);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ActualiteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void update(ActionEvent event) throws Exception {
        Actualite a = listview.getSelectionModel().getSelectedItem();
        java.sql.Date date_converted = java.sql.Date.valueOf(date_a_jour.getValue());
        a.setDate_ajout(date_converted);
        a.setDescrition(description_a_jour.getText());
        service.updateActualite(a);
        resetTableData();

    }

    @FXML
    private void supprimer(ActionEvent event) {
        Actualite a = listview.getSelectionModel().getSelectedItem();
        service.suppActualite(a);
        resetTableData();
    }

    public void resetTableData() {
        List<Actualite> list = new ArrayList<>();
        list = service.displayActualite();
        ObservableList<Actualite> data = FXCollections.observableArrayList(list);
        listview.setItems(data);
    }

    @FXML
    private void charger(MouseEvent event) {
        Actualite a = listview.getSelectionModel().getSelectedItem();
        description_a_jour.setText(a.getDescrition());
        date_a_jour.setValue(a.getDate_ajout().toLocalDate());
        pan.setVisible(true);

    }

    public void writeExcel() throws Exception {
        Writer writer = null;
        try {

            File file = new File("C:/Users/HP/Desktop/khedmet lgroupe/nour pidev/file.csv.");
            writer = new BufferedWriter(new FileWriter(file));
            for (Actualite person : listview.getItems()) {

                String text = person.getDescrition() + "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            writer.flush();
            writer.close();
        }
    }

    @FXML
    private void createexcel(ActionEvent event) throws Exception {
        writeExcel();
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage window = (Stage) GoBack.getScene().getWindow();
        window.setScene(new Scene(root));

    }

}
