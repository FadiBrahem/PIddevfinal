/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Services;

import piddevfinal.Entity.Admin;
import piddevfinal.Bunch.ConnectionDB;
import piddevfinal.Bunch.SecurityMeasures;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author leila
 */
public class AdminServices {

    Connection con = ConnectionDB.getInstance().getCnx();

    public boolean Authentification(Admin a) {
        SecurityMeasures ms = new SecurityMeasures();
        boolean status = false;
        try {
            String req = "SELECT * FROM admin  WHERE username = ? and password = ?";
            PreparedStatement st = con.prepareStatement(req);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                status = true;
               

            }
        } catch (Exception ex) {

        }
        return status;
    }

}
