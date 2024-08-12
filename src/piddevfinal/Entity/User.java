/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Entity;

import java.util.Date;

/**
 *
 * @author Fedi
 */
public class User {
    
    
    String username;
    String email;
    String password;
    String nom ;
    String prenom ;
    Date dateNai ;
    String adresse ;
    String tel ;
    
    public User( String username, String username_canonical, String email, String email_canonical, String password, String nom, String prenom, Date dateNai, String adresse, String tel, String salt,  int enable  ) {
        
      
        this.username = username;
        this.email=email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNai = dateNai; 
        this.adresse = adresse;
        this.tel = tel;
    }

    public User() {
    }
    


    public void setUsername(String username) {
        this.username = username;
    }

    

    public void setEmail(String email) {
        this.email = email;
    }

   

    public void setPassword(String password) {
        this.password = password;
    }

    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNai(Date dateNai) {
        this.dateNai = dateNai;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getUsername() {
        return username;
    }

    

    public String getEmail() {
        return email;
    }

    

    public String getPassword() {
        return password;
    }

    

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNai() {
        return dateNai;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }



    

  
}
