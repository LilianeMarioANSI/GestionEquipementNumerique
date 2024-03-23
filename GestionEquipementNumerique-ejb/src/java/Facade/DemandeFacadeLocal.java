/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Demande;
import Entite.Membre;
import Entite.Offre;
import Entite.Personne;
import Entite.StatutDemande;
import Entite.TypeDemande;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface DemandeFacadeLocal {

    void create(Demande demande);

    void edit(Demande demande);

    void remove(Demande demande);

    Demande find(Object id);

    List<Demande> findAll();

    List<Demande> findRange(int[] range);

    int count(); 
    
    // nos méthodes
    
    List<Demande> listePrêts(Personne p);

    List<Demande> listeDon(Personne p); 
    
    int getNombreMembreAvecDemandeByPeriode(Date dateDebut, Date dateFin);

    Demande creerDemande(Personne personne, Offre offre);
    
    void supprimerDemande(Demande demande);
    
    Demande rechercherDemande(long idDemande);
}
