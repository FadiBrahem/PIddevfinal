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
public class ExerciceCoursRelationRefined {
    
    private String nomCours;
    private String nomExercice;

    public ExerciceCoursRelationRefined() {
    }

    public ExerciceCoursRelationRefined(String nomCours, String nomExercice) {
        this.nomCours = nomCours;
        this.nomExercice = nomExercice;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public String getNomExercice() {
        return nomExercice;
    }

    public void setNomExercice(String nomExercice) {
        this.nomExercice = nomExercice;
    }
    
    
    
    
}
