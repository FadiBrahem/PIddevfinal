/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Entity;

import java.util.Objects;

/**
 *
 * @author Fedi
 */
public class Categorie {
    private int idc;
    private String nom;

        public Categorie(int idc, String nom) {
            this.idc = idc;
            this.nom = nom;
        }
        public Categorie(int idc) {
        this.idc = idc;
        }

        public int getIdc() {
            return idc;
        }

        public String getNom() {
            return nom;
        }

        public void setIdc(int idc) {
            this.idc = idc;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 37 * hash + this.idc;
            hash = 37 * hash + Objects.hashCode(this.nom);
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
            final Categorie other = (Categorie) obj;
            if (this.idc != other.idc) {
                return false;
            }
            if (!Objects.equals(this.nom, other.nom)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Categorie{" + "idc=" + idc + ", nom=" + nom + '}';
        }
    
    
    }
    

