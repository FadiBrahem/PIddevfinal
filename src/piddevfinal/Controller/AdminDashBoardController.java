/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Controller;

import static piddevfinal.Controller.ClientHomepageController.infoBox;
import piddevfinal.Entity.Client;
import piddevfinal.Entity.Exercice;
import piddevfinal.Entity.ExerciceCoursRelationRefined;
import piddevfinal.Entity.Mentor;
import piddevfinal.Services.ClientServices;
import piddevfinal.Services.CoachOperations;
import piddevfinal.Services.MentorServices;
import piddevfinal.Bunch.ConnectionDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fedi
 */
public class AdminDashBoardController implements Initializable {

    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnReduce;
    @FXML
    private AnchorPane AdminDashBoardPane;
    @FXML
    private Button btnClients;
    @FXML
    private Button btnMentors;
    @FXML
    private Button btnExercices;
    @FXML
    private Button btnLogout;
    @FXML
    private JFXButton btnAddClient;
    @FXML
    private Label lbltitle;
    @FXML
    private Pane pnlStatus;

    @FXML
    private TableView<Client> tableClient;

    @FXML
    private TableColumn<Client, Integer> cd_id;
    @FXML
    private TableColumn<Client, String> cd_username;
    @FXML
    private TableColumn<Client, String> cd_email;
    @FXML
    private TableColumn<Client, String> cd_nom;
    @FXML
    private TableColumn<Client, String> cd_prenom;
    @FXML
    private TableColumn<Client, Date> cd_dateNai;
    @FXML
    private TableColumn<Client, String> cd_adresse;
    @FXML
    private TableColumn<Client, String> cd_tel;

    private final Connection con = ConnectionDB.getInstance().getCnx();

    ObservableList<Client> clientlist = FXCollections.observableArrayList();

    ClientServices ccs = new ClientServices();
    ObservableList<Client> list = ccs.getClientList();

    @FXML
    private JFXButton btnRefresh;
    @FXML
    private TableColumn<Client, String> cd_sexe;
    @FXML
    private TextField txtmanageusername;
    @FXML
    private JFXButton btnModify;

    PreparedStatement pst = null;

    int index = -1;
    int idExercice;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TextField filterField;
    @FXML
    private Pane GestionClientPane;
    @FXML
    private Pane GestionMentorPane;
    @FXML
    private TableColumn<Mentor, Integer> cd_id1;
    @FXML
    private TableColumn<Mentor, String> cd_username1;
    @FXML
    private TableColumn<Mentor, String> cd_email1;
    @FXML
    private TableColumn<Mentor, String> cd_nom1;
    @FXML
    private TableColumn<Mentor, String> cd_prenom1;
    @FXML
    private TableColumn<Mentor, Date> cd_dateNai1;
    @FXML
    private TableColumn<Mentor, String> cd_sexe1;
    @FXML
    private TableColumn<Mentor, String> cd_adresse1;
    @FXML
    private TableColumn<Mentor, String> cd_tel1;
    @FXML
    private Pane pnlStatus1;
    @FXML
    private Label lbltitle1;
    private TextField txtmanageusername1;
    @FXML
    private JFXButton btnRefresh1;
    @FXML
    private TextField filterField1;
    @FXML
    private TableView<Mentor> tableMentor;
    @FXML
    private TableColumn<Mentor, String> cd_role;
    @FXML
    private TextField txtIdselectionner;
    @FXML
    private TableView<Exercice> tableExercices;
    @FXML
    private TableColumn<Exercice, Integer> columnIdExercice;
    @FXML
    private TableColumn<Exercice, String> columnNomExercice;
    @FXML
    private TableColumn<Exercice, Integer> columnCoachExercice;
    @FXML
    private TableColumn<Exercice, String> columnStatusExercice;
    @FXML
    private TableColumn<Exercice, String> columnNiveauExercice;
    @FXML
    private Pane pnlStatus11;
    @FXML
    private Label lbltitle11;
    @FXML
    private TextField txtIdselectionner1;
    @FXML
    private JFXCheckBox checkAttente;
    @FXML
    private JFXButton supBtn;
    @FXML
    private JFXButton btnRefresh11;
    @FXML
    private TextField rechSpecPane3;
    @FXML
    private Pane GestionExercicePane;
    @FXML
    private JFXButton btnAccept;
    @FXML
    private JFXButton btnRefuse;

