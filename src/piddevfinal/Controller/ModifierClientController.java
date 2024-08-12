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
import java.net.URL;
import java.sql.Connection;
import static java.sql.Date.valueOf;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
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
public class ModifierClientController implements Initializable {

    @FXML
    private AnchorPane ModifierClientPane;
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
    private JFXRadioButton registerMale;
    @FXML
    private ToggleGroup Sexe;
    @FXML
    private JFXRadioButton registerFemale;
    @FXML
    private JFXTextArea Radresse;
    @FXML
    private JFXTextField Rtel;
    @FXML
    private JFXButton BTNSignUp;
    @FXML
    private ImageView btnReduce;
    @FXML
    private ImageView btnClose;
    @FXML
    private Label lblregister;

    private Client SelectedClient;
    private JFXTextField Rage;
    @FXML
    private JFXTextField Rid;

    private final Connection con = ConnectionDB.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    public void initData(Client client) {

        SelectedClient = client;
        Rid.setText(SelectedClient.getId_client().toString());
        Rusername.setText(SelectedClient.getUsername());
        Remail.setText(SelectedClient.getEmail());
       // Rpassword.setText(SelectedClient.getPassword());
        Rnom.setText(SelectedClient.getNom());
        Rprenom.setText(SelectedClient.getPrenom());

        LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(SelectedClient.getDateNai()));
        RdateNai.setValue(localDate);

        String sex = SelectedClient.getSexe();

        if ("MALE".equals(sex)) {
            registerMale.setSelected(true);
        } else {
            registerFemale.setSelected(true);
        }

        Rtel.setText(SelectedClient.getTel());
        Radresse.setText(SelectedClient.getAdresse());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void Update(ActionEvent event) throws Exception {
       

        Boolean controle = true;

        if (!isValidEmailAddress(Remail.getText())) {

            controle = false;
            lblregister.setText("EMAIL UNCORRECT");
        } else if (Rusername.getText().isEmpty() || Rpassword.getText().isEmpty() || Remail.getText().isEmpty() || Rnom.getText().isEmpty() || Rprenom.getText().isEmpty()) {

            controle = false;
            lblregister.setText("VEUILLEZ REMPLIR TOUS LES CHAMPS");
        } else if (RdateNai.getValue().isAfter(LocalDate.of(2009, Month.DECEMBER, 31))) {

            controle = false;
            lblregister.setText("VOUS DEVEZ AVOIR PLUS DE 12 ANS");

        } else //query
        if (controle) {

            ClientServices cs = new ClientServices();
            Client c = new Client();

            c.setId_client(Integer.valueOf(Rid.getText()));
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

            lblregister.setText("done");
            cs.modifierClient(c);
            System.out.println(c);
        } else {
            lblregister.setText("invalide");
        }

        /*     Statement statement = con.createStatement();
               //'username' = " + username + ", ' email ' " + email + ", ' password ' " + password + ", ' nom ' " + nom + ", ' prenom ' " + prenom + ", ' dateNai ' " + datebirth + ", ' sexe ' " + sexe + ", ' tel ' " + tel + ", ' adresse ' " + adresse;
               
                 String sql = "update client set username= '"+username+"',email= '"+email+"',password= '"+
                    password+"',nom= '"+nom+"',prenom= '"+prenom+ "',dateNai= '"+ datebirth + "',sexe= '"+ sexe + "',tel= '"+ tel + "',adresse= '"+ adresse + "' where id_client='"+id_client+"' ;";
                   PreparedStatement st = con.prepareStatement(sql);
                      System.out.println(sql);
                 st.execute(sql);
               
                lblregister.setText("MODIFICATION SUCCES");

            } catch (SQLException ex) {
               
              
            }*/
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

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Stage stage = (Stage) ModifierClientPane.getScene().getWindow();
        if (event.getSource().equals(btnReduce)) {

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            stage.close();
        }
    }

    public static int stringToInt(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
