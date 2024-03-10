/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author loulo
 */
@Entity
public class Offre implements Serializable {

    

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
        if (!(object instanceof Offre)) {
            return false;
        }
        Offre other = (Offre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entite.Offre[ id=" + id + " ]";
    }
    
    /*
        intitule
    */
    
    @Column(nullable = false)
    private String intitule;
    
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    /*
        Categorie accessoire
    */
    @Column(nullable = false)
    private CategorieAccessoire categorie;
    
    public CategorieAccessoire getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieAccessoire categorie) {
        this.categorie = categorie;
    }
    
    /*
        Date publication
    */
    @Column(unique = true, nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePublication;
    
    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatedatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
    
    /*
        Relations
    
        Utilisateur
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
        Accessoires
    */
    
    @ManyToOne
    private Accessoire accessoire;

    public Accessoire getAccessoire() {
        return accessoire;
    }

    public void setAccessoire(Accessoire accessoire) {
        this.accessoire = accessoire;
    }
    
    /*
        Demande
    */
    
    @OneToMany(mappedBy = "offre")
    private List<Demande> demandes;

}