    @FXML
    private Button btnExercices1;

    @FXML
    private TableView<ExerciceCoursRelationRefined> coursExerciceTable;
    @FXML
    private TableColumn<ExerciceCoursRelationRefined, String> colCours;
    @FXML
    private TableColumn<ExerciceCoursRelationRefined, String> colExercice;
    @FXML
    private Pane pnlStatus111;
    @FXML
    private Label lbltitle111;
    @FXML
    private BarChart<?, ?> userChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private BarChart<?, ?> CoursExerciceChart;
    @FXML
    private NumberAxis yAxis1;
    @FXML
    private CategoryAxis xAxis1;
    @FXML
    private Pane GestionCours;
    @FXML
    private Pane pnlStatus3;
    @FXML
    private Label lbltitle3;
    @FXML
    private GridPane pnCategorie;
    @FXML
    private TableView<?> tvCategorie;
    @FXML
    private TableColumn<?, ?> colIdC;
    @FXML
    private TableColumn<?, ?> colNomC;
    @FXML
    private TextField tfSearchC;
    @FXML
    private Button btnAjoutC;
    @FXML
    private Button btnModifC;
    @FXML
    private Button btnSuppC;
    @FXML
    private JFXTextField txttitre;
    @FXML
    private JFXTextField txttitre1;
    @FXML
    private Pane GestionCategorie;
    @FXML
    private JFXButton btnModifyMentor;
    @FXML
    private JFXButton btnDeleteMentor;
    @FXML
    private JFXButton btnAddMentor;

    public void ShowClients() {
        tableClient.getItems().clear();
        ClientServices ms = new ClientServices();
        ObservableList<Client> list1 = ms.getClientList();
        cd_id.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        cd_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        cd_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cd_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cd_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cd_dateNai.setCellValueFactory(new PropertyValueFactory<>("dateNai"));
        cd_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        cd_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cd_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        tableClient.setItems(list1);

    }

    public void ShowMentors() {
        tableMentor.getItems().clear();
        MentorServices ms = new MentorServices();
        ObservableList<Mentor> list1 = ms.getMentorList();
        cd_id1.setCellValueFactory(new PropertyValueFactory<>("id_Mentor"));
        cd_username1.setCellValueFactory(new PropertyValueFactory<>("username"));
        cd_email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        cd_nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cd_prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cd_dateNai1.setCellValueFactory(new PropertyValueFactory<>("dateNai"));
        cd_sexe1.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        cd_adresse1.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cd_tel1.setCellValueFactory(new PropertyValueFactory<>("tel"));
        cd_role.setCellValueFactory(new PropertyValueFactory<>("Role"));

        tableMentor.setItems(list1);

    }

