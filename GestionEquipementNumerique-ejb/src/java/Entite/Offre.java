/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entite;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
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
        Intitule
    */
    
    @Expose
    @Column(nullable = false)
    private String intitule;
    
    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    /*
        Date publication
    */
    @Expose
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePublication;
    
    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatedatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
    
    /*
        Type offre
    */
    
    @Expose
    @Column(nullable = false)
    private TypeOffre TypeOffre;

    public TypeOffre getTypeOffre() {
        return TypeOffre;
    }

    public void setTypeOffre(TypeOffre TypeOffre) {
        this.TypeOffre = TypeOffre;
    }
    
    /*
        Description
    */
    
    @Expose
    @Column(nullable = false)
    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    /*
        Date début
    */
    
    @Expose
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    /*
        Date fin
    */
    
    @Expose
    @Column(nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    /*
        Etat de l'offre
    */
    
    @Expose
    private EtatOffre etat;

    public EtatOffre getEtat() {
        return etat;
    }

    public void setEtat(EtatOffre etat) {
        this.etat = etat;
    }


    /*
        Relations
    
        Personne
    */
    
    @ManyToOne
    @Expose(serialize = false, deserialize = false)
    private Personne utilisateur;

    public Personne getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Personne utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    /*
        Accessoires
    */
    
    @ManyToOne
    @Expose(serialize = false, deserialize = false)
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
    @Expose(serialize = false, deserialize = false)
    private List<Demande> demandes;
    
    /*
        Methodes
    */
    
    //Retourne une représentation de l'objet au format Json
    public String toJson() {
        GsonBuilder builder = new GsonBuilder();  
        builder.excludeFieldsWithoutExposeAnnotation();  
        Gson gson = builder.create(); 
        return gson.toJson(this);
    }

}
