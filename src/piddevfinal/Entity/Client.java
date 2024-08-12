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
public class Client extends User {

    Integer id_client;
    String sexe;

    public Client(int id_client, String username, String email, String password, String nom, String prenom, Date dateNai, String sexe, String tel, String adresse) {
        this.id_client = id_client;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNai = dateNai;
        this.sexe = sexe;
        this.tel = tel;
        this.adresse = adresse;

    }

    public Client(Integer id_client, String sexe) {
        this.id_client = id_client;
        this.sexe = sexe;
    }

    public Client() {

    }

    public Client(String username) {
       this.username = username;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getSexe() {
        return sexe;
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
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
        hash = 97 * hash + Objects.hashCode(this.id_client);
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.id_client, other.id_client)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "id_client=" + id_client + ", username=" + username + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", dateNai=" + dateNai + ", adresse=" + adresse + ", tel=" + tel + ", sexe =" + sexe + "}\n";
    }

   

}
