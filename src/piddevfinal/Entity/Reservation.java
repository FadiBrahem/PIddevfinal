/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Entity;

import java.util.logging.Logger;

/**
 *
 * @author Fedi
 */
public class Reservation {
    String IdRes;
    String NomEvRes;
    String TypeEvRes;
    Integer id_client;

    public Reservation(String IdRes, String NomEvRes, String TypeEvRes, Integer id_client) {
        this.IdRes = IdRes;
        this.NomEvRes = NomEvRes;
        this.TypeEvRes = TypeEvRes;
        this.id_client = id_client;
    }

    public String getIdRes() {
        return IdRes;
    }

    public void setIdRes(String IdRes) {
        this.IdRes = IdRes;
    }

    public String getNomEvRes() {
        return NomEvRes;
    }

    public void setNomEvRes(String NomEvRes) {
        this.NomEvRes = NomEvRes;
    }

    public String getTypeEvRes() {
        return TypeEvRes;
    }

    public void setTypeEvRes(String TypeEvRes) {
        this.TypeEvRes = TypeEvRes;
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

   
    
}
