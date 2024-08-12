/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import piddevfinal.Entity.Mentor;
import piddevfinal.Services.MentorServices;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Properties;
import java.util.Random;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class AjouterMentorController implements Initializable {

    @FXML
    private JFXTextField Rusername;
    @FXML
    private JFXTextField Remail;
   
    @FXML
    private JFXTextField Rnom;
    @FXML
    private JFXTextField Rprenom;
    @FXML
    private JFXDatePicker RdateNai;
    @FXML
    private ToggleGroup Sexe;
    @FXML
    private JFXComboBox<String> Rrole;
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
    private Label lblregister;
    @FXML
    private AnchorPane AjouterMentorPane;

    private final Connection con = ConnectionDB.getInstance().getCnx();
    @FXML
    private JFXRadioButton registerMale;
    @FXML
    private JFXRadioButton registerFemale;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        Rrole.getItems().add("COACH");
    }

    @FXML
    private void handleClick(ActionEvent event) {

        Boolean controle = true;
        if (event.getSource() == BTNSignUp) {

            if (Rusername.getText().isEmpty() || Remail.getText().isEmpty() || Rnom.getText().isEmpty() || Rprenom.getText().isEmpty()) {

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

            }
        }

        if (event.getSource() == btnReset) {
            ClearChamps();
        }
    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Stage stage = (Stage) AjouterMentorPane.getScene().getWindow();
        if (event.getSource().equals(btnReduce)) {

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            stage.close();
        }

    }

    private void ClearChamps() {
        Rusername.setText("");
       
        LocalDate datebirth = RdateNai.getValue();
        Remail.setText("");
        Rnom.setText("");
        Rprenom.setText("");
        Radresse.setText("");
        Rtel.setText("");
    }

    public void Register() {
        try {
            MentorServices mss = new MentorServices();
            code = randomString();
            Mentor m = new Mentor();
            m.setUsername(Rusername.getText());
            m.setPassword(code);
            m.setEmail(Remail.getText());
            m.setNom(Rnom.getText());
            m.setPrenom(Rprenom.getText());

            m.setAdresse(Radresse.getText());
            m.setTel(Rtel.getText());
            m.setDateNai(valueOf(RdateNai.getValue()));
            RadioButton selectedRadioButton = (RadioButton) Sexe.getSelectedToggle();
            String sexe = selectedRadioButton.getText();
            m.setSexe(sexe);
            m.setRole(Rrole.getValue());
            System.out.println(m);

            mss.ajouterMentor(m);
            lblregister.setText("Ajout avec Succes");
            Send();
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

    public String randomString() {
        String characters = "AZERTYUIOPQSDFGHJKLMWXCVBN1234567890azertyuiopqsdfghjklmwxcvbn";

        StringBuilder sb = new StringBuilder();
        int length = 6;

        Random rand = new Random();
        char[] text = new char[6];
        int i = 0;
        while (i < length) {
            sb.append(characters.charAt(rand.nextInt(characters.length())));
            i++;
        }

        return sb.toString();

    }
    String code = "";
    boolean status = false;

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void Send() throws SQLException {

        String username = Rusername.getText();
       

        String email = Remail.getText();

        String to = email;

        
        

        
        String from = "tetrachromat.formation@gmail.com";
        String host = "smtp.gmail.com";
        final String Username = "tetrachromat.formation@gmail.com";
        final String Password = "formation123";
        // String from = Username;

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Username, Password);
            }

        });

        try {
            Message m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            System.out.println(code);

            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            m.setSubject("New Formateur Account");
            m.setText("Bonjour,\n" + Rprenom.getText()
                    + "\n"
                    + "Nous vous souhaitons la bienvenue à Tetraformation.\n"
                    + "\n"
                    + "Votre identifiant : " + username + "\n"
                    + "\n"
                    + "Votre role : " + Rrole.getValue() + "\n"
                    + "\n"
                    + "Mot de passe pour accéder à votre compte est : " + code + "\n"
                    + "\n"
                    + "Bonne journée!\n"
                    + "\n N.B: Ceci est un mail automatique Merci de ne pas répondre.)");
            System.out.println(m);
            Transport.send(m);
            System.out.println("Sent message successfully....");
            lblregister.setText("Consulter l'email");

        } catch (MessagingException e) {

            e.printStackTrace();

        }

    }

}
