/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import Services.ServiceCategorie;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DALI CHARFEDDINE
 */
public class StatController implements Initializable {

    @FXML
    private PieChart chart;
     private ServiceCategorie ca = new ServiceCategorie();
    @FXML
    private Button GoDashboard;
    @FXML
    private Button prod;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart.setTitle("Quantite"); 
        chart.getData().setAll(new PieChart.Data("Quantite <20", ca.Recherche3()), new PieChart.Data("Quantite [20-50]", ca.Recherche1()),  
                new PieChart.Data("Quantite [50-70]", ca.Recherche2()), new PieChart.Data("Quantite [70-100]", ca.Recherche4()));
   
 
 
 }

    @FXML
   private void GoDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Stage window = (Stage) GoDashboard.getScene().getWindow();
        window.setScene(new Scene(root));
        
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        Stage window = (Stage) prod.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}

