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
public class ModifierMentorController implements Initializable {

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

    private Mentor SelectedMentor;

    private final Connection con = ConnectionDB.getInstance().getCnx();
    @FXML
    private AnchorPane ModifierMentorPane;
    @FXML
    private JFXTextField Rid;

    /**
     * Initializes the controller class.
     */
    public void initData(Mentor mentor) {

        SelectedMentor = mentor;
        Rid.setText(SelectedMentor.getId_Mentor().toString());
        Rusername.setText(SelectedMentor.getUsername());
        Remail.setText(SelectedMentor.getEmail());
        Rpassword.setText(SelectedMentor.getPassword());
        Rnom.setText(SelectedMentor.getNom());
        Rprenom.setText(SelectedMentor.getPrenom());
        LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(SelectedMentor.getDateNai()));
        RdateNai.setValue(localDate);

        String sex = SelectedMentor.getSexe();

        if ("MALE".equals(sex)) {
            registerMale.setSelected(true);
        } else {
            registerFemale.setSelected(true);
        }

        Rtel.setText(SelectedMentor.getTel());
        Radresse.setText(SelectedMentor.getAdresse());
        
        Rrole.setValue(SelectedMentor.getRole());

    }

    public void Update() throws Exception {

        Boolean controle = true;

        if (!isValidEmailAddress(Remail.getText())) {

            controle = false;
            lblregister.setText("EMAIL UNCORRECT");
        }

        if (Rusername.getText().isEmpty() || Rpassword.getText().isEmpty() || Remail.getText().isEmpty() || Rnom.getText().isEmpty() || Rprenom.getText().isEmpty()) {

            controle = false;
            lblregister.setText("VEUILLEZ REMPLIR TOUS LES CHAMPS");
        }

        if (RdateNai.getValue().isAfter(LocalDate.of(2009, Month.DECEMBER, 31))) {

            controle = false;
            lblregister.setText("VOUS DEVEZ AVOIR PLUS DE 12 ANS");

        }

        //query
        if (controle) {
            lblregister.setText("");
            MentorServices ms = new MentorServices();
            Mentor m = new Mentor();
            m.setId_Mentor(Integer.valueOf(Rid.getText()));
            m.setUsername(Rusername.getText());
            m.setPassword(Rpassword.getText());
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
            lblregister.setText("done");
            ms.modifierMentor(m);
            System.out.println(m);
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

    @FXML
    private void handleClick(ActionEvent event) throws Exception {
        if (event.getSource() == BTNSignUp) {
            Update();
        }
    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Stage stage = (Stage) ModifierMentorPane.getScene().getWindow();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Rrole.getItems().add("CHEF");
        Rrole.getItems().add("COACH");
    }

}
