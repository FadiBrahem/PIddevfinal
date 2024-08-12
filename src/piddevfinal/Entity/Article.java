/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piddevfinal.Entity;

import java.sql.Date;

/**
 *
 * @author Fedi
 */
public class Article {

    private int idArticle;
    private String titreArticle;
    private String descArticle;
    private Date datecreatedArticle;
    private String catArticle;

    public Article(int idArticle, String titreArticle, String descArticle, Date datecreatedArticle, String catArticle) {
        this.idArticle = idArticle;
        this.titreArticle = titreArticle;
        this.descArticle = descArticle;
        this.datecreatedArticle = datecreatedArticle;
        this.catArticle = catArticle;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public String getDescArticle() {
        return descArticle;
    }

    public void setDescArticle(String descArticle) {
        this.descArticle = descArticle;
    }

    public Date getDatecreatedArticle() {
        return datecreatedArticle;
    }

    public void setDatecreatedArticle(Date datecreatedArticle) {
        this.datecreatedArticle = datecreatedArticle;
    }

    public String getCatArticle() {
        return catArticle;
    }

    public void setCatArticle(String catArticle) {
        this.catArticle = catArticle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idArticle;
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
        final Article other = (Article) obj;
        if (this.idArticle != other.idArticle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", titreArticle=" + titreArticle + ", descArticle=" + descArticle + ", datecreatedArticle=" + datecreatedArticle + ", catArticle=" + catArticle + '}';
    }

}
