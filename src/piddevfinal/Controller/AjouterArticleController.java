/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private AnchorPane AjouterArticlePane;
    @FXML
    private JFXButton btnReset;
    @FXML
    private ImageView btnReduce;
    @FXML
    private ImageView btnClose;
    @FXML
    private Label lblregister;
    @FXML
    private JFXTextField tfTitleAjout;
    @FXML
    private JFXTextArea tfDescriptionAjout;
    @FXML
    private JFXComboBox<String> tfCategorie;
    @FXML
    private JFXButton btnAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    

    @FXML
    private void handleMouseButton(MouseEvent event) {
         Stage stage = (Stage) AjouterArticlePane.getScene().getWindow();
        if (event.getSource().equals(btnReduce)) {

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            stage.close();
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource().equals(btnAjout)){
            
        }
    }
    
}
