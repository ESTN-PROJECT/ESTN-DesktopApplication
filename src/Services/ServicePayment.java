/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.controls.JFXButton;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import javafx.scene.image.Image;

import services.ServicePayment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author DALI CHARFEDDINE
 */
public class ServicePayment {
    
    


public ServicePayment() {
        Stripe.apiKey = "sk_test_51KaV6UDzKtYTqfmuVfaDWW78SDMlztauFjXnGhoFjDZreBAhtHxWNtVVmY4oxAqucTTakMouwuGcTIiEN2giqZSh005wyGgJHS";

    }


 public void RetrieveCustomer(){
        try {
            Customer a=Customer.retrieve("cus_LHfcx5pRWyekvl");

            //create card
            Map<String, Object> cardParams = new HashMap<String, Object> ();
            cardParams.put("number", "4000056655665556");
            cardParams.put("exp_month", "12");
            cardParams.put("exp_year", "2022");
            cardParams.put("cvc", "456");
            Map<String, Object> tokenParams = new HashMap<String, Object>();
            tokenParams.put("card", cardParams);
            Token token=Token.create(tokenParams);
//            Map<String, Object> source = new HashMap<String, Object>();
//            source.put("source", token.getId());
//            a.getSources().create(source);
            Gson gson=new GsonBuilder ().setPrettyPrinting().create();
            System.out.println(gson.toJson(a));
        }
        catch (StripeException ex) {
            System.out.println(ex.getMessage());        }}
    public void payement(int prix){

        try {Customer a=Customer.retrieve("cus_LHfcx5pRWyekvl");

            //charge customer
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", (prix)+"00");
            chargeParams.put("currency", "usd");
            chargeParams.put("customer", a.getId());
            Charge.create(chargeParams);

        } catch (StripeException ex) {
            System.out.println(ex.getMessage());        }}


}

