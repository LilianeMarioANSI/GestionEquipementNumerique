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
import javax.persistence.OneToMany;

/**
 *
 * @author loulo
 */
@Entity
public class CategorieAccessoire implements Serializable {

    

    

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
        if (!(object instanceof CategorieAccessoire)) {
            return false;
        }
        CategorieAccessoire other = (CategorieAccessoire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entite.CategorieAccessoire[ id=" + id + " ]";
    }
    
    /* 
        Intitulé
    */
    
    @Column (nullable = false)
    private String intitule;
    
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
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
        Relations

        Souhaits
    */
    
    @OneToMany(mappedBy = "categorieAccessoire")
    private List<Souhait> souhaits;

    public List<Souhait> getSouhaits() {
        return souhaits;
    }

    public void setSouhaits(List<Souhait> souhaits) {
        this.souhaits = souhaits;
    }
    
    /*
        Accessoires
    */
    
    @OneToMany(mappedBy = "categorieAccessoire")
    private List<Accessoire> accessoires;
    
}