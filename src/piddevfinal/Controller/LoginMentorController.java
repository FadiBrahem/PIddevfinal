/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import static piddevfinal.Controller.ClientHomepageController.infoBox;
import piddevfinal.Entity.Mentor;
import piddevfinal.Services.MentorServices;
import piddevfinal.Bunch.ConnectionDB;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class LoginMentorController implements Initializable {
    
      private final Connection con = ConnectionDB.getInstance().getCnx();

    @FXML
    private JFXComboBox<String> role;
    @FXML
    private AnchorPane MentorPane;
    @FXML
    private Pane paneLogin;
    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXButton BTNSignIn;
    @FXML
    private ImageView btnBack;
    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnReduce;
    @FXML
    private Label lblerror;
    @FXML
    private Hyperlink ForgotPass;
    @FXML
    private Pane paneForgotPass;
    @FXML
    private JFXTextField txtforgotemail;
    @FXML
    private JFXButton BTNsendcode;
    @FXML
    private JFXButton btnSignIn1;
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

    @FXML
    public void Login() throws Exception {

        MentorServices ms = new MentorServices();
        Mentor m = new Mentor();
        if (txtusername.getText().isEmpty() || txtpassword.getText().isEmpty()) {
            lblerror.setText("VEUILLEZ REMPLIR TOUS LES CHAMPS");
        } else {
            lblerror.setText("");
            m.setUsername(txtusername.getText());
            m.setPassword(txtpassword.getText());
            m.setRole(role.getValue());
        }
        if (!ms.Authentification(m)) {
            txtusername.setText("");
            txtpassword.setText("");

            lblerror.setText("MOT DE PASSE / USERNAME INCORRECT");
        } else {
            if ("COACH".equals(role.getValue())){
                System.out.println(role.getValue());
               
            Stage stage = (Stage) MentorPane.getScene().getWindow();
            Stage stage2 = new Stage();
            stage.close();

            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("piddevfinal/UI/CoachHomepage.fxml"));

                Parent root = (Parent) loader.load();
                CoachHomepageController setController = loader.getController();
                setController.initData(txtusername.getText());
                System.out.println(txtusername.getText());

                stage2.setScene(new Scene(root));
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.show();

            } catch (IOException ex) {
                Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
            }
            lblerror.setText("");

        }

    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Stage stage = (Stage) MentorPane.getScene().getWindow();
        Stage stage2 = new Stage();
        if (event.getSource().equals(btnBack)) {
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
    
        role.getItems().add("COACH");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == ForgotPass) {
            new FadeIn(paneForgotPass).play();
            paneForgotPass.toFront();
        }
    }
    
    @FXML
   private void toforgotpass(ActionEvent event) {
        paneForgotPass.toFront();
        txtnewpassword.setText("");
        txtnewpassword2.setText("");
        txtforgotemail.setText("");
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

            String query = "select * from mentor WHERE username = ?";

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
                        m.setText("Cher Formateur, \n Votre code de changement de mot de passe est = " + code);
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
            Mentor m = new Mentor();
            m.setPassword(txtnewpassword.getText());
            m.setUsername(txtforgotemail.getText());
            MentorServices mss = new MentorServices();
            mss.updatepass(m);
            System.out.println("CHANGEMENT SUCCES");
            infoBox("Changement de mot de passe avec succes", null, "Succesfull");
            Stage stage = (Stage) MentorPane.getScene().getWindow();
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

}