    public void searchClients() {
        ClientServices cs = new ClientServices();
        ObservableList<Client> dataList = cs.getClientList();
        cd_id.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        cd_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        cd_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cd_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cd_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cd_dateNai.setCellValueFactory(new PropertyValueFactory<>("dateNai"));
        cd_sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        cd_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cd_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));

        tableClient.setItems(dataList);

        FilteredList<Client> filteredData = new FilteredList<>(dataList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (client.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.

                } else if (client.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (client.getTel().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else {
                    return client.getNom().toLowerCase().contains(lowerCaseFilter); // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Client> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableClient.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableClient.setItems(sortedData);
    }

    @FXML
    void getSelected(MouseEvent event) {

        index = tableClient.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txtmanageusername.setText(cd_id.getCellData(index).toString());

    }

    @FXML
    void getSelectedMentor(MouseEvent event) {

        index = tableMentor.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        txtIdselectionner.setText(cd_id1.getCellData(index).toString());

    }

    public void Delete() throws IOException {
        int opt = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de Supprimer ?", "Supprimer", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {

            ClientServices cs = new ClientServices();
            Client c = new Client();
            c.setId_client(Integer.valueOf(txtmanageusername.getText()));

            cs.supprimerClient(c);
            System.out.println("SUPPRESSION SUCCES");
            infoBox("Suppression avec succes", null, "Succesfull");

        }
        reload();

    }

    /* Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ShowClients();
        ShowMentors();
        GestionCours.toFront();
        GestionCours.setVisible(false);
 
        GestionCategorie.setVisible(false);

        GestionMentorPane.setVisible(false);
        GestionExercicePane.setVisible(false);
        GestionClientPane.setVisible(true);
        
        this.rechSpecPane3.textProperty().addListener((obs, ov, nv) -> {
            if (rechSpecPane3.getText().length() != 0) {
                this.refreshTableSpecific(rechSpecPane3.getText());
            } else {
                this.showExercice();
            }
        });
        
        
        
        searchClients();

    }

    @FXML
    private void handleClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) AdminDashBoardPane.getScene().getWindow();

        if (event.getSource() == btnClients) {

            ShowClients();
            GestionClientPane.toFront();

        } else if (event.getSource() == btnLogout) {
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
        } else if (event.getSource() == btnAddClient) {
            try {
                Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/AjouterClient.fxml"));
                Scene scene = new Scene(parent);
                Stage stage2 = new Stage();
                stage2.setScene(scene);
                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.show();

            } catch (IOException ex) {
                Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (event.getSource() == btnModify) {
            index = tableClient.getSelectionModel().getSelectedIndex();
            if (index <= -1) {

                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("piddevfinal/UI/ModifierClient.fxml"));
            //  Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/ModifierClient.fxml"));
            //      Scene scene = new Scene(parent);
            Parent root = (Parent) loader.load();
            ModifierClientController setController = loader.getController();
            setController.initData(tableClient.getSelectionModel().getSelectedItem());
            Stage stage0 = new Stage();
            stage0.setScene(new Scene(root));
            stage0.initStyle(StageStyle.UNDECORATED);
            stage0.show();

        }
        
        if (event.getSource() == btnClients) {
        
        
        
        }
        

    }

    @FXML
    private void handleMouseButton(MouseEvent event) {

        if (event.getSource().equals(btnReduce)) {
            Stage stage = (Stage) AdminDashBoardPane.getScene().getWindow();

            stage.setIconified(true);
        }

        if (event.getSource().equals(btnClose)) {
            System.exit(0);
        }

    }

    @FXML
    public void reload() throws IOException {
        this.showExercice();
    }

    @FXML
    public void AddMentor(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/AjouterMentor.fxml"));
            Scene scene = new Scene(parent);
            Stage stage2 = new Stage();
            stage2.setScene(scene);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.show();

        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    private void AddEvent1(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/AjouterEvent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage3 = new Stage();
            stage3.setScene(scene);
            stage3.initStyle(StageStyle.UNDECORATED);
            stage3.show();

        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void UpdateMentor(ActionEvent event) {

        index = tableMentor.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("piddevfinal/UI/ModifierMentor.fxml"));
        //  Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("piddevfinal/UI/ModifierClient.fxml"));
        //      Scene scene = new Scene(parent);
        Parent root;
        try {
            root = (Parent) loader.load();
            ModifierMentorController setController = loader.getController();
            setController.initData(tableMentor.getSelectionModel().getSelectedItem());
            Stage stage0 = new Stage();
            stage0.setScene(new Scene(root));
            stage0.initStyle(StageStyle.UNDECORATED);
            stage0.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void DeleteMentor(ActionEvent event) {

        int opt = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de Supprimer ?", "Supprimer", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {

            MentorServices mss = new MentorServices();
            Mentor m = new Mentor();
            m.setId_Mentor(Integer.valueOf(txtIdselectionner.getText()));

            mss.supprimerMentor(m);
            GestionMentorPane.toFront();
            System.out.println("SUPPRESSION SUCCES");
            infoBox("Suppression avec succes", null, "Succesfull");
            ShowMentors();

        }

    }
    
    
      @FXML
    private void DeleteClient(ActionEvent event) {

        int opt = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de Supprimer ?", "Supprimer", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {

            ClientServices css = new ClientServices();
            Client c = new Client();
            c.setId_client(Integer.valueOf(txtmanageusername.getText()));
            
            css.supprimerClient(c);
            GestionClientPane.toFront();
            System.out.println("SUPPRESSION SUCCES");
            infoBox("Suppression avec succes", null, "Succesfull");
            ShowClients();

        }

    }

    @FXML
    private void toMentorPane(ActionEvent event) {

        GestionMentorPane.toFront();
        GestionCours.setVisible(false);
        GestionCours.setVisible(false);
        
        GestionCategorie.setVisible(false);

        GestionMentorPane.setVisible(true);
        GestionExercicePane.setVisible(false);
        GestionClientPane.setVisible(false);
        ShowMentors();

    }
  @FXML
    private void toClientPane(ActionEvent event) {

        GestionClientPane.toFront();
        GestionCours.setVisible(false);
        GestionCours.setVisible(false);

        GestionCategorie.setVisible(false);

        GestionMentorPane.setVisible(false);
        GestionExercicePane.setVisible(false);
        GestionClientPane.setVisible(true);
        ShowClients();

    }
    

    @FXML
    private void checkAttenteChange(ActionEvent event) {
        this.showExercice();
    }

    @FXML
    private void supBtnPress(ActionEvent event) {
        CoachOperations co = new CoachOperations();
        int id = this.tableExercices.getSelectionModel().getSelectedItem().getIdExercice();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer cet Exercice?");
        Optional<ButtonType> btn = alert.showAndWait();
        if (btn.get() == ButtonType.OK) {
            co.deleteExercice(id);
            this.showExercice();
        } else {
            alert.close();
        }
    }

    //FETCH SPECIFIC EXERCICES AS ADMIN
    public List<Exercice> fetchSpecificExercices(String kw) {
        try {
            CoachOperations co = new CoachOperations();
            ResultSet data = co.viewExercicesSpecific(kw);
            List el = new LinkedList();
            while (data.next()) {
                el.add(new Exercice(data.getInt("idExercice"), data.getString("nomExercice"), data.getInt("id_Mentor"), data.getString("stateExercice"), data.getString("niveauExercice")));
            }
            return el;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void refreshTableSpecific(String kw) {
        try {
            columnIdExercice.setCellValueFactory(new PropertyValueFactory<>("idExercice"));
            columnNomExercice.setCellValueFactory(new PropertyValueFactory<>("nomExercice"));
            columnCoachExercice.setCellValueFactory(new PropertyValueFactory<>("id_Mentor"));
            columnStatusExercice.setCellValueFactory(new PropertyValueFactory<>("stateExercice"));
            columnNiveauExercice.setCellValueFactory(new PropertyValueFactory<>("niveauExercice"));
            if (checkAttente.isSelected()) {
                tableExercices.getItems().setAll(fetchOnlyEnAttente());
            } else {
                tableExercices.getItems().setAll(fetchSpecificExercices(kw));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchSpecificExercices() {
        this.rechSpecPane3.textProperty().addListener((obs, ov, nv) -> {
            if (rechSpecPane3.getText().length() != 0) {
                this.refreshTableSpecific(rechSpecPane3.getText());
            } else {
                this.refreshTable();
            }
        });
    }

    //EXERCICE AZIZ
    @FXML
    private void toExercicePane(ActionEvent event) {
        GestionExercicePane.toFront();
         GestionCours.setVisible(false);
        GestionCours.setVisible(false);
       
        GestionCategorie.setVisible(false);

        GestionMentorPane.setVisible(false);
        GestionExercicePane.setVisible(true);
        GestionClientPane.setVisible(false);
        showExercice();
    }

    public List<Exercice> fetchAllExercices() {
        try {
            CoachOperations co = new CoachOperations();
            ResultSet data = co.viewAllExercices();
            List el = new LinkedList();
            while (data.next()) {
                Exercice ex = new Exercice();
                ex.setIdExercice(data.getInt("idExercice"));
                ex.setNomExercice(data.getString("nomExercice"));
                ex.setIdMentor(data.getInt("id_Mentor"));
                ex.setDescExercice(data.getString("stateExercice"));
                ex.setNiveauExercice(data.getString("niveauExercice"));
                ex.setStateExercice(data.getString("stateExercice"));
                el.add(ex);
            }
            return el;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private List<Exercice> fetchOnlyEnAttente() {
        try {
            CoachOperations co = new CoachOperations();
            co = new CoachOperations();
            ResultSet data = co.exercicesEnAttente();
            List el = new LinkedList();
            while (data.next()) {
                el.add(new Exercice(data.getInt("idExercice"), data.getString("nomExercice"), data.getInt("id_Mentor"), data.getString("stateExercice"), data.getString("niveauExercice")));
            }
            return el;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void showExercice() {
        try {
            tableExercices.getItems().clear();
            columnIdExercice.setCellValueFactory(new PropertyValueFactory<>("idExercice"));
            columnNomExercice.setCellValueFactory(new PropertyValueFactory<>("nomExercice"));
            columnCoachExercice.setCellValueFactory(new PropertyValueFactory<>("id_Mentor"));
            columnStatusExercice.setCellValueFactory(new PropertyValueFactory<>("stateExercice"));
            columnNiveauExercice.setCellValueFactory(new PropertyValueFactory<>("niveauExercice"));
            if (checkAttente.isSelected()) {
                tableExercices.getItems().setAll(fetchOnlyEnAttente());
            } else {
                tableExercices.getItems().setAll(fetchAllExercices());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void StateChange(ActionEvent event) {
        CoachOperations co = new CoachOperations();
        Exercice e = new Exercice();
        e.setIdExercice(idExercice);
        if (event.getSource() == btnAccept) {
            e.setStateExercice("ACCEPTEE");

            co.updatestate(e);
        } else if (event.getSource() == btnRefuse) {
            e.setStateExercice("REFUSEE");
            co.updatestate(e);
        }
        showExercice();
    }

    @FXML
    public void getSelectedExercice(MouseEvent event) {
        index = tableExercices.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        idExercice = (int) columnIdExercice.getCellData(index);
        System.out.println(idExercice);
    }

    // LOUAY WORK CATEGORIE
    @FXML
    private void handleClicks(ActionEvent event) {

    }
    //--------------------------------

    @FXML
    private void toCoursPane(ActionEvent event) {
        GestionCours.toFront();
        GestionCours.setVisible(true);
      
        GestionCategorie.setVisible(false);

        GestionMentorPane.setVisible(false);
        GestionExercicePane.setVisible(false);
        GestionClientPane.setVisible(false);
        populateLeftChart();
        populateRightChart();
        refreshTable();
    }

    //POPULATE LEFT CHART
    public void populateLeftChart() {
        CoachOperations co = new CoachOperations();
        userChart.getData().clear();
        ResultSet data = co.viewCoaches();

        XYChart.Series set1 = new XYChart.Series<>();
        XYChart.Series set2 = new XYChart.Series<>();
        set1.setName("Formation");
        set2.setName("participant");
        try {
            while (data.next()) {
                set1.getData().add(new XYChart.Data(data.getString("username"), data.getInt("nbrCours")));
                set2.getData().add(new XYChart.Data(data.getString("username"), data.getInt("nbrExercices")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        yAxis.setLabel("Quantité");
        xAxis.setLabel("Tutor");

        userChart.getData().addAll(set1, set2);
    }

    //POPULATE RIGHT CHART
    public void populateRightChart() {
        CoachOperations co = new CoachOperations();
        CoursExerciceChart.getData().clear();
        ResultSet data1 = co.viewStatCoursEx();

        XYChart.Series set = new XYChart.Series<>();

        set.setName("Nombre de participant");

        try {
            while (data1.next()) {
                set.getData().add(new XYChart.Data(co.getCoursName(data1.getInt("idCours")), data1.getInt("cnt")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        yAxis1.setLabel("Participant");
        xAxis1.setLabel("formation");

        CoursExerciceChart.getData().add(set);
    }

    //FETCH COURS DATA
    public List<ExerciceCoursRelationRefined> fetchAll() {
        try {
            CoachOperations co = new CoachOperations();
            ResultSet data = co.viewAllRelation();
            List ecl = new LinkedList();
            while (data.next()) {
                ecl.add(new ExerciceCoursRelationRefined(co.getCoursName(data.getInt("idCours")), co.getExerciceName(data.getInt("idExercice"))));
            }
            return ecl;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void refreshTable() {
        try {
            colCours.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
            colExercice.setCellValueFactory(new PropertyValueFactory<>("nomExercice"));
            coursExerciceTable.getItems().setAll(fetchAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //--------------
   

    private void toCategoriePane(ActionEvent event) {
        GestionCategorie.toFront();
        GestionCours.setVisible(false);
        
        GestionCategorie.setVisible(true);
       
        GestionMentorPane.setVisible(false);
        GestionExercicePane.setVisible(false);
        GestionClientPane.setVisible(false);
    }

}
