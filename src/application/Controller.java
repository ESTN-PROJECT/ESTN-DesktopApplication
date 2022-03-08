/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Guide Info
 */
public class Controller implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label songLabel;
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private ComboBox<?> speedBox;
    @FXML
    private Slider volumeSlider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void playMedia(ActionEvent event) {
    }

    @FXML
    private void pauseMedia(ActionEvent event) {
    }

    @FXML
    private void resetMedia(ActionEvent event) {
    }

    @FXML
    private void previousMedia(ActionEvent event) {
    }

    @FXML
    private void nextMedia(ActionEvent event) {
    }

    @FXML
    private void changeSpeed(ActionEvent event) {
    }
    
}
