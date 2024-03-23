/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    /*
        DatePublication
    */
    @Column (nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePublication;
    
    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
    
    /*
        Date de début du besoin
    */
    
    @Column (nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
    
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    /*
        Date de fin du besoin
    */
    
    @Column (nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    
    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    /*
        Type souhait
    */
    
    @Column (nullable = false)
    private TypeSouhait typeSouhait;

    public TypeSouhait getTypeSouhait() {
        return typeSouhait;
    }

    public void setTypeSouhait(TypeSouhait typeSouhait) {
        this.typeSouhait = typeSouhait;
    }
    
    /*
        Type accessoire
    */
    
    @Column (nullable = false)
    private TypeAccessoire TypeAccessoire;

    public TypeAccessoire getTypeAccessoire() {
        return TypeAccessoire;
    }

    public void setTypeAccessoire(TypeAccessoire TypeAccessoire) {
        this.TypeAccessoire = TypeAccessoire;
    }
    
    @Column(nullable = false)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    
    /*
        Relations
    
        Personne
    */
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "UTILISATEUR_ID")
    private Personne utilisateur;
    
    public Personne getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Personne utilisateur) {
        this.utilisateur = utilisateur;
    }

}
