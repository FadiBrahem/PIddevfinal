/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import piddevfinal.Entity.Cours;
import piddevfinal.Entity.Exercice;
import piddevfinal.Entity.ExerciceCoursRelation;
import piddevfinal.Entity.Mentor;
import piddevfinal.Services.CoachOperations;
import piddevfinal.Services.MentorServices;
import piddevfinal.Bunch.ConnectionDB;
import piddevfinal.Bunch.SecurityMeasures;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import static java.sql.Date.valueOf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class CoachHomepageController implements Initializable {

    @FXML
    private AnchorPane CoachHomepage;
    @FXML
    private Button btnClients;
    @FXML
    private Button btnExercices;
    @FXML
    private Button btnLogout;
    @FXML
    private ImageView btnReduce;
    @FXML
    private ImageView btnClose;
    @FXML
    private Pane ProfilPane;
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
    private JFXPasswordField Rpassword1;
    @FXML
    private JFXButton BTNSignUp;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private Label lblregister;

    @FXML
    private JFXButton btnModifytodo;
    @FXML
    private JFXButton btnDeletetodo;
    @FXML
    private TextField id_client;
    @FXML
    private Label lblajout;
    @FXML
    private Pane HomepagePane;
    @FXML
    private Label lblusername11;
    @FXML
    private Label lblusername;
    @FXML
    private Label lblusername111;
    @FXML
    private Label lblidclient;
    @FXML
    private Button btnCours;
    @FXML
    private Pane ExercicePane;

    int index = -1;
    int id;
    int idExercice = 0;
    private final Connection con = ConnectionDB.getInstance().getCnx();
    public String oldpassword;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXTextField fieldNomExercice;
    @FXML
    private JFXTextArea fieldDescExercice;
    @FXML
    private JFXComboBox<String> fieldNiveauExercice;
    @FXML
    private TableColumn<Exercice, String> colIdNom;
    @FXML
    private TableColumn<Exercice, String> colState;
    @FXML
    private TableColumn<Exercice, String> colNiveau;
    @FXML
    private TableColumn<Exercice, String> colDesc;
    @FXML
    private TableView<Exercice> tableExercice;
    @FXML
    private TableColumn<Exercice, Integer> cd_id;

    CoachOperations co = new CoachOperations();
    @FXML
    private TableColumn<?, ?> cd_username;
    @FXML
    private ImageView addedImage;
    @FXML
    private JFXButton btnAddImage;
    @FXML
    private ImageView selectedImage;
    @FXML
    private Pane coursPane;
    @FXML
    private TableView<Cours> coursTable;
    @FXML
    private TableColumn<?, ?> colNomCours;
    @FXML
    private TableColumn<?, ?> colDescCours;
    @FXML
    private TableColumn<?, ?> colNbExercices;
    @FXML
    private JFXButton btnAddCours;
    @FXML
    private JFXButton btnSupprimerCours;
    @FXML
    private TextField searchFieldCours;
    @FXML
    private TextField nomCoursMod;
    @FXML
    private TextArea descCoursMod;
    @FXML
    private JFXButton btnModCours;
    @FXML
    private ComboBox<String> cbExercices;
    @FXML
    private JFXButton btnAddExerciceToCours;
    @FXML
    private TextField nomCoursAdd;
    @FXML
    private TextField descCoursAdd;
    @FXML
    private TextField idCoursMod;
    @FXML
    private Pane coursPane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        MentorServices ms = new MentorServices();
        id = ms.getIdMentor(lblusername.getText());
        fieldNiveauExercice.getItems().addAll("Debutant", "Intermediaire", "Expert");
        System.out.println(id);
    }

    /*  public void refreshTable() {
        try {
            cd_id.setCellValueFactory(new PropertyValueFactory<>("idExercice"));
            colIdNom.setCellValueFactory(new PropertyValueFactory<>("nomExercice"));
            colNiveau.setCellValueFactory(new PropertyValueFactory<>("niveauExercice"));
            colState.setCellValueFactory(new PropertyValueFactory<>("stateExercice"));
            colDesc.setCellValueFactory(new PropertyValueFactory<>("descExercice"));
            tableExercice.getItems().setAll(fetchAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Exercice> fetchAll() {
        try {
            CoachOperations co = new CoachOperations();
            ResultSet data = co.viewExercicesByCoach(4);
            List el = new LinkedList();
            while (data.next()) {
                el.add(new Exercice(data.getInt("idExercice"), data.getString("nomExercice"), data.getInt("idCoach"), data.getString("stateExercice"), data.getString("niveauExercice"), data.getString("descExercice")));
            }
            return el;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }*/
    public void showExercices() {
        tableExercice.getItems().clear();
     ObservableList<Exercice> list = co.viewExercicesByCoach(id);
        System.out.println("this is id " + id);

        cd_id.setCellValueFactory(new PropertyValueFactory<>("idExercice"));
        colIdNom.setCellValueFactory(new PropertyValueFactory<>("nomExercice"));
        colNiveau.setCellValueFactory(new PropertyValueFactory<>("niveauExercice"));
        colState.setCellValueFactory(new PropertyValueFactory<>("stateExercice"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descExercice"));

        tableExercice.setItems(list);

        System.out.println(list);
    }

    @FXML
    private void toHomePage(ActionEvent event) {
        ExercicePane.setVisible(false);
        ProfilPane.setVisible(false);
        HomepagePane.setVisible(true);
 coursPane.setVisible(false);
        new FadeIn(HomepagePane).play();
    }

    @FXML
    private void toProfile(ActionEvent event) {
        ExercicePane.setVisible(false);
        ProfilPane.setVisible(true);
         coursPane.setVisible(false);
        HomepagePane.setVisible(false);
        setvalues();
        lblregister.setText("");
        new FadeIn(ProfilPane).play();
    }

    @FXML
    private void toExercice(ActionEvent event) {
        ExercicePane.setVisible(true);
        ProfilPane.setVisible(false);
        coursPane.setVisible(false);
        HomepagePane.setVisible(false);
        showExercices();

        new FadeIn(ExercicePane).play();
    }
    
    
    
    
    @FXML
    private void toCours(ActionEvent event) {
        this.coursPane.setVisible(true);
        ExercicePane.setVisible(false);
        ProfilPane.setVisible(false);
        HomepagePane.setVisible(false);
        this.searchFieldCours.setDisable(false);
        this.refreshTableCours();
        greyOut();
        populateCb();

        this.coursTable.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.ungreyOut();
                Cours cours = this.coursTable.getSelectionModel().getSelectedItem();
                nomCoursMod.setText(cours.getNomCours());
                descCoursMod.setText(cours.getDescCours());
                idCoursMod.setText(""+cours.getIdCours());
            }
        });
        
        this.searchFieldCours.textProperty().addListener((obs, ov, nv) -> {
            if (searchFieldCours.getText().length() != 0){
                this.refreshTableSpecific(searchFieldCours.getText());
            }else{
                this.refreshTableCours();
            }
        } );

    }

    @FXML
    private void handleClick(ActionEvent event) {
        Stage stage = (Stage) CoachHomepage.getScene().getWindow();
        if (event.getSource() == btnLogout) {
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
    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        if (event.getSource().equals(btnReduce)) {
            Stage stage = (Stage) CoachHomepage.getScene().getWindow();

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            System.exit(0);
        }
    }

    public void initData(String username) {
        lblusername.setText(username);
        MentorServices ms = new MentorServices();
        id = ms.getIdMentor(username);
        System.out.println(id);
    }

    public void setvalues() {
        String username = lblusername.getText();
        String query = "select * from mentor WHERE username = ?";

        try {
            PreparedStatement pre = con.prepareStatement(query);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Rusername.setText(rs.getString("username"));
                Remail.setText(rs.getString("email"));
                Rpassword.setText(rs.getString("password"));
                oldpassword = rs.getString("password");
                Rnom.setText(rs.getString("nom"));
                Rprenom.setText(rs.getString("prenom"));

                RdateNai.setValue(rs.getDate("dateNai").toLocalDate());

                if ("HOMME".equals(rs.getString("sexe"))) {
                    registerMale.setSelected(true);
                } else {
                    registerFemale.setSelected(true);
                }
                Rtel.setText(rs.getString("tel"));
                Radresse.setText(rs.getString("adresse"));
                System.out.println(oldpassword);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientHomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void Update(ActionEvent event) throws Exception {
        SecurityMeasures ms = new SecurityMeasures();
        System.out.println(oldpassword + "  " + Rpassword1.getText());

        if (oldpassword.equals(ms.encryptMessagetoMD5(Rpassword1.getText()))) {
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

                MentorServices mss = new MentorServices();
                Mentor m = new Mentor();

                m.setId_Mentor(id);
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

                lblregister.setText("done");
                mss.modifierMentor(m);
                System.out.println(m);
            } else {
                lblregister.setText("invalide");
            }

        } else {
            lblregister.setText("MOT DE PASSE ACTUEL INCORRECT");
        }

    }

    @FXML
    private void Delete(ActionEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {

        CoachOperations co = new CoachOperations();
        tableExercice.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
            try {
                Exercice ex = co.getExerciceCredByName(this.tableExercice.getSelectionModel().getSelectedItem().getNomExercice());
                if (ex.getPathImageExercice().length() < 5) {
                    selectedImage.setImage(new Image(new FileInputStream("D:\\tetrachromatIntegration1\\PIDEV\\src\\Icons\\noImage.png")));
                } else {
                    selectedImage.setImage(new Image(new FileInputStream(ex.getPathImageExercice())));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

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

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


    public List<Cours> fetchAllCours() {
        try {
            CoachOperations co = new CoachOperations();
            Cours cours;
            ResultSet data = co.viewCoursesByCoach(id);
            List cl = new LinkedList();
            while (data.next()) {
                cours = new Cours();
                cours.setIdCours(data.getInt("idCours"));
                cours.setId_Mentor(data.getInt("id_Mentor"));
                cours.setDescCours(data.getString("descCours"));
                cours.setNbExercices(data.getInt("nbExercices"));
                cours.setNomCours(data.getString("nomCours"));
                cl.add(cours);
            }
            return cl;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void refreshTableCours() {
        try {
            colNomCours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
            colDescCours.setCellValueFactory(new PropertyValueFactory<>("descCours"));
            colNbExercices.setCellValueFactory(new PropertyValueFactory<>("nbExercices"));
            coursTable.getItems().setAll(fetchAllCours());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void ModifierExercice(ActionEvent event) {
        co = new CoachOperations();
        Exercice ex = new Exercice();
        //ex.setIdCoach(4);
        ex.setDescExercice(fieldDescExercice.getText());
        ex.setNomExercice(fieldNomExercice.getText());
        ex.setIdExercice(idExercice);
        ex.setNiveauExercice(fieldNiveauExercice.getValue());
        co.updateExercice(ex);
        showExercices();
    }

    @FXML
    public void SupprimerExercice(ActionEvent event) {
        Exercice Exercice = this.tableExercice.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer cet Exercice?");
        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            co.deleteExercice(idExercice);
            this.refreshTableCours();
            System.out.println(idExercice);
            showExercices();
        } else {
            alert.close();
        }
        
      
       
    }

    String sDevolved;
    String s;
    String p;

    @FXML
    private void addImage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null) {
            String path = f.getAbsolutePath();
            addedImage.setImage(new Image("file:///" + path));
            sDevolved = path;
            s = "file:///" + path;
            p = f.getAbsolutePath();
        } else {
            addedImage.setImage(new Image("D:\\tetrachromatIntegration1\\PIDEV\\src\\Icons\\noImage.png"));
        }

    }

    

    @FXML
    private void onBtnSumbitPress(ActionEvent event) {
        CoachOperations co = new CoachOperations();
        Exercice ex = new Exercice();
        ex.setNomExercice(fieldNomExercice.getText());
        ex.setNiveauExercice(fieldNiveauExercice.getValue());
        ex.setDescExercice(fieldDescExercice.getText());
        System.out.println(sDevolved);
        ex.setPathImageExercice(sDevolved);
        ex.setIdMentor(id);
        System.out.println(ex.getPathImageExercice());
        co.proposeExercice(ex);
    }

    @FXML
    private void onBtnAddCoursPress(ActionEvent event) {
        CoachOperations co = new CoachOperations();
        Cours c = new Cours();
        c.setNomCours(nomCoursAdd.getText());
        c.setDescCours(descCoursAdd.getText());
        c.setId_Mentor(id);
        co.createCours(c);
        this.refreshTableCours();
    }

    @FXML
    private void onBtnSupprimerCoursPress(ActionEvent event) {
        Cours cours = this.coursTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous vraiment Supprimer ce Cours?");
        Optional<ButtonType> btn = alert.showAndWait();
        if(btn.get() == ButtonType.OK){
            co.deleteCours(cours);
            this.refreshTableCours();
            greyOut();
        }else{
            alert.close();
        }
        
    }

    @FXML
    private void onBtnModCoursPress(ActionEvent event) {
        CoachOperations co = new CoachOperations();
        Cours cours = new Cours();
        cours.setDescCours(descCoursMod.getText());
        cours.setNomCours(nomCoursMod.getText());
        cours.setIdCours(Integer.parseInt(idCoursMod.getText()));
        co.updateCours(cours);
        this.refreshTableCours();
        
    }

    @FXML
    private void onAddExerciceToCoursBtnPress(ActionEvent event) {
        try {
            if (cbExercices.getValue() != "") {
                CoachOperations co = new CoachOperations();
                int idExercice = co.getExerciceId(cbExercices.getValue());
                ExerciceCoursRelation ecr = new ExerciceCoursRelation();
                ecr.setIdCours(coursTable.getSelectionModel().getSelectedItem().getIdCours());
                ecr.setIdExercice(idExercice);
                co.addExerciceToCours(ecr,id);
                refreshTableCours();
                greyOut();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void greyOut() {
        this.descCoursMod.setDisable(true);
        this.btnModCours.setDisable(true);
        this.nomCoursMod.setDisable(true);
        this.cbExercices.setDisable(true);
    }

    public void ungreyOut() {
        this.descCoursMod.setDisable(false);
        this.btnModCours.setDisable(false);
        this.nomCoursMod.setDisable(false);
        this.cbExercices.setDisable(false);
        this.btnAddExerciceToCours.setDisable(false);
    }

    public void populateCb() {
        try {
            CoachOperations co = new CoachOperations();
            ResultSet data = co.viewAllExercices();
            while (data.next()) {
                cbExercices.getItems().add(data.getString("nomExercice"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Cours> fetchAllSpecific(String kw){
        try{
            CoachOperations co = new CoachOperations();
            Cours cours;
            ResultSet data = co.viewSpecificCoursesByCoach(id, kw);
            List cl = new LinkedList();
            while(data.next()){
                cours = new Cours();
                cours.setIdCours(data.getInt("idCours"));
                cours.setId_Mentor(data.getInt("id_Mentor"));
                cours.setDescCours(data.getString("descCours"));
                cours.setNbExercices(data.getInt("nbExercices"));
                cours.setNomCours(data.getString("nomCours"));
                cl.add(cours);
            }
            return cl;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void refreshTableSpecific(String kw){
        try{
            colNomCours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
            colDescCours.setCellValueFactory(new PropertyValueFactory<>("descCours"));
            colNbExercices.setCellValueFactory(new PropertyValueFactory<>("nbExercices"));
            coursTable.getItems().setAll(fetchAllSpecific(kw));
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
