/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Bunch;

/**
 *
 * @author Fedi
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leila
 */

public class ConnectionDB {

    private String url = "jdbc:mysql://localhost/formation";
    private String login = "root";
    private String mdp = "";
    private Connection cnx;
    private static ConnectionDB instance;

    public Connection getCnx() {
        return cnx;
    }



    public ConnectionDB() {//une seule connexion dans une application et limité nombre de cnx

        try {
            cnx = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
           Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }

        return instance;
    }

}
