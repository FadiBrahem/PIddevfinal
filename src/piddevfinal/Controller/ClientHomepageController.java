/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import piddevfinal.Entity.Client;
import piddevfinal.Entity.Cours;
import piddevfinal.Entity.Exercice;
import piddevfinal.Entity.Todolist;
import piddevfinal.Services.ClientServices;
import piddevfinal.Services.CoachOperations;
import piddevfinal.Services.TodoServices;
import piddevfinal.Bunch.ConnectionDB;
import piddevfinal.Bunch.SecurityMeasures;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import static java.sql.Date.valueOf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class ClientHomepageController implements Initializable {

    @FXML
    private AnchorPane ClientHomepage;
    @FXML
    private Button btnClients;
    @FXML
    private Button btnMentors;
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
    private Label lblregister;
    @FXML
    private Pane HomepagePane;
    @FXML
    private Label lblusername;
    private String SelectedClient;
    @FXML
    private Label lblusername11;
    @FXML
    private Label lblusername111;

    private final Connection con = ConnectionDB.getInstance().getCnx();
    public String oldpassword;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private Pane ToDolistPane;
    @FXML
    private TableView<Todolist> tableTodolist;
    @FXML
    private TableColumn<Todolist, Integer> cd_id;
    @FXML
    private TableColumn<Todolist, String> cd_username;
    @FXML
    private TableColumn<Todolist, String> cd_titre;
    @FXML
    private TableColumn<Todolist, String> cd_description;
    @FXML
    private TableColumn<Todolist, Date> cd_datecreated;
    @FXML
    private JFXButton btnModifytodo;
    @FXML
    private JFXButton btnDeletetodo;
    @FXML
    private JFXButton btnAddTodo;
    @FXML
    private JFXTextField txttitre;
    @FXML
    private JFXTextArea txtdescription;
    int index = -1;
    int id;
    int idtodo = 0;
    ObservableList<Todolist> Todolist = FXCollections.observableArrayList();
    Client c = new Client();
    TodoServices todo = new TodoServices();

    @FXML
    private TextField id_client;
    Todolist t = new Todolist();
    @FXML
    private Button btnReservation;
    @FXML
    private Label lblajout;
    @FXML
    private Label lblidclient;
    @FXML
    private TextField id_client1;
    @FXML
    private Label lblajout1;
    @FXML
    private TableView<?> tableEvenements;
    @FXML
    private TableColumn<?, ?> c_id;
    @FXML
    private TableColumn<?, ?> c_nom;
    @FXML
    private TableColumn<?, ?> c_Organisateur;
    @FXML
    private TableColumn<?, ?> c_Date_Deb;
    @FXML
    private TableColumn<?, ?> c_Date_Fin;
    @FXML
    private TableColumn<?, ?> c_type;
    @FXML
    private TableColumn<?, ?> CapaciteRes;
    @FXML
    private TableColumn<?, ?> Cp_Image;
    @FXML
    private TableColumn<?, ?> cp_Path;
    @FXML
    private JFXButton res;
    @FXML
    private JFXTextField IdReservation;
    @FXML
    private JFXTextField TypeResEv;
    @FXML
    private JFXTextField NomEvReservation;
    @FXML
    private JFXTextField Matricule;
    @FXML
    private TextField Recherche;
    @FXML
    private JFXButton Enregistrer;
    @FXML
    private JFXButton Statistique;
    @FXML
    private JFXTextField ID_EVE;
    @FXML
    private JFXTextField CapFild;
    @FXML
    private ImageView Ev_res_Image;
    @FXML
    private Pane ReservationPane;
    @FXML
    private Pane coursPane;
    @FXML
    private TableView<Cours> CoursTable;
    @FXML
    private TableColumn<?, ?> coursnameColumn;
    @FXML
    private TableView<Exercice> exerciceTable;
    @FXML
    private TableColumn<?, ?> exerciceNomColumn;
    @FXML
    private Pane exerciceDetailsPane;
    @FXML
    private Label titreExercice;
    @FXML
    private Label niveauExercice;
    @FXML
    private ImageView imgExercice;
    @FXML
    private Label descExercice;

    /**
     * Initializes the controller class.
     *
     * @param username
     */
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

                ClientServices cs = new ClientServices();
                Client c = new Client();

                c.setId_client(id);
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

                lblregister.setText("done");
                cs.modifierClient(c);
                System.out.println(c);
            } else {
                lblregister.setText("invalide");
            }

        } else {
            lblregister.setText("MOT DE PASSE ACTUEL INCORRECT");
        }

    }

    public void initData(String username) {
        lblusername.setText(username);
        ClientServices cs = new ClientServices();
        id = cs.getIdClient(username);
        System.out.println(id);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ClientServices cs = new ClientServices();
        id = cs.getIdClient(lblusername.getText());

    }

    @FXML
    private void handleClick(ActionEvent event) {
        Stage stage = (Stage) ClientHomepage.getScene().getWindow();
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
    private void toProfile(ActionEvent event) {
        ToDolistPane.setVisible(false);
        HomepagePane.setVisible(false);
        ProfilPane.setVisible(true);
         ReservationPane.setVisible(false);
         coursPane.setVisible(false);
        new FadeIn(ProfilPane).play();
        setvalues();
        lblregister.setText("");

    }

    public void setvalues() {
        String username = lblusername.getText();
        String query = "select * from client WHERE username = ?";

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
    public void toToDoList(ActionEvent event) {
        ProfilPane.setVisible(false);
        HomepagePane.setVisible(false);
        ToDolistPane.setVisible(true);
        coursPane.setVisible(false);
         ReservationPane.setVisible(false);
        new FadeIn(ToDolistPane).play();
        showtodolist();

    }

 
    public void showtodolist() {
        tableTodolist.getItems().clear();
        ObservableList<Todolist> list = todo.getTodolist(id);

        cd_id.setCellValueFactory(new PropertyValueFactory<Todolist, Integer>("id_todo"));
        cd_username.setCellValueFactory(new PropertyValueFactory<Todolist, String>("id_client"));
        cd_titre.setCellValueFactory(new PropertyValueFactory<Todolist, String>("titretodo"));
        cd_description.setCellValueFactory(new PropertyValueFactory<Todolist, String>("description"));
        cd_datecreated.setCellValueFactory(new PropertyValueFactory<Todolist, Date>("datecreated"));

        tableTodolist.setItems(list);

        System.out.println(list);
    }

    /* public void showTodolist() {
       
      

    }*/
    @FXML
    private void toRecette(ActionEvent event) {
    }

    @FXML
    private void toExercice(ActionEvent event) {
    }


    @FXML
    private void handleMouseButton(MouseEvent event) {
        if (event.getSource().equals(btnReduce)) {
            Stage stage = (Stage) ClientHomepage.getScene().getWindow();

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            System.exit(0);
        }
    }

    @FXML
    private void toHomePage(ActionEvent event) {
        ToDolistPane.setVisible(false);
        ProfilPane.setVisible(false);
        HomepagePane.setVisible(true);
        ReservationPane.setVisible(false);
         coursPane.setVisible(false);
        new FadeIn(HomepagePane).play();
    }

    public static int stringToInt(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @FXML
    private void Delete(ActionEvent event) {

        int opt = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de désactiver ?", "Desactiver", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            ClientServices cs = new ClientServices();
            Client c = new Client();
            c.setId_client(id);
            cs.supprimerClient(c);
            System.out.println("SUPPRESSION SUCCES");
            infoBox("Desactivation avec succes", null, "Succesfull");
            Stage stage = (Stage) ClientHomepage.getScene().getWindow();
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

    @FXML
    public void getSelected(MouseEvent event) {
        index = tableTodolist.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        idtodo = (int) cd_id.getCellData(index);
        System.out.println(idtodo);

    }

    @FXML
    public void ModifierTache(ActionEvent event) throws SQLException, Exception {

        Timestamp datecreated = Timestamp.valueOf(LocalDateTime.now());

        TodoServices ts = new TodoServices();
        Todolist t = new Todolist();
        t.setId_todo(idtodo);
        t.setTitretodo(txttitre.getText());
        t.setDescription(txtdescription.getText());
        t.setDatecreated(datecreated);
        ts.modifierTodo(t);
        showtodolist();
        System.out.println(t);
        txttitre.setText("");
      txtdescription.setText("");

    }

    @FXML
    public void SupprimerTache(ActionEvent event) throws SQLException, IOException {
        int opt = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de désactiver ?", "Desactiver", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            TodoServices ts = new TodoServices();
            Todolist t = new Todolist();
            t.setId_todo(idtodo);
            ts.supprimerTodo(t);
            System.out.println(t);

             showtodolist();
        }
    }

    @FXML
   public void AjouterTache(ActionEvent event) throws SQLException, IOException {

        Timestamp datecreated = Timestamp.valueOf(LocalDateTime.now());
        TodoServices ts = new TodoServices();
        Todolist t = new Todolist();
        t.setTitretodo(txttitre.getText());
        t.setDescription(txtdescription.getText());
        t.setDatecreated(datecreated);
        t.setId_client(id);
        ts.ajouterTodo(t);
        System.out.println(t);
         showtodolist();
      txttitre.setText("");
      txtdescription.setText("");

    }

    public void reload() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("piddevfinal/UI/ClientHomepage.fxml"));

            Parent root = (Parent) loader.load();
            ClientHomepageController setController = loader.getController();
            setController.initData(lblusername.getText());

            Stage stage = (Stage) tableTodolist.getScene().getWindow();
            stage.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void toReservation(ActionEvent event) {
        ToDolistPane.setVisible(false);
        ProfilPane.setVisible(false);
        HomepagePane.setVisible(false);
        coursPane.setVisible(false);
        ReservationPane.setVisible(true);
        Matricule.setText(SelectedClient);
    }

    @FXML
    private void MouseAction(MouseEvent event) {
    }

    @FXML
    private void reserver(ActionEvent event) {
    }

    @FXML
    private void PDF(ActionEvent event) {
    }

    @FXML
    private void ChartStat(ActionEvent event) {
    }

    @FXML
    private void toCours(ActionEvent event) {
        ToDolistPane.setVisible(false);
        ProfilPane.setVisible(false);
        HomepagePane.setVisible(false);
        ReservationPane.setVisible(false);
        coursPane.setVisible(true);
        refreshCoursTable();
        manageExercicesTable();
        manageDetailsPane();
    }
    
    public List<Cours> getAllCours(){
        try{
            CoachOperations co = new CoachOperations();
            Cours cours;
            ResultSet data= co.viewAllCourses();
            List cl = new LinkedList();
            while(data.next()){
                cours = new Cours();
                cours.setIdCours(data.getInt("idCours"));
                cours.setNomCours(data.getString("nomCours"));
                cl.add(cours);
            }
            return cl;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void refreshCoursTable(){
        try{
            coursnameColumn.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
            CoursTable.getItems().setAll(getAllCours());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public List<Exercice> fetchExercicesOfCours(String nomCours){
        try{
            CoachOperations co = new CoachOperations();
            ResultSet data = co.viewExercicesOfCours(co.getCoursId(nomCours));
            List el = new LinkedList();
            while(data.next()){
                Exercice ex = new Exercice();
                ex.setNomExercice(data.getString("nomExercice"));
                el.add(ex);
            }
            return el;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void refreshExerciceTable(String nomCours){
        try{
            exerciceNomColumn.setCellValueFactory(new PropertyValueFactory<>("nomExercice"));
            exerciceTable.getItems().setAll(fetchExercicesOfCours(nomCours));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void manageExercicesTable(){
        this.CoursTable.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection)-> {
            if(newSelection != null){
                this.refreshExerciceTable(this.CoursTable.getSelectionModel().getSelectedItem().getNomCours());
            }
        });
    }
    
    public void manageDetailsPane(){
        exerciceTable.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                try{
                    CoachOperations co = new CoachOperations();
                    Exercice ex = new Exercice();
                    ex = co.getExerciceCredByName(this.exerciceTable.getSelectionModel().getSelectedItem().getNomExercice());
                    this.titreExercice.setText(titreExercice.getText()+ex.getNomExercice());
                    this.niveauExercice.setText(niveauExercice.getText()+ex.getNiveauExercice());
                    this.imgExercice.setImage(new Image(new FileInputStream(ex.getPathImageExercice())));
                    this.descExercice.setText(descExercice.getText()+ex.getDescExercice());
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    
    

}
