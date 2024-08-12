/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Services;

import piddevfinal.Entity.Article;
import piddevfinal.Bunch.ConnectionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leila
 */
public class ArticleServices {
      Connection con = ConnectionDB.getInstance().getCnx();
      
        public void ajouterClient(Article a) throws Exception {
        
        Statement stm;
        try {
            stm = con.createStatement();
            String query = "INSERT INTO `article` (`titreArticle`, `descArticle`, `datecreatedArticle`, `catArticle`) VALUES ('" + a.getTitreArticle() + "','" + a.getDescArticle() + "','" + a.getDatecreatedArticle() + "','" + a.getCatArticle() + "');";
            stm.executeUpdate(query);
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
