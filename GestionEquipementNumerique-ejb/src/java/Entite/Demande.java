/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import com.google.gson.annotations.Expose;
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
public class Demande implements Serializable {

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
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entite.Demande[ id=" + id + " ]";
    }
    
    /*
        Statut de la demande
    */
    @Column (nullable = false)
    private StatutDemande statut;

    public StatutDemande getStatut() {
        return statut;
    }

    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }
    
    /*
        Date demande
    */
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDemande;

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }
    
    /*
        Relation
    
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
    
    /*
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
