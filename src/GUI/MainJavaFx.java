/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author HP
 */
public class MainJavaFx extends Application {

    MediaPlayer mediaplayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("auth.fxml"));

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainJavaFx.class.getName()).log(Level.SEVERE, null, ex);
        }
        Media musicFile = new Media("file:///C://Users//HP//Documents//NetBeansProjects//ESTN-DesktopApplication-branch1//src//song/music.mp3");
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.setAutoPlay(true);
        mediaplayer.setVolume(0.1);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
