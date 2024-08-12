/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import piddevfinal.Entity.Admin;
import piddevfinal.Services.AdminServices;
import piddevfinal.Bunch.ConnectionDB;
import piddevfinal.Bunch.SecurityMeasures;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
public class AdminLoginController implements Initializable {

    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private ImageView btnBack;
    @FXML
    private AnchorPane AdminPane;
    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnReduce;
    @FXML
    private Label lblerror;

    private Connection con = ConnectionDB.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet res;

    @FXML
    private void handleMouseButton(MouseEvent event) {

        if (event.getSource().equals(btnBack)) {
            Stage stage = (Stage) AdminPane.getScene().getWindow();
            Stage stage2 = new Stage();
            stage.close();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/Index.fxml"));
                Scene scene = new Scene(root);
                stage2.setScene(scene);
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.show();

            } catch (IOException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (event.getSource().equals(btnReduce)) {
            Stage stage = (Stage) AdminPane.getScene().getWindow();

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            System.exit(0);
        }

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        if (event.getSource() == btnSignIn) {
            if (txtusername.getText().isEmpty() || txtpassword.getText().isEmpty()) {

                lblerror.setText("VEUILLEZ REMPLIR TOUS LES CHAMPS");
            } else {
                lblerror.setText("");
                //login
                Login();
            }

        }
    }

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void Login() throws Exception {
        
        AdminServices as = new AdminServices();
        Admin a = new Admin();

        a.setUsername(txtusername.getText());
        a.setPassword(txtpassword.getText());
        if (!as.Authentification(a)) {
            txtusername.setText("");
            txtpassword.setText("");

            lblerror.setText("MOT DE PASSE / USERNAME INCORRECT");
        } else {
            lblerror.setText("");
            Stage stage = (Stage) AdminPane.getScene().getWindow();
            Stage stage2 = new Stage();
            stage.close();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/AdminDashBoard.fxml"));
                Scene scene = new Scene(root);
                stage2.setScene(scene);
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.show();

            } catch (IOException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
