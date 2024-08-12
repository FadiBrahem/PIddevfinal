/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Services;

import piddevfinal.Entity.Mentor;
import piddevfinal.Bunch.ConnectionDB;
import piddevfinal.Bunch.SecurityMeasures;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author leila
 */
public class MentorServices {

    private Connection con;

    public MentorServices() {
        con = ConnectionDB.getInstance().getCnx();

    }
    boolean status = false;

    public boolean Authentification(Mentor m) throws Exception {
        SecurityMeasures ms = new SecurityMeasures();
        try {
            String req = "SELECT * FROM mentor  WHERE username = ? and password = ? and Role = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, m.getUsername());
            st.setString(2, ms.encryptMessagetoMD5(m.getPassword()));
            st.setString(3, m.getRole());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                System.out.println(st);

                System.out.println(m.getId_Mentor());

                if ("CHEF".equals(m.getRole())) {

                    status = true;
                } else {

                    status = true;
                }

            }
        } catch (SQLException ex) {

        }
        return status;
    }

    int idmentor;

    public int getIdMentor(String username) {

        try {

            String req = "SELECT * FROM mentor  WHERE username = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                idmentor = rs.getInt("id_mentor");

            }
        } catch (SQLException ex) {

        }

        return idmentor;
    }

    private void showDialog(String info, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    ObservableList<Mentor> MentorList = FXCollections.observableArrayList();

    public ObservableList<Mentor> getMentorList() {

        try {
            String query = "select * from mentor";
            PreparedStatement pre = con.prepareStatement(query);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Mentor m = new Mentor(rs.getInt("id_Mentor"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNai"), rs.getString("tel"), rs.getString("adresse"), rs.getString("sexe"), rs.getString("Role"), rs.getInt("nbrExercices"), rs.getInt("nbrCours"));
                MentorList.add(m);

            }
        } catch (SQLException ex) {

        }

        return MentorList;

    }

    public void ajouterMentor(Mentor m) throws Exception {

        Statement stm;
        SecurityMeasures ms = new SecurityMeasures();
        try {
            stm = con.createStatement();
            String query = "INSERT INTO `mentor` (`username`, `email`, `password`, `nom`, `prenom`, `dateNai`, `sexe`, `tel`, `adresse`, `Role`, `nbrExercices`, `nbrCours`) VALUES ('" + m.getUsername() + "','" + m.getEmail() + "','" + ms.encryptMessagetoMD5(m.getPassword()) + "','" + m.getNom() + "','" + m.getPrenom() + "','" + m.getDateNai() + "','" + m.getSexe() + "','" + m.getTel() + "','" + m.getAdresse() + "','" + m.getRole() + "', 0, 0);";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierMentor(Mentor m) throws Exception {
        SecurityMeasures ms = new SecurityMeasures();
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "UPDATE mentor SET username = '" + m.getUsername() + "', email = '" + m.getEmail() + "', password = '" + ms.encryptMessagetoMD5(m.getPassword()) + "', nom = '" + m.getNom() + "', prenom = '" + m.getPrenom() + "', dateNai = '" + m.getDateNai() + "', sexe = '" + m.getSexe() + "', tel = '" + m.getTel() + "', adresse = '" + m.getAdresse() + "', Role = '" + m.getRole() + "' WHERE id_Mentor = " + m.getId_Mentor() + ";";
            stm.executeUpdate(query);
            System.out.println(query);

        } catch (SQLException ex) {
            Logger.getLogger(MentorServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerMentor(Mentor m) {

        Statement stm;
        try {
            stm = con.createStatement();
            String query = "DELETE From mentor WHERE id_Mentor =" + m.getId_Mentor() + ";";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatepass(Mentor m) throws Exception {
        SecurityMeasures ms = new SecurityMeasures();
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "UPDATE mentor SET password = '" + ms.encryptMessagetoMD5(m.getPassword()) + "' WHERE username ='" + m.getUsername() + "';";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
