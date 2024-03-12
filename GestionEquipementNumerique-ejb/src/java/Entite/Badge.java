/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author loulo
 */
@Entity
public class Badge implements Serializable {

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
        if (!(object instanceof Badge)) {
            return false;
        }
        Badge other = (Badge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entite.Badge[ id=" + id + " ]";
    }
    
    /*
        Niveau
    */
    
    @Column (nullable = false)
    private NiveauBadge niveau; 

    public NiveauBadge getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauBadge niveau) {
        this.niveau = niveau;
    }
    
    @ManyToOne
    private Personne utilisateur;

    public Personne getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Personne utilisateur) {
        this.utilisateur = utilisateur;
    }

}
