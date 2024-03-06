/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author loulo
 */
@Entity
public class Souhait implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Souhait)) {
            return false;
        }
        Souhait other = (Souhait) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entite.Souhait[ id=" + id + " ]";
    }
    
    
    @Column (nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePublication;
    
    /*
        Relations
    
        Souhait
    */
    
    @ManyToOne
    private Utilisateur utilisateur;
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    /*
        Categorie d'accessoire
    */
    
    @ManyToOne
    private CategorieAccessoire categorieAccessoire;

    public CategorieAccessoire getCategorieAccessoire() {
        return categorieAccessoire;
    }
    
    public void setCategorieAccessoire(CategorieAccessoire categorieAccessoire) {
        this.categorieAccessoire = categorieAccessoire;
    }

}
