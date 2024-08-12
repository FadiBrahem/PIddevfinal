/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Services;

import piddevfinal.Entity.Evenement;
import piddevfinal.Bunch.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.image.ImageView;
import java.time.LocalDate;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EvenementServices {
    private Connection con;
    private Statement ste;

    public EvenementServices() {
        con = ConnectionDB.getInstance().getCnx();
    }
    
     
    public void ajouter(Evenement e) throws SQLException{
        try {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `formation`.`Evenement` (`Id`, `Nom`, `Organisateur`, `Date_Debut`, `Date_Fin`, `Type` ,`capacite`, ` img` ) VALUES (?, ?, ?, ?, ?,?,?,?);");
    pre.setString(1, e.getId());
    pre.setString(2, e.getNom());
    pre.setString(3, e.getOrganisateur());
    pre.setDate(4, (java.sql.Date) e.getDate_Debut());
    pre.setDate(5, (java.sql.Date) e.getDate_Fin());
    pre.setString(6,  e.getType());
    pre.setString(7, e.getImg());
    pre.executeUpdate();
    System.out.println("formation ajoutee !");

} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
    }
    
   public ObservableList<Evenement> getEvenementList() {
        ObservableList<Evenement> EvenementList = FXCollections.observableArrayList();
   
        try {
            ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from Evenement");
             while (rs.next()) {
            String id = rs.getString("Id");
            String Nom = rs.getString("Nom");
            String Organisateur = rs.getString("Organisateur");
            Date Date_Debut = rs.getDate("Date_Debut");
            Date Date_Fin = rs.getDate("Date_Fin");
            String Type = rs.getString("Type");
            String Capacite = rs.getString("Capacite");
            String img = rs.getString("img");
  
           Evenement e=new Evenement(id, Nom, Type, Organisateur, Date_Debut, Date_Fin, Capacite, img);
           EvenementList.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
return EvenementList;   
}
   
 public void modifierEvenement(Evenement ev) {
        Statement stm;
        try {
            stm = con.createStatement();
            String query ="UPDATE Evenement SET Id ='"+ev.getId()+"',Nom='"+ev.getNom()+"',Organisateur='"+ev.getOrganisateur()+"',Capacite='"+ev.getCapacite()+"',Type='"+ev.getType()+"',Date_Debut='"+ev.getDate_Debut()+"',Date_Fin='"+ev.getDate_Fin()+"'img='"+ev.getImg()+ "'WHERE Id = '"+ev.getId()+"' ";
            
            
            
      
            stm.executeUpdate(query);
            System.out.println(query);
            } catch (Exception e) {
            e.printStackTrace(); 
            }
    }
    
     public void supprimerEvenement(Evenement eve) {

        Statement stm;
        try {
            stm = con.createStatement();
            String query= "DELETE FROM Evenement WHERE Id = "+eve.getId() +"";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
    
   
   

