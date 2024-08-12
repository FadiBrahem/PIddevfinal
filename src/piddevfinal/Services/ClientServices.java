/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Services;

import piddevfinal.Bunch.SecurityMeasures;

import piddevfinal.Entity.Client;
import piddevfinal.Bunch.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author leila
 */
public class ClientServices {

    Connection con = ConnectionDB.getInstance().getCnx();

    public boolean Authentification(Client c) {
        SecurityMeasures ms = new SecurityMeasures();
        boolean status = false;
        try {
            String req = "SELECT * FROM client  WHERE username = ? and password = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, c.getUsername());
            st.setString(2, ms.encryptMessagetoMD5(c.getPassword()));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                status = true;
                System.out.println(c.getId_client());

            }
        } catch (Exception ex) {

        }
        return status;
    }

    int idclient;

    public int getIdClient(String username) {

        try {

            String req = "SELECT * FROM client  WHERE username = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                idclient = rs.getInt("id_client");

            }
        } catch (SQLException ex) {

        }

        return idclient;
    }

    public void ajouterClient(Client c) throws Exception {
        SecurityMeasures ms = new SecurityMeasures();
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "INSERT INTO `client` (`username`, `email`, `password`, `nom`, `prenom`, `dateNai`, `sexe`, `tel`, `adresse`) VALUES ('" + c.getUsername() + "','" + c.getEmail() + "','" + ms.encryptMessagetoMD5(c.getPassword()) + "','" + c.getNom() + "','" + c.getPrenom() + "','" + c.getDateNai() + "','" + c.getSexe() + "','" + c.getTel() + "','" + c.getAdresse() + "');";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ObservableList<Client> ClientList = FXCollections.observableArrayList();

    public ObservableList<Client> getClientList() {

        try {
            String query = "select * from client";
            PreparedStatement pre = con.prepareStatement(query);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
  

                Client m = new Client(rs.getInt("id_client"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNai"), rs.getString("tel"), rs.getString("adresse"), rs.getString("sexe"));
                ClientList.add(m);
                
             

            }
        } catch (SQLException ex) {
        }

        return ClientList;

    }

    public void modifierClient(Client c) throws Exception {
        SecurityMeasures ms = new SecurityMeasures();
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "UPDATE client SET username = '" + c.getUsername() + "', email = '" + c.getEmail() + "', password = '" + ms.encryptMessagetoMD5(c.getPassword()) + "', nom = '" + c.getNom() + "', prenom = '" + c.getPrenom() + "', dateNai = '" + c.getDateNai() + "', sexe = '" + c.getSexe() + "', tel = '" + c.getTel() + "', adresse = '" + c.getAdresse() + "' WHERE id_client =" + c.getId_client() + ";";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerClient(Client c) {

        Statement stm;
        try {
            stm = con.createStatement();
            String query = "DELETE From client WHERE id_client =" + c.getId_client() + ";";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    public void ModifyClient(Client c) throws Exception {
        SecurityMeasures ms = new SecurityMeasures();
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "UPDATE client SET username = '" + c.getUsername() + "', email = '" + c.getEmail() + "', password = '" + ms.encryptMessagetoMD5(c.getPassword()) + "', nom = '" + c.getNom() + "', prenom = '" + c.getPrenom() + "', dateNai = '" + c.getDateNai() + "', sexe = '" + c.getSexe() + "', tel = '" + c.getTel() + "', adresse = '" + c.getAdresse() + "' WHERE username ='" + c.getUsername() + "';";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void DeleteClient(Client c) {

        Statement stm;
        try {
            stm = con.createStatement();
            String query = "DELETE From client WHERE username ='" + c.getUsername() + "';";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatepass(Client c) throws Exception {
        SecurityMeasures ms = new SecurityMeasures();
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "UPDATE client SET password = '" + ms.encryptMessagetoMD5(c.getPassword()) + "' WHERE username ='" + c.getUsername() + "';";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
