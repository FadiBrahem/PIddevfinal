package piddevfinal.Services;


 



import piddevfinal.Entity.Reservation;
import piddevfinal.Bunch.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReservationService {
    
    private Connection con;
    private Statement ste;

    public ReservationService() {
        con = ConnectionDB.getInstance().getCnx();
    }
    
   public ObservableList<Reservation> getReservationList() {
        ObservableList<Reservation> ReservationList = FXCollections.observableArrayList();
        try {
            ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from Reservation");
             while (rs.next()) {
            String IdRes = rs.getString("IdRes");
            String NomEvRes = rs.getString("NomEvRes");
            String TypeEvRes = rs.getString("TypeEvRes");
            Integer id_client = rs.getInt("id_client");
           Reservation r =new Reservation(IdRes, NomEvRes, TypeEvRes, id_client);
           ReservationList.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
return ReservationList;
    
}
   
    public void ajouterReservation(Reservation r) throws SQLException
    {

    PreparedStatement pre=con.prepareStatement("INSERT INTO `First`.`Reservation` (`IdRes`, `NomEvRes`, `TypeEvRes`, `MatriculeClient`) VALUES (?, ?, ?, ?);");
    pre.setString(1, r.getIdRes() );
    pre.setString(2, r.getNomEvRes());
    pre.setString(3, r.getTypeEvRes());
    pre.setInt(4, r.getId_client());
    pre.executeUpdate();
    }
    
    
     public void modifierReservation(Reservation r) {

        Statement stm;
        try {
            stm = con.createStatement();
            String query ="UPDATE Reservation SET IdRes ='"+r.getIdRes()+"',NomEvRes='"+r.getNomEvRes()+"',TypeEvRes='"+r.getTypeEvRes()+"',MatriculeClient='"+r.getId_client()+"' WHERE Id = '"+r.getIdRes()+"' ";
            stm.executeUpdate(query);
            System.out.println(query);
            } catch (Exception e) {
            e.printStackTrace(); 
            }
    }
    
        
    
    
     public void supprimerEvenement(Reservation r) {

        Statement stm;
        try {
            stm = con.createStatement();
            String query= "DELETE FROM Reservation WHERE IdRes = "+r.getIdRes() +"";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
