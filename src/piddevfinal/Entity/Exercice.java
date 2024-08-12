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
public class Exercice {

    private int idExercice;
    private String nomExercice;
    private String descExercice;
    private String niveauExercice;
    private String stateExercice;
    private int idMentor;
    private String pathImageExercice;

    public Exercice(int aInt, String string, String string0, String string1, String string2) {
       
    }

    public Exercice(int idExercice, String nomExercice, String descExercice, String niveauExercice, String stateExercice, int id_mentor) {
       this.idExercice = idExercice;
       this.nomExercice = nomExercice;
       this.descExercice = descExercice;
       this.niveauExercice = niveauExercice;
       this.stateExercice = stateExercice;
       this.idMentor = idMentor;
               
    }

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }

    public String getDescExercice() {
        return descExercice;
    }

    public void setDescExercice(String descExercice) {
        this.descExercice = descExercice;
    }

    public String getNiveauExercice() {
        return niveauExercice;
    }

    public void setNiveauExercice(String niveauExercice) {
        this.niveauExercice = niveauExercice;
    }

    public String getStateExercice() {
        return stateExercice;
    }

    public void setStateExercice(String stateExercice) {
        this.stateExercice = stateExercice;
    }

    public int getIdMentor() {
        return idMentor;
    }

    public void setIdMentor(int idMentor) {
        this.idMentor = idMentor;
    }

    public String getPathImageExercice() {
        return pathImageExercice;
    }

    public void setPathImageExercice(String pathImageExercice) {
        this.pathImageExercice = pathImageExercice;
    }
    
    

    public Exercice(String nomExercice, String descExercice, String niveauExercice, String stateExercice, int idMentor) {
        //this.idExercice = idExercice;
        this.nomExercice = nomExercice;
        this.descExercice = descExercice;
        this.niveauExercice = niveauExercice;
        this.stateExercice = stateExercice;
        this.idMentor = idMentor;
    }

    public Exercice() {
    }

    public Exercice(int idExercice, String nomExercice, int idMentor, String stateExercice, String niveauExercice ) {
        this.idExercice = idExercice;
        this.nomExercice = nomExercice;
        this.idMentor = idMentor;
        //this.descExercice = descExercice;
        this.niveauExercice = niveauExercice;
        this.stateExercice = stateExercice;

    }
    
    public Exercice(int idExercice, String nomExercice, int idMentor, String stateExercice, String niveauExercice, String descExercice ) {
        this.idExercice = idExercice;
        this.nomExercice = nomExercice;
        this.idMentor = idMentor;
        this.descExercice = descExercice;
        this.niveauExercice = niveauExercice;
        this.stateExercice = stateExercice;

    }

    @Override
    public String toString() {
        return "Exercice{" + "idExercice=" + idExercice + ", nomExercice=" + nomExercice + ", descExercice=" + descExercice + ", niveauExercice=" + niveauExercice + ", stateExercice=" + stateExercice + ", id_Mentor=" + idMentor + ", path= "+ pathImageExercice + '}';
    }
    
    

}
