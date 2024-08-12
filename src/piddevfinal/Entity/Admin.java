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
public class Admin extends User{
    
    private Integer id;
   
    public Admin(){
        
    }

    public Admin(String username, String username_canonical, String email, String email_canonical, String password, String nom, String prenom, Date dateNai, String adresse, String tel, String salt, int enable) {
        super(username, username_canonical, email, email_canonical, password, nom, prenom, dateNai, adresse, tel, salt, enable);
    }

    public Admin(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Admin other = (Admin) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    

   
    

}
