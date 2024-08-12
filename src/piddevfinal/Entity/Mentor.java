/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Fedi
 */
public class Mentor extends User {

    Integer id_Mentor;
    String sexe;
    String Role;
    Integer nbrExercices;
    Integer nbrCours;

  
   

    public Mentor(int id_Mentor, String username, String email, String password, String nom, String prenom, Date dateNai, String tel, String adresse, String sexe, String Role, int nbrExerices, int nbrCours) {
        this.id_Mentor = id_Mentor;
        this.username = username; 
        this.email = email; 
        this.password = password; 
        this.nom = nom; 
        this.prenom = prenom; 
        this.dateNai = dateNai;
        this.tel = tel; 
        this.adresse = adresse;
        this.sexe = sexe;
        this.Role = Role;
        this.nbrCours = nbrCours;
        this.nbrExercices = nbrExerices;
        
    }

    public Mentor(Integer id_Mentor, String sexe, String Role, Integer nbrExercices, Integer nbrCours) {
        this.id_Mentor = id_Mentor;
        this.sexe = sexe;
        this.Role = Role;
        this.nbrExercices = nbrExercices;
        this.nbrCours = nbrCours;
    }
    
     public Mentor(Integer id_Mentor, String sexe, String Role) {
        this.id_Mentor = id_Mentor;
        this.sexe = sexe;
        this.Role = Role;
    }

    public Mentor() {
        
    }

    public Mentor(int aInt, String string, String string0, String string1, String string2, String string3, java.sql.Date date, String string4, String string5, String string6, String string7) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getNbrExercices() {
        return nbrExercices;
    }

    public void setNbrExercices(Integer nbrExercices) {
        this.nbrExercices = nbrExercices;
    }

    public Integer getNbrCours() {
        return nbrCours;
    }

    public void setNbrCours(Integer nbrCours) {
        this.nbrCours = nbrCours;
    }

    public Integer getId_Mentor() {
        return id_Mentor;
    }

    public void setId_Mentor(Integer id_Mentor) {
        this.id_Mentor = id_Mentor;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    @Override
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public Date getDateNai() {
        return dateNai;
    }

    @Override
    public void setDateNai(Date dateNai) {
        this.dateNai = dateNai;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    @Override
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String getTel() {
        return tel;
    }

    @Override
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id_Mentor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mentor other = (Mentor) obj;
        if (!Objects.equals(this.id_Mentor, other.id_Mentor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mentor{" + "id_Mentor=" + id_Mentor + ", username =" + username + ", email= "+ email + ", password="+ password +", nom=" + nom + ", prenom=" + prenom + ", dateNai="+ dateNai + ", adresse=" + adresse + ", tel=" + tel + ", sexe=" + sexe + ", Role=" + Role + ", nbrExercices=" + nbrExercices + ", nbrCours=" + nbrCours + '}';
    }

    
    

}
