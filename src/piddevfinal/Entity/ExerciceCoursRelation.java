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
public class ExerciceCoursRelation {
    
    private int idCours;
    private int idExercice;

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(int idExercice) {
        this.idExercice = idExercice;
    }

    public ExerciceCoursRelation(int idCours, int idExercice) {
        this.idCours = idCours;
        this.idExercice = idExercice;
    }
    
    public ExerciceCoursRelation(){
        
    }
    
    
    
}
