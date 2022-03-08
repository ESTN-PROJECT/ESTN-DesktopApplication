/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.Evenement_Controller;
import Model.Evenement;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import database_conn.MyDB;
import java.awt.Button;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.search;


/**
 * FXML Controller class
 *
 * @author AZIZ
 */
public class FXMLEvenementController implements Initializable {
    
   
 Evenement_Controller ev = new Evenement_Controller();
 Evenement e = new Evenement();   
    
    
@FXML
private ListView <Evenement> EventList;
@FXML
private javafx.scene.control.Button ShowEvents;

private List<Evenement> evenements = ev.afficher();
    @FXML
    private javafx.scene.control.Button Add_Event;
    @FXML
    private javafx.scene.control.Button Modify_Event;
    @FXML
    private javafx.scene.control.Button Delete_event;
     @FXML
    private TextField search; 
    @FXML
    private javafx.scene.control.Button Search_Event;
    @FXML
    private TextField add_event_title;
    @FXML
    private TextField add_description;
    @FXML
    private DatePicker datepicker_event;
    @FXML
    private javafx.scene.control.Button gobackbtn;
    @FXML
    private javafx.scene.control.Button add_generate_pdf;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }   
    private void Refresh() {
        Evenement_Controller ev = new Evenement_Controller();
         List<Evenement> Evenement = ev.afficher();
        EventList.getItems().clear();
        EventList.getItems().addAll(Evenement);
        
    }
    
    
private void executeQuery(String query) {
       Connection cnx = MyDB.getInstance().getCnx();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void Show(ActionEvent event) {
        EventList.getItems().addAll(evenements);
        Refresh();
        
    } 
    @FXML
    private void Add(ActionEvent event) {
if(verifUserChampsajouter()){
   e.setTitre_event(add_event_title.getText());
   e.setDescription_event(add_description.getText());
     Date event_date = Date.valueOf(this.datepicker_event.getValue());
     e.setDate_event(event_date);
     //e.setImage_event(image_event);
     
     ev.Ajouter_Evenement(e);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "EVENT ADDED", ButtonType.OK);
            alert.show();
      Refresh();
}
     
      
    }
/*private void getADDprod(ActionEvent event) {
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
    */

    private boolean verifUserChampsajouter() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        add_event_title.setStyle(styledefault);
        add_description.setStyle(styledefault);
        datepicker_event.setStyle(styledefault);

        if (add_event_title.getText().equals("")) {
            add_description.setStyle(style);
           
           
            verif = 1;
        }
//        if (dateC.getValue().equals("")) {
//            dateC.setStyle(style);
//            verif = 1;
//        }
        if (add_event_title.getText().equals("")) {
         
           add_description.getText().equals("");
            verif = 1;
        }

        if (verif == 0) {
            return true;
        }

        JOptionPane.showMessageDialog(null, "Remplir tous les champs!");
        return false;

    }
    
    private int  a ;
    @FXML 
    private void Delete(ActionEvent event)  {
        
        int SelectedId = EventList.getSelectionModel().getSelectedIndex();
        
        e.setId_event(EventList.getItems().get(EventList.getSelectionModel().getSelectedIndex()).getId_event());
        ev.Supprimer_Evenement(e.getId_event());
        EventList.getItems().remove(EventList.getSelectionModel().getSelectedIndex());
        Refresh();
        
        
        
        
        
        
     
       /* e.setId_event(EventList.getItems().get(a).getId_event());
        ev.Supprimer_Evenement(e.getId_event());
        Refresh();
        */
    }
    @FXML
    private void search(ActionEvent event) {
        List<Evenement> searchtitleevent = ev.RechercheByTitleEvent(search.getText());
        EventList.getItems().removeAll(evenements);
        EventList.getItems().addAll(searchtitleevent);
        

    }
   
    
    //@FXML 
    private void Modify(ActionEvent event){
    
      Evenement e = new Evenement();
      Evenement_Controller ev = new Evenement_Controller();
       
    
        e.setTitre_event(add_event_title.getText());
        e.setDescription_event(add_description.getText());
        LocalDate event_date = datepicker_event.getValue();
        String Sdate = String.valueOf(event_date);
        e.setDate_event(Date.valueOf(Sdate));
        ev.Modifer_Evenement(e, EventList.getSelectionModel().getSelectedItem().getId_event());
  
        
       //Date event_date = Date.valueOf(datepicker_event.getValue());
       //String edate = String.ValueOfa(event_date);
       //e.setDate_event(Date.ValueOf(edate));
       //Date event_date = Date.valueOf(this.datepicker_event.getValue());
        //e.setDate_event(event_date);
        

    //ev.Modifer_Evenement(e);
        
       /* 
        Refresh();
        add_event_title.setText("");
        add_description.setText ("");
        
        //datepicker_event.setDate
        //e.setDate_event(event_date);
        EventList.getItems().clear();
        EventList.getItems().addAll(ev.afficher());
        Refresh();
*/
    }
    

    

    @FXML
    private void index(MouseEvent event) {
        Evenement e=EventList.getSelectionModel().getSelectedItem();
         
        add_event_title.setText(e.getTitre_event());
       add_description.setText(e.getDescription_event());
       datepicker_event.setValue(LocalDate.now());
       Date event_date = Date.valueOf(this.datepicker_event.getValue());
        e.setDate_event(event_date);
     
    }

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLInterfaceA.fxml"));
        Stage window = (Stage) gobackbtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }    

    @FXML
    private void generate_pdf(ActionEvent event) {
       
        
        
        
        
        
        
        try {
            Connection con = MyDB.getInstance().getCnx();
            Statement stmt = con.createStatement();
            ResultSet query_set = stmt.executeQuery("SELECT * FROM evenement");
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:/Users/AZIZ/Desktop/packs.pdf"));
            my_pdf_report.open();
            PdfPTable my_report_table = new PdfPTable(3);
            PdfPCell table_cell;

            while (query_set.next()) {
                String titre_event = query_set.getString("titre_event");
                table_cell = new PdfPCell(new Phrase(titre_event));
                my_report_table.addCell(table_cell);
                String description_event = query_set.getString("description_event");
                table_cell = new PdfPCell(new Phrase(description_event));
                my_report_table.addCell(table_cell);
                String date_event = query_set.getString("date_event");
                table_cell = new PdfPCell(new Phrase(date_event));
                my_report_table.addCell(table_cell);

            }
            my_pdf_report.add(new Paragraph("--------------------------------------------------------------"));
            my_pdf_report.add(new Paragraph("                             Event List                       "));          
            my_pdf_report.add(new Paragraph("--------------------------------------------------------------"));
            my_pdf_report.add(my_report_table);
            my_pdf_report.close();
            query_set.close();
            stmt.close();
            // con.close();
             
            
                   
            

            System.out.println("ok");

        } catch (SQLException ex) {
            System.out.println("err");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        
        
        /*
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(EventList);
        //int x =j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION){
            
            path = j.getSelectedFile().getPath();
                
        }
     try {
         Document doc = new Document();
         PdfWriter.getInstance(doc, new FileOutputStream(path+"events.pdf"));
         
         doc.open();
     PdfPTable tb1= new PdfPTable(4);
         
         
         
         
         
     } catch (FileNotFoundException ex) {
         Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
     

}    catch (DocumentException ex) {
         Logger.getLogger(FXMLEvenementController.class.getName()).log(Level.SEVERE, null, ex);
     }
*/
    } 

}
