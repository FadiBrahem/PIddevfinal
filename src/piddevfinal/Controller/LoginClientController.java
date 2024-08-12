/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import static piddevfinal.Controller.ClientHomepageController.infoBox;
import piddevfinal.Entity.Client;
import piddevfinal.Services.ClientServices;
import piddevfinal.Bunch.ConnectionDB;

import animatefx.animation.FadeIn;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class LoginClientController implements Initializable {

     @FXML
    private JFXButton btnSignUp;
    @FXML
    private Pane paneLogin;
    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private Pane paneSignUp;
    @FXML
    private JFXButton BTNSignIn;
    @FXML
    private AnchorPane ClientPane;
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
    private JFXTextArea Radresse;
    @FXML
    private JFXTextField Rtel;
    @FXML
    private JFXButton BTNSignUp;
    @FXML
    private JFXButton btnReset;
    @FXML
    private ImageView btnBack;
    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnReduce;

    @FXML
    private Hyperlink ForgotPass;
    @FXML
    private Pane paneForgotPass;
    @FXML
    private JFXButton btnSignIn1;
    @FXML
    private JFXDatePicker RdateNai;
    @FXML
    private JFXRadioButton registerMale;
    @FXML
    private ToggleGroup Sexe;
    @FXML
    private JFXRadioButton registerFemale;
    @FXML
    private Label lblregister;

    private final Connection con = ConnectionDB.getInstance().getCnx();
    @FXML
    private Label lblerror;

    public static int stringToInt(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    @FXML
    private JFXTextField txtforgotemail;
    @FXML
    private JFXButton BTNsendcode;
    @FXML
    private Label lblregister2;
    @FXML
    private Pane paneForgotPass1;
    @FXML
    private JFXTextField txtcode;
    @FXML
    private JFXButton BTNsendcode1;
    @FXML
    private JFXButton btnSignIn11;
    @FXML
    private Pane paneForgotPass2;
    @FXML
    private JFXPasswordField txtnewpassword;
    @FXML
    private JFXPasswordField txtnewpassword2;
    @FXML
    private JFXButton BTNsendcode11;
    @FXML
    private JFXButton btnSignIn111;
    @FXML
    private Label lblnewpassword;

    /**
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     * @throws Exception
     */
   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException, Exception {

        if (event.getSource() == btnSignUp) {
            new FadeIn(paneSignUp).play();
            paneSignUp.toFront();
        }

        if (event.getSource() == btnSignIn1) {
            new FadeIn(paneLogin).play();
            paneLogin.toFront();
        }

        if (event.getSource() == btnSignIn) {
            new FadeIn(paneLogin).play();
            paneLogin.toFront();
        }

        if (event.getSource() == ForgotPass) {
            new FadeIn(paneForgotPass).play();
            paneForgotPass.toFront();
        }
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
                try {
                    Stage stage = (Stage) ClientPane.getScene().getWindow();
                    Stage stage2 = new Stage();
                    stage.close();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getClassLoader().getResource("piddevfinal/UI/ClientHomepage.fxml"));

                    Parent root = (Parent) loader.load();
              ClientHomepageController setController = loader.getController();
               

                    stage2.setScene(new Scene(root));
                    stage2.initStyle(StageStyle.UNDECORATED);
                    stage2.show();

                } catch (IOException ex) {
                    Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    

          

        if (event.getSource() == BTNSignIn) {

            if (txtusername.getText().isEmpty() || txtpassword.getText().isEmpty()) {

                lblerror.setText("VEUILLEZ REMPLIR TOUS LES CHAMPS");
            } else {

                lblerror.setText("");
                Login();

            }

        }

    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Stage stage = (Stage) ClientPane.getScene().getWindow();

        if (event.getSource().equals(btnBack)) {
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

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    private void Register() throws SQLException, Exception {

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
    private void ClearChamps(ActionEvent event) {
        Rusername.setText("");
        Rpassword.setText("");

        Remail.setText("");
        Rnom.setText("");
        Rprenom.setText("");
        Radresse.setText("");
        Rtel.setText("");

    }

    /*   public void Login() throws IOException {

        ClientServices cs = new ClientServices();
        Client c = new Client();

        c.setUsername(txtusername.getText());
        c.setPassword(txtpassword.getText());

        if (!cs.Authentification(c)) {
            txtusername.setText("");
            txtpassword.setText("");

            lblerror.setText("MOT DE PASSE / USERNAME INCORRECT");
        } else {
            lblerror.setText("");
            System.out.println("connecté");

        }

    }*/
    public void Login() {

        String username = txtusername.getText();
        String password = txtpassword.getText();
        ClientServices cs = new ClientServices();
        Client c = new Client();

        c.setUsername(txtusername.getText());
        c.setPassword(txtpassword.getText());
        if (!cs.Authentification(c)) {
            txtusername.setText("");
            txtpassword.setText("");

            lblerror.setText("MOT DE PASSE / USERNAME INCORRECT");
        } else {
            Stage stage = (Stage) ClientPane.getScene().getWindow();
            Stage stage2 = new Stage();
            stage.close();

            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("piddevfinal/UI/ClientHomepage.fxml"));

                Parent root = (Parent) loader.load();
                ClientHomepageController setController = loader.getController();
                setController.initData(username);

                stage2.setScene(new Scene(root));
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.show();

            } catch (IOException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public String randomString() {
        String characters = "AZERTYUIOPQSDFGHJKLMWXCVBN1234567890";
        String randomString = "";
        StringBuilder sb = new StringBuilder();
        int length = 5;

        Random rand = new Random();
        char[] text = new char[5];
        int i = 0;
        while (i < length) {
            sb.append(characters.charAt(rand.nextInt(characters.length())));
            i++;
        }

        return sb.toString();

    }
    String code = "";
    boolean status = false;

    @FXML
    public void Send(ActionEvent event) throws SQLException {

        if (event.getSource().equals(BTNsendcode)) {

            String username = txtforgotemail.getText();
            code = randomString();

            String query = "select * from client WHERE username = ?";

            PreparedStatement pre = con.prepareStatement(query);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                status = true;

                String email = rs.getString("email");

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

                if (txtforgotemail.getText().isEmpty()) {

                    lblregister2.setText("VEUILLEZ REMPLIR TOUS LES CHAMPS");
                } else {
                    try {
                        Message m = new MimeMessage(session);
                        m.setFrom(new InternetAddress(from));

                        m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                        m.setSubject("Recuperation de compte");
                        m.setText("Cher Client, Votre code de changement de mot de passe est = " + code);
                        System.out.println(m);
                        Transport.send(m);
                        System.out.println("Sent message successfully....");
                        lblregister2.setText("Consulter votre email");

                        paneForgotPass1.toFront();

                    } catch (MessagingException e) {

                        e.printStackTrace();

                    }
                }

            }
        }
        if (event.getSource().equals(BTNsendcode1)) {

            System.out.println(code + " " + txtcode.getText());

            if (txtcode.getText().equals(code)) {
                paneForgotPass2.toFront();
                lblregister2.setText("");

            } else {
                lblregister2.setText("Le code est incorrect");
            }

        }
    }

    @FXML
    private void updatepassword(ActionEvent event) throws Exception {
        System.out.println(txtforgotemail.getText());
        if (txtnewpassword.getText().isEmpty() || txtnewpassword2.getText().isEmpty()) {
            lblnewpassword.setText("saisir mot de passe");
        } else if (!txtnewpassword.getText().equals(txtnewpassword2.getText())) {
            lblnewpassword.setText("erreur mot de passe");
        } else {
            Client c = new Client();
            c.setPassword(txtnewpassword.getText());
            c.setUsername(txtforgotemail.getText());
            ClientServices cs = new ClientServices();
            cs.updatepass(c);
            System.out.println("CHANGEMENT SUCCES");
            infoBox("Changement de mot de passe avec succes", null, "Succesfull");
            Stage stage = (Stage) ClientPane.getScene().getWindow();
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
    }

    @FXML
    private void toforgotpass(ActionEvent event
    ) {
        paneForgotPass.toFront();
        txtnewpassword.setText("");
        txtnewpassword2.setText("");
        txtforgotemail.setText("");
    }

}
