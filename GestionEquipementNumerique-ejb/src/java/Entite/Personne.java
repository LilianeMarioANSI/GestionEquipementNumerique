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
import javax.persistence.OneToMany;

/**
 *
 * @author loulo
 */
@Entity
public class Personne implements Serializable {

    

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
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entite.Personne[ id=" + id + " ]";
    }
    
    /*
        login
    */
    
    @Column(nullable = false)
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    /*
        mdp
    */
    
    @Column(nullable = false)
    private String mdp;

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /*
        Nom
    */
    
    @Column (nullable = false)
    private String nom;
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    /*
        Prenom
    */
    
    @Column (nullable = false)
    private String prenom;
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    /*
        Bureau
    */
    
    @Column (nullable = false)
    private String bureau;

    public String getBureau() {
        return bureau;
    }

    public void setBureau(String bureau) {
        this.bureau = bureau;
    }
    
    /*
        Telephone
    */
    
    @Column (nullable = false)
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    /*
        Site géographique
    */
    
    @Column (nullable = false)
    private Agence agence;

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    
    /*
        Relations
    
        Badges
    */

    @OneToMany(mappedBy = "utilisateur")
    private List<Badge> badges;

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
    
    
    /*
        Souhaits
    */
    @OneToMany(mappedBy = "utilisateur")
    private List<Souhait> souhaits;

    public List<Souhait> getSouhaits() {
        return souhaits;
    }

    public void setSouhaits(List<Souhait> souhaits) {
        this.souhaits = souhaits;
    }
    
    /*
        Offres
    */
    
    @OneToMany(mappedBy = "utilisateur")
    private List<Offre> offres;

    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
    }
    
    /*
        Demandes
    */
    
    @OneToMany(mappedBy = "utilisateur")
    private List<Demande> demandes;

    public List<Demande> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<Demande> demandes) {
        this.demandes = demandes;
    }
    
    /*
        Personnes
    */
    
    @ManyToMany(mappedBy = "Personnes")
    private List<Accessoire> accessoires;

    public List<Accessoire> getAccessoires() {
        return accessoires;
    }

    public void setAccessoires(List<Accessoire> accessoires) {
        this.accessoires = accessoires;
    }
    
}
