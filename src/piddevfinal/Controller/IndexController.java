/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;


import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class IndexController implements Initializable {

    @FXML
    private JFXButton btnToLoginClient;
    @FXML
    private JFXButton btnToAdmin;
    @FXML
    private JFXButton btnToMentor;
    @FXML
    private AnchorPane IndexPane;
    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnReduce;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void Scene2 (ActionEvent event) throws IOException{
        
        if (event.getSource().equals(btnToLoginClient)){
            Stage stage = (Stage) IndexPane.getScene().getWindow();
        Stage stage2 = new Stage();
              stage.close();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/LoginClient.fxml")); 
                    Scene scene = new Scene(root);
                    stage2.setScene(scene); 
                    stage2.initStyle(StageStyle.UNDECORATED);
                    stage2.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
        if (event.getSource().equals(btnToAdmin)){
            Stage stage = (Stage) IndexPane.getScene().getWindow();
        Stage stage2 = new Stage();
              stage.close();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/AdminLogin.fxml")); 
                    Scene scene = new Scene(root);
                    stage2.setScene(scene); 
                    stage2.initStyle(StageStyle.UNDECORATED);
                    stage2.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        if (event.getSource().equals(btnToMentor)){
            Stage stage = (Stage) IndexPane.getScene().getWindow();
        Stage stage2 = new Stage();
              stage.close();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/LoginMentor.fxml")); 
                    Scene scene = new Scene(root);
                    stage2.setScene(scene); 
                    stage2.initStyle(StageStyle.UNDECORATED);
                    stage2.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        if (event.getSource().equals(btnReduce)){
          Stage stage = (Stage) IndexPane.getScene().getWindow();
      
          stage.setIconified(true);
        }
        
        if (event.getSource().equals(btnClose)) 
        {
            System.exit(0);           
        }
    }

}

    
