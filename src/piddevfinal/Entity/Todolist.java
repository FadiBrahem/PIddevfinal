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
public class Todolist extends Client {

    Integer id_todo;
    String titretodo;
    String description;
    Date datecreated;

    public Todolist(Integer id_todo, String titretodo, String description, Date datecreated, int id_client, String username, String email, String password, String nom, String prenom, Date dateNai, String sexe, String tel, String adresse) {
        super(id_client, username, email, password, nom, prenom, dateNai, sexe, tel, adresse);
        this.id_todo = id_todo;
        this.titretodo = titretodo;
        this.description = description;
        this.datecreated = datecreated;
    }

    public Todolist(Integer id_todo, String titretodo, String description, Date datecreated, Integer id_client, String sexe) {
        super(id_client, sexe);
        this.id_todo = id_todo;
        this.titretodo = titretodo;
        this.description = description;
        this.datecreated = datecreated;
    }

    public Todolist(Integer id_todo, String titretodo, String description, Date datecreated) {
        this.id_todo = id_todo;
        this.titretodo = titretodo;
        this.description = description;
        this.datecreated = datecreated;
    }

    public Todolist(Integer id_todo, String titretodo, String description, Date datecreated, String username) {
        super(username);
        this.id_todo = id_todo;
        this.titretodo = titretodo;
        this.description = description;
        this.datecreated = datecreated;
    }

    public Todolist(int id_todo, String titretodo, String description, Date datecreated, int id_client) {
        this.id_todo = id_todo;
        this.titretodo = titretodo;
        this.description = description;
        this.datecreated = datecreated;
        this.id_client = id_client;
    }

    public Todolist() {
       
    }

    public Integer getId_todo() {
        return id_todo;
    }

    public void setId_todo(Integer id_todo) {
        this.id_todo = id_todo;
    }

    public String getTitretodo() {
        return titretodo;
    }

    public void setTitretodo(String titretodo) {
        this.titretodo = titretodo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNai() {
        return dateNai;
    }

    public void setDateNai(Date dateNai) {
        this.dateNai = dateNai;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id_todo);
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
        final Todolist other = (Todolist) obj;
        if (!Objects.equals(this.id_todo, other.id_todo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Todolist{" + "id_todo=" + id_todo + ", titretodo=" + titretodo + ", description=" + description + ", datecreated=" + datecreated + '}';
    }

  
   

}
