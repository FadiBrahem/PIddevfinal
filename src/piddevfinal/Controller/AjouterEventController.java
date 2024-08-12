/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import piddevfinal.Entity.Evenement;
import piddevfinal.Entity.Mentor;
import piddevfinal.Services.MentorServices;
import piddevfinal.Services.EvenementServices;
import piddevfinal.Bunch.ConnectionDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;

import java.sql.Connection;

import static java.sql.Date.valueOf;

import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author fedi
 */
public class AjouterEventController implements Initializable {


    @FXML
    private JFXButton BTNSignUp;
    @FXML
    private JFXButton btnReset;
    @FXML
    private ImageView btnReduce;
    @FXML
    private ImageView btnClose;
    @FXML
    private Label lblregister;
    @FXML
    private AnchorPane AjouterEventPane;

   
    @FXML
    private JFXTextField NomField;
    @FXML
    private JFXTextField OrganisateurField;
    @FXML
    private JFXDatePicker DateDebutField;
    @FXML
    private JFXDatePicker DateFinField;
    @FXML
    private JFXTextField TypeField;
    @FXML
    private JFXTextField CapField;
  private final Connection con = ConnectionDB.getInstance().getCnx();
    @FXML
    private JFXButton btnAddImage;
    @FXML
    private ImageView addedImage;

    /**
     * Initializes the controller class.
     */
    

    @FXML
    private void handleClick(ActionEvent event) {

        Boolean controle = true;
        if (event.getSource() == BTNSignUp) {

            if (NomField.getText().isEmpty() || OrganisateurField.getText().isEmpty() || TypeField.getText().isEmpty() || CapField.getText().isEmpty() ) {

                controle = false;
                lblregister.setText("VEUILLEZ REMPLIR TOUS LES CHAMPS");
             
            } else if (DateDebutField.getValue().isBefore(LocalDate.of(2021, Month.APRIL, 22))) {

                controle = false;
                lblregister.setText("invalid date");

            }else if (DateFinField.getValue().isBefore(DateDebutField.getValue())) {

                controle = false;
                lblregister.setText("choose a valid date");

            }
            else if (controle) {

                Register();

            }
        }

        if (event.getSource() == btnReset) {
            ClearChamps();
        }
    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Stage stage = (Stage) AjouterEventPane.getScene().getWindow();
        if (event.getSource().equals(btnReduce)) {

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            stage.close();
        }

    }



    private void ClearChamps() {
        NomField.setText("");
       
        LocalDate dateclear1 = DateDebutField.getValue();
        LocalDate dateclear2 = DateFinField.getValue();
        OrganisateurField.setText("");
        TypeField.setText("");
        CapField.setText("");
  
    }

    public void Register() {
        try {
            EvenementServices mss = new EvenementServices();
        
            Evenement m = new Evenement();
            m.setNom(NomField.getText());
            m.setDate_Debut(valueOf(DateDebutField.getValue()));
            m.setDate_Fin(valueOf(DateFinField.getValue()));
            m.setOrganisateur(OrganisateurField.getText());
            m.setType(TypeField.getText());
            m.setCapacite(CapField.getText());
             
         
            System.out.println(m);

            mss.ajouter(m);
            lblregister.setText("Ajout avec Succes");
           
        } catch (Exception ex) {
            Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void addImage(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    
   

}
