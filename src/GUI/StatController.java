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
import javafx.scene.chart.PieChart;
import service.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author DALI CHARFEDDINE
 */
public class StatController implements Initializable {

    @FXML
    private PieChart chart;
     private ServiceCategorie ca = new ServiceCategorie();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart.setTitle("Quantite"); 
        chart.getData().setAll(new PieChart.Data("faible", ca.Recherche3()), new PieChart.Data("Moyenne", ca.Recherche1()),  
                new PieChart.Data("HAUT", ca.Recherche2()), new PieChart.Data("TRES HAUT", ca.Recherche4()));
       
              
        
    
      
    }    
     
}
