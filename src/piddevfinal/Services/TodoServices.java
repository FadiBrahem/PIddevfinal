/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Services;

import piddevfinal.Entity.Todolist;
import piddevfinal.Bunch.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author leila
 */
public class TodoServices {

    private Connection con;
    private Statement ste;

    public TodoServices() {
        con = ConnectionDB.getInstance().getCnx();

    }

    ObservableList<Todolist> Todolist = FXCollections.observableArrayList();

    // Todolist t = new Todolist();
    public ObservableList<Todolist> getTodolist(int id) {

        try {
           
            String query = "SELECT * FROM todolist WHERE id_client = " + id + ";";
             System.out.println(query);
            PreparedStatement pre = con.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id_todo = rs.getInt("id_todo");
                String titretodo = rs.getString("titretodo");
                String description = rs.getString("description");
                Date datecreated = rs.getDate("datecreated");
                int id_client = rs.getInt("id_client");

                Todolist o = new Todolist(id_todo, titretodo, description, datecreated, id_client);
                Todolist.add(o);
                System.out.println(o);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Todolist;

    }

    public void ajouterTodo(Todolist t) {
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "INSERT INTO `todolist` (`titretodo`, `description`, `datecreated`, `id_client`) VALUES ('" + t.getTitretodo() + "','" + t.getDescription() + "','" + t.getDatecreated() + "'," + t.getId_client() + ");";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
            public void modifierTodo(Todolist t) throws Exception {
  
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "UPDATE todolist SET titretodo = '" + t.getTitretodo() +"', description = '" + t.getDescription() + "', datecreated = '" + t.getDatecreated() + "' WHERE id_todo =" + t.getId_todo()+ ";";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
         
    
        
    
    
     public void supprimerTodo(Todolist t) {

        Statement stm;
        try {
            stm = con.createStatement();
            String query = "DELETE From todolist WHERE id_todo =" + t.getId_todo() + ";";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

}
