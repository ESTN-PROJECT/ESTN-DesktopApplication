/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.MyDB;
import Models.Produit;

import Services.ServiceProduit;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.ServicePayment;

/**
 * FXML Controller class
 *
 * @author DALI CHARFEDDINE
 */
public class Payement1Controller implements Initializable {
MyDB myConnection = MyDB.getInstance ();
        ServiceProduit cartServices =new ServiceProduit ();
        List<Produit> LastOrder= new ArrayList();
        
         Stage dialogStage = new Stage();
    Scene scene;
    @FXML
    private JFXTextField montant;
    @FXML
    private JFXTextField usermail;
    @FXML
    private JFXTextField First;
    @FXML
    private JFXTextField Last;
    @FXML
    private JFXTextField Number;
    @FXML
    private JFXTextField Card;
    @FXML
    private JFXTextField CVC;
    @FXML
    private Button payer;
       
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usermail.setText ("mohamedali.charfeddine1@esprit.tn");
       // montant.setText (String.valueOf (((int) LastOrder.get (0).getPrix())));
        First.setText ("user01");
        Last.setText ("user02");
        Number.setText (String.valueOf (25963741));
        Card.setText ("**** **** **** 5556");
        CVC.setText ("101");
    }    

    @FXML
    private void payerService(ActionEvent event) throws IOException {
         ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(() -> {
            ServicePayment P=new ServicePayment();
            
            P.RetrieveCustomer ();
//                Integer Dueamount = Integer.parseInt(montant.getText());
//    P.payement (Dueamount);

try {
    Desktop.getDesktop().browse(new URL("https://dashboard.stripe.com/test/customers/cus_LHfcx5pRWyekvl?fbclid=IwAR0Qq32Mve6E-ETXw1HRGN8U35vckgJQz4-Sq11Ht5Xw2-Egv-ebZwNLq6Y").toURI());
} catch (IOException e) {
    e.printStackTrace();
} catch (URISyntaxException e) {
    e.printStackTrace();
}
         });
        emailExecutor.shutdown();
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
        scene = new Scene (FXMLLoader.load(getClass().getResource("Shop.fxml")));
        
        dialogStage.setScene(scene);

        String title = "Payement succesful";
        String message = "You payment  has been Approved";

    

        dialogStage.show();
    }
}
