/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Entity;


import java.time.LocalDate;
import java.util.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author Fedi
 */


public class Evenement {
    String Id;
    String Nom;
    String Type;
    String Organisateur;
    Date Date_Debut;
    Date Date_Fin;
    String capacite;
    String img;
    ImageView image1;

    public Evenement() {
    }

    public Evenement(String Nom, String Type, String Organisateur, Date Date_Debut, Date Date_Fin, String capacite) {
        this.Nom = Nom;
        this.Type = Type;
        this.Organisateur = Organisateur;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.capacite = capacite;
    }

   

   
    
public ImageView getImage1() {
        return image1;
    }

    public void setImage1(ImageView image1) {
        this.image1 = image1;
    }

    public Evenement(String Id, String Nom, String Type, String Organisateur, Date Date_Debut, Date Date_Fin, String capacite, String img, ImageView image1) {
        this.Id = Id;
        this.Nom = Nom;
        this.Type = Type;
        this.Organisateur = Organisateur;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.capacite = capacite;
        this.img = img;
        this.image1 = image1;
    }

    public Evenement(String Nom, String Type, String Organisateur, Date Date_Debut, Date Date_Fin, String capacite, String img) {
        this.Nom = Nom;
        this.Type = Type;
        this.Organisateur = Organisateur;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.capacite = capacite;
        this.img = img;
    }

    public Evenement(String Id, String Nom, String Type, String Organisateur, Date Date_Debut, Date Date_Fin, String capacite, String img) {
        this.Id = Id;
        this.Nom = Nom;
        this.Type = Type;
        this.Organisateur = Organisateur;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.capacite = capacite;
        this.img = img;
    }

    public Evenement(String Id, String Nom, String Type, String Organisateur, Date Date_Debut, Date Date_Fin, String capacite) {
        this.Id = Id;
        this.Nom = Nom;
        this.Type = Type;
        this.Organisateur = Organisateur;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
        this.capacite = capacite;
    }

    public Evenement(String Id, String Nom, String Type, String Organisateur, Date Date_Debut, Date Date_Fin) {
        this.Id = Id;
        this.Nom = Nom;
        this.Type = Type;
        this.Organisateur = Organisateur;
        this.Date_Debut = Date_Debut;
        this.Date_Fin = Date_Fin;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getOrganisateur() {
        return Organisateur;
    }

    public void setOrganisateur(String Organisateur) {
        this.Organisateur = Organisateur;
    }

    public Date getDate_Debut() {
        return Date_Debut;
    }

    public void setDate_Debut(Date Date_Debut) {
        this.Date_Debut = Date_Debut;
    }

    public Date getDate_Fin() {
        return Date_Fin;
    }

    public void setDate_Fin(Date Date_Fin) {
        this.Date_Fin = Date_Fin;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Evenement{" + "Id=" + Id + ", Nom=" + Nom + ", Type=" + Type + ", Organisateur=" + Organisateur + ", Date_Debut=" + Date_Debut + ", Date_Fin=" + Date_Fin + ", capacite=" + capacite + ", img=" + img + ", image1=" + image1 + '}';
    }
    
}