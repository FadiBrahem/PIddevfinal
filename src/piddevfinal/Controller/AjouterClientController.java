/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import piddevfinal.Entity.Client;
import piddevfinal.Services.ClientServices;
import piddevfinal.Bunch.ConnectionDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import static java.sql.Date.valueOf;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class AjouterClientController implements Initializable {

    @FXML
    private AnchorPane AjouterClientPane;
    @FXML
    private JFXTextField Rusername;
    @FXML
    private JFXTextField Remail;
    @FXML
    private JFXPasswordField Rpassword;
    @FXML
    private JFXTextField Rnom;
    @FXML
    private JFXTextField Rprenom;
    @FXML
    private JFXDatePicker RdateNai;
    @FXML
    private JFXTextArea Radresse;
    @FXML
    private JFXTextField Rtel;
    @FXML
    private JFXButton BTNSignUp;
    @FXML
    private JFXButton btnReset;
    @FXML
    private ImageView btnReduce;
    @FXML
    private ImageView btnClose;
    @FXML
    private JFXRadioButton registerMale;
    @FXML
    private ToggleGroup Sexe;
    @FXML
    private JFXRadioButton registerFemale;

    private final Connection con = ConnectionDB.getInstance().getCnx();
    @FXML
    private Label lblregister;

    AdminDashBoardController ab = new AdminDashBoardController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Stage stage = (Stage) AjouterClientPane.getScene().getWindow();
        if (event.getSource().equals(btnReduce)) {

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            stage.close();
        }

    }

    @FXML
    private void handleClick(ActionEvent event) throws SQLException, IOException {
        if (event.getSource() == BTNSignUp) {

            Boolean controle = true;
            if (event.getSource() == BTNSignUp) {

                if (Rusername.getText().isEmpty() || Rpassword.getText().isEmpty() || Remail.getText().isEmpty() || Rnom.getText().isEmpty() || Rprenom.getText().isEmpty()) {

                    controle = false;
                    lblregister.setText("VEUILLEZ REMPLIR TOUS LES CHAMPS");
                } else if (!isValidEmailAddress(Remail.getText())) {

                    controle = false;
                    lblregister.setText("EMAIL INCORRECT");
                } else if (RdateNai.getValue().isAfter(LocalDate.of(2009, Month.DECEMBER, 31))) {

                    controle = false;
                    lblregister.setText("VOUS DEVEZ AVOIR PLUS DE 12 ANS");

                } else if (controle) {

                    Register();
                    lblregister.setText("Ajout avec Succes");

                } else {
                    lblregister.setText("Erreur");
                }
            }

            if (event.getSource() == btnReset) {
                lblregister.setText("");
                ClearChamps();
            }

        }
    }

    public void Register() {
         try {
        ClientServices cs = new ClientServices();
        Client c = new Client();
        c.setUsername(Rusername.getText());
        c.setPassword(Rpassword.getText());
        c.setEmail(Remail.getText());
        c.setNom(Rnom.getText());
        c.setPrenom(Rprenom.getText());

        c.setAdresse(Radresse.getText());
        c.setTel(Rtel.getText());
        c.setDateNai(valueOf(RdateNai.getValue()));
        RadioButton selectedRadioButton = (RadioButton) Sexe.getSelectedToggle();
        String sexe = selectedRadioButton.getText();
        c.setSexe(sexe);
        System.out.println(c);
       
            cs.ajouterClient(c);
        } catch (Exception ex) {
            Logger.getLogger(AjouterClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private void ClearChamps() {
        Rusername.setText("");
        Rpassword.setText("");

        Remail.setText("");
        Rnom.setText("");
        Rprenom.setText("");
        Radresse.setText("");
        Rtel.setText("");
    }

}
