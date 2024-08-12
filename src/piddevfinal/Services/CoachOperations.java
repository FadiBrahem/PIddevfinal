/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Services;

import piddevfinal.Entity.Cours;
import piddevfinal.Entity.Exercice;
import piddevfinal.Entity.ExerciceCoursRelation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import piddevfinal.Bunch.ConnectionDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class CoachOperations {

    Connection connection;
    ConnectionDB dbc;

    /* -----------------INSERTING SECTION----------------- */
    //STAYS THE SAME
    //PROPOSE EXERCICE TO ADMIN
    public String proposeExercice(Exercice exercice) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Proposing Exercice for Admin...");
            String query = "INSERT INTO Exercice (nomExercice,descExercice,niveauExercice,stateExercice,id_Mentor) VALUES (?,?,?,?,?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, exercice.getNomExercice());
            statement.setString(2, exercice.getDescExercice());
            statement.setString(3, exercice.getNiveauExercice());
            statement.setString(4, "En Attente");
            statement.setInt(5, exercice.getIdMentor());

            statement.executeUpdate();
            System.out.println("Exercice Propose a l'Administrateur!");
            String update = "UPDATE mentor SET nbrExercices = nbrExercices + 1 WHERE id_Mentor = " + exercice.getIdMentor();
            System.out.println(update);
            
            statement = connection.prepareStatement(update);
            statement.executeUpdate();
            System.out.println("Coach Updated!");
            return "Exercice Propose a l'Administrateur!";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }

    //STAYS THE SAME
    //INSERT COURS
    public String createCours(Cours cours) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Adding a row to Table 'Cours'...");
            String query = "INSERT INTO COURS (nomCours,descCours,nbExercices,id_Mentor) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cours.getNomCours());
            statement.setString(2, cours.getDescCours());
            statement.setInt(3, 0);
            statement.setInt(4, cours.getId_Mentor());
            statement.executeUpdate();
            System.out.println("Cours Added!");
            String update = "UPDATE Coach SET nbrCours = nbrCours + 1 WHERE id_Mentor = " + cours.getId_Mentor();
            statement = connection.prepareStatement(update);
            statement.executeUpdate();
            System.out.println("Coach Updated!");
            return "Row Added to Table 'Cours' !";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    //SHOW ALL COACHES
    public ResultSet viewCoaches() {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Fetching all Coaches...");
            String query = "SELECT * FROM Mentor WHERE Role LIKE 'COACH'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    //GET THE NAME OF THE COACH FROM THEIR ID
    public String getNameCoach(int id_Mentor){
        try{
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            String query = "SELECT username FROM mentor WHERE id_Mentor = "+id_Mentor+" AND role LIKE 'COACH'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String username = "";
            while (rs.next()){
                username = rs.getString("username");
                break;
            }
            return username;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return null;
        }
        
    }
    
    //SHOW ID OF COURS BY NAME
    public int getCoursId(String nomCours)throws SQLException{
        dbc = new ConnectionDB();
        connection = dbc.getCnx();
        String query = "SELECT idCours FROM Cours WHERE nomCours = '"+nomCours+"'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int id = 0;
        while(rs.next()){
            id = rs.getInt("idCours");
            break;
        }
        return id;
    }
    
    //SHOW EXERCICE CREDENTIALS BY NAME
    public Exercice getExerciceCredByName(String nomExercice) throws SQLException{
        dbc = new ConnectionDB();
        connection = dbc.getCnx();
        String query = "SELECT * FROM Exercice WHERE nomExercice = '"+nomExercice+"'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        Exercice ex = new Exercice();
        while (rs.next()){
            ex.setNomExercice(rs.getString("nomExercice"));
            ex.setNiveauExercice(rs.getString("niveauExercice"));
            ex.setDescExercice(rs.getString("descExercice"));
            ex.setPathImageExercice(rs.getString("pathImageExercice"));
            break;
        }
        return ex;
    }

    //STAYS THE SAME
    //ADD EXERCICE TO COURS
    public String addExerciceToCours(ExerciceCoursRelation ecr, int id_Mentor) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Adding Exercice to Cours " + ecr.getIdCours());
            String query = "INSERT INTO exercice_cours_relation(idCours,idExercice) VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ecr.getIdCours());
            statement.setInt(2, ecr.getIdExercice());
            statement.executeUpdate();
            System.out.println("Exercice Added to Cours");
            String update = "UPDATE Cours SET nbExercices = nbExercices +1 WHERE id_Mentor = " + id_Mentor;
            statement = connection.prepareStatement(update);
            statement.executeUpdate();
            System.out.println("Number of Exercices updated in Cours " + ecr.getIdCours());
            return "Row added and row updated";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }


    /* -----------------DELETING SECTION----------------- */
    //STAYS THE SAME
    //DELETE COURS
    public String deleteCours(Cours cours) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Deleting A Row from Table 'Cours'...");
            String query = "DELETE FROM Cours WHERE idCours = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cours.getIdCours());
            statement.executeUpdate();
            System.out.println("Cours Deleted!");
            String update = "UPDATE Coach SET nbrCours = nbrCours -1 WHERE idCoach = " + cours.getId_Mentor();
            statement = connection.prepareStatement(update);
            statement.executeUpdate();
            System.out.println("Coach Updated!");
            return "Cours Deleted!";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
    
    //SHOW EXERCICES OF CERTAIN COURS
    public ResultSet viewExercicesOfCours(int idCours) throws SQLException{
        dbc = new ConnectionDB();
        connection = dbc.getCnx();
        String query = "SELECT idCours,(SELECT nomExercice FROM Exercice WHERE idExercice = exercice_cours_relation.idExercice) as nomExercice from exercice_cours_relation WHERE idCours = "+idCours;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    //STAYS THE SAME
    //DELETE EXERCICE
    public void deleteExercice(int id) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Deleting Exercice...");
            String query = "DELETE FROM Exercice WHERE idExercice = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("EXERCICE DELETED!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //STAYS THE SAME
    //DELETE EXERCICE (FROM COURS)
    public String deleteExerciceFromCours(Exercice exercice, Cours cours) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Removing Exercice From Course...");
            String query = "DELETE FROM exercice_cours_relation WHERE (idCours = ? AND idExercice = ?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cours.getIdCours());
            statement.setInt(2, exercice.getIdExercice());
            statement.executeUpdate();
            return "Exercice Removed From Course!";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    /* -----------------UPDATING SECTION----------------- */
    //STAYS THE SAME
    //UPDATE COURS
    public String updateCours(Cours cours) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Updating an Exsiting Course...");
            String query = "UPDATE cours SET nomCours = ? , descCours = ? WHERE idCours = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cours.getNomCours());
            statement.setString(2, cours.getDescCours());
            statement.setInt(3, cours.getIdCours());
            statement.executeUpdate();
            return "Cours Updated!";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    //STAYS THE SAME
    //UPDATE STATE EXERCICE
    public void updateStateExercice(int id, String state) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Updating State...");
            String query = "UPDATE Exercice SET stateExercice = ? WHERE idExercice = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, state);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("UPDATED!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //STAYS THE SAME
    //UPDATE EXERCICE
    public String updateExercice(Exercice exercice) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Updating an Exsiting Exercice...");
            String query = "UPDATE exercice SET nomExercice = ? , descExercice = ? , niveauExercice = ? WHERE idExercice = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, exercice.getNomExercice());
            statement.setString(2, exercice.getDescExercice());
            statement.setString(3, exercice.getNiveauExercice());
            statement.setInt(4, exercice.getIdExercice());
            statement.executeUpdate();
            return "Exercice Updated!";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    //UPDATE COACH (AS COACH)
    /* -----------------Missing CODE HERE----------------- */

 /* -----------------SELECTING SECTION----------------- */
    //SHOW ALL COURSES
    public ResultSet viewAllCourses() {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Fetching all Courses...");
            String query = "SELECT * FROM Cours";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //SHOW ALL EXERCICES
    public ResultSet viewAllExercices() {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Fetching all Exercicces...");
            String query = "SELECT * FROM exercice";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //SHOW SPECIFIC EXERCICES
    public ResultSet viewExercicesSpecific(String keyword) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            String query = "SELECT * FROM exercice WHERE nomExercice LIKE '%" + keyword + "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //SHOW COURSES BY COACH NAME
    public ResultSet viewCoursesByCoach(int id) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Fetching courses of this Coach...");
            String query = "SELECT * FROM Cours WHERE id_Mentor = " + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //SHOW SPECIFIC COURSES BY COACH NAME
    public ResultSet viewSpecificCoursesByCoach(int id, String keyword) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            String query = "SELECT * FROM Cours WHERE id_Mentor = " + id + " AND nomCours LIKE '%" + keyword + "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //SHOW EXERCICES BY COACH NAME
       ObservableList<Exercice> Exercicelist = FXCollections.observableArrayList();
    public ObservableList<Exercice> viewExercicesByCoach(int id) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Fetching Exercices of this Coach...");
            String query = "SELECT * FROM Exercice WHERE id_Mentor = " + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
           System.out.println(query);
            while (rs.next()) {
                int idExercice = rs.getInt("idExercice");
                String nomExercice = rs.getString("nomExercice");
                String descExercice = rs.getString("descExercice");
               String niveauExercice = rs.getString("niveauExercice");
               String stateExercice = rs.getString("stateExercice");
                int id_mentor = rs.getInt("id_Mentor");

                Exercice e = new Exercice(idExercice, nomExercice, descExercice, niveauExercice, stateExercice, id_mentor);
                Exercicelist.add(e);
                System.out.println(e);
            }
           

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return Exercicelist;
    }

    //SHOW SPECIFIC EXERCICES BY COACH NAME
    public ResultSet viewSpecificExercicesByCoach(int id, String keyword) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            String query = "SELECT * FROM Exercice WHERE idCoach = " + id + " AND nomExercice LIKE '%" + keyword + "%'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //SHOW EXERCICES OF A CERTAIN COURSE
    public ResultSet viewExercicesByCourse(Cours cours) {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            System.out.println("Fetching Exercices of this Cours...");
            String query = "SELECT * FROM Exercice WHERE idExercice IN (SELECT idExercice FROM exercice_cours_relation WHERE idCours = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cours.getIdCours());
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //SHOW EXERCICE ID USING ITS NAME
    public int getExerciceId(String nomExercice) throws SQLException {

        dbc = new ConnectionDB();
        connection = dbc.getCnx();
        System.out.println("Fetching Exercice ID by Name...");
        String query = "SELECT idExercice FROM Exercice WHERE nomExercice = '" + nomExercice + "'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("idExercice");
            break;
        }
        return id;

    }

    //SHOW ONLY STANDBY EXERCICES
    public ResultSet exercicesEnAttente() throws SQLException {
        dbc = new ConnectionDB();
        connection = dbc.getCnx();
        System.out.println("Fetching only Standby Exercices...");
        String query = "SELECT * FROM Exercice WHERE stateExercice LIKE 'En Attente'";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    //SHOW ALL EXERCICE COURS RELATIONS
    public ResultSet viewAllRelation() throws SQLException {
        dbc = new ConnectionDB();
        connection = dbc.getCnx();
        System.out.println("Fetching All Exercice-Cours Relations...");
        String query = "SELECT * FROM exercice_cours_relation";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        System.out.println(rs.toString());
        return rs;
    }

    //SHOW CERTAIN EXERCICE NAME
    public String getExerciceName(int id) throws SQLException {
        dbc = new ConnectionDB();
        connection = dbc.getCnx();
        String query = "SELECT nomExercice FROM Exercice WHERE idExercice = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        String name = "";
        while (rs.next()) {
            name = rs.getString("nomExercice");
            break;
        }
        return name;
    }

    //SHOW CERTAIN COURS NAME
    public String getCoursName(int id) throws SQLException {
        dbc = new ConnectionDB();
        connection = dbc.getCnx();
        String query = "SELECT nomCours FROM Cours WHERE idCours = " + id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        String name = "";
        while (rs.next()) {
            name = rs.getString("nomCours");
            break;
        }
        return name;
    }

    //FOR SECOND CHART
    public ResultSet viewStatCoursEx() {
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            String query = "SELECT idCours,count(*) as cnt FROM exercice_cours_relation GROUP BY idCours";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    //UPDATE STATE OF EXERCICE
    public void updatestate(Exercice e){
        Statement stm;
        try {
            dbc = new ConnectionDB();
            connection = dbc.getCnx();
            stm = connection.createStatement();
            String query = "UPDATE exercice SET stateExercice = '" + e.getStateExercice()+ "' WHERE idExercice ='" + e.getIdExercice() + "';";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

}
