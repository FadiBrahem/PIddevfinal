/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Entity;

/**
 *
 * @author Fedi
 */
public class Cours {
    
    private int idCours;
    private String nomCours;
    private String descCours;
    private int nbExercices;
    private int id_Mentor;


    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public String getDescCours() {
        return descCours;
    }

    public void setDescCours(String descCours) {
        this.descCours = descCours;
    }

    public int getNbExercices() {
        return nbExercices;
    }

    public void setNbExercices(int nbExercices) {
        this.nbExercices = nbExercices;
    }

    public int getId_Mentor() {
        return id_Mentor;
    }

    public void setId_Mentor(int id_Mentor) {
        this.id_Mentor = id_Mentor;
    }

    public Cours(/*long idCours,*/ String nomCours, String descCours, int nbExercices, int id_Mentor) {
        //this.idCours = idCours;
        this.nomCours = nomCours;
        this.descCours = descCours;
        this.nbExercices = nbExercices;
        this.id_Mentor = id_Mentor;
    }
    

    public Cours() {
        
    }    
    
    
}
