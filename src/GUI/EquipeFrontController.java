/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Services.SendSMS;
import Models.Equipe;
import Services.ServiceEquipe;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class EquipeFrontController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private TextField lb_teamname;
    @FXML
    private TextField lb_game;
    @FXML
    private TextField lb_leaderign;
    @FXML
    private TextField lb_p2;
    @FXML
    private TextField lb_p3;
    @FXML
    private TextField lb_p4;
    @FXML
    private TextField lb_p5;
    @FXML
    private Button BackFromTF;
    @FXML
    private Button CreateTF;
    @FXML
    private Button btnQR;
    @FXML
    private Button btnQRR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Stage window = (Stage) BackFromTF.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    

    @FXML
    private void CreateTF(ActionEvent event) {
        String nom_equipe =lb_teamname.getText();     
        String team_leader_ign =lb_leaderign.getText();  
        String p2 =lb_p2.getText();  
        String p3 =lb_p3.getText(); 
        String p4 =lb_p4.getText();
        String p5 =lb_p5.getText();            
        String jeu =lb_game.getText();
        
      ServiceEquipe es = new  ServiceEquipe();
      Equipe E = new Equipe(nom_equipe,team_leader_ign,p2,p3,p4,p5,jeu);
      es.ajouter(E);
      Services.SendSMS.SendSMS(E);
      
    }

    @FXML
    private void qrgenerate(ActionEvent event) {
        try {
            
            ByteArrayOutputStream out = QRCode.from("Team name : " + lb_teamname.getText() + "\n" + "Team Leader : " + lb_leaderign.getText() + "\n" + "Player 2 : " + lb_p2.getText() + "\n" + "Player 3 : " + lb_p3.getText() + "\n" + "Player 4 : " + lb_p4.getText() + "\n" + "Player 5 : " + lb_p5.getText() + "\n" + "Game : " + lb_game.getText())
                    .to(ImageType.PNG).stream();
            
            
            String f_name = lb_teamname.getText();
            String Path_name = "C:\\Users\\Guide Info\\Documents\\NetBeansProjects\\PIDEV\\QR\\" ;
            
            FileOutputStream fout = new FileOutputStream(new File(Path_name +(f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void qrread(ActionEvent event) {
        try {
            
            InputStream barcodeInputStream = new FileInputStream(QR_path.getText());
            BufferedImage barcBufferedImage = ImageIO.read(barcodeInputStream);
            
            LuminanceSource source = new BufferedImageLuminanceSource(barcBufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            Result reslut = reader.decode(bitmap);
            
            QR_read.setText(reslut.getText());
            
            
            
            
        } catch (Exception e) {
        }
    }
    private javax.swing.JTextField QR_path;
    private javax.swing.JLabel QR_read;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField qr_text;

    
    
}
