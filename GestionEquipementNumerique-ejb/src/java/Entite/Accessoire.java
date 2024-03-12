/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author loulo
 */
@Entity
public class Accessoire implements Serializable {

    

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
        if (!(object instanceof Accessoire)) {
            return false;
        }
        Accessoire other = (Accessoire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entite.Accessoi&re[ id=" + id + " ]";
    }
    
    /* 
        Désignation
    */
    
    @Column (nullable = false)
    private String designation;
    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    /* 
        Description
    */
    
    @Column (nullable = false)
    private String description;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    /* 
        Etat accesoire
    */
    
    @Column (nullable = false)
    private EtatAccessoire etat;
    
     public EtatAccessoire getEtat() {
        return etat;
    }

    public void setEtat(EtatAccessoire etat) {
        this.etat = etat;
    }
    
    /* 
        Disponibilité
    */
    
    @Column (nullable = false)
    private boolean disponibilite;  

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    /*
        Type Accessoire
    */
    
    @Column (nullable = false)
    private TypeAccessoire TypeAccessoire;

    public TypeAccessoire getTypeAccessoire() {
        return TypeAccessoire;
    }

    public void setTypeAccessoire(TypeAccessoire TypeAccessoire) {
        this.TypeAccessoire = TypeAccessoire;
    }

    
    /*
        Relations
    
        Personne
    */
    
    @ManyToMany
    private List<Personne> utilisateurs;

    public List<Personne> getPersonnes() {
        return utilisateurs;
    }

    public void setPersonnes(List<Personne> Personnes) {
        this.utilisateurs = Personnes;
    }

    
    /*
        Offre
    */
    
    @ManyToOne
    private Offre offre;

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    
    
}
