/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Demande;
import Entite.Membre;
import Entite.Offre;
import Entite.Personne;
import Entite.StatutDemande;
import Entite.TypeDemande;
import Entite.TypeOffre;
import java.util.List;
import java.sql.Date;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author loulo
 */
@Stateless
public class DemandeFacade extends AbstractFacade<Demande> implements DemandeFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeFacade() {
        super(Demande.class);
    }
    
    @Override
    public int getNombreMembreAvecDemandeByPeriode(Date dateDebut, Date dateFin) {
        String txt = "SELECT COUNT(DISTINCT d.utilisateur) FROM Demande d WHERE d.dateDemande BETWEEN :dateDebut AND :dateFin";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("dateDebut", dateDebut);
        req.setParameter("dateFin", dateFin);
        Long count = (Long) req.getSingleResult();
        return count.intValue();
    }
    
    @Override
    public List<Demande> listePrêts(Personne p){
        String txt = "SELECT d FROM Demande d WHERE d.utilisateur = :p AND d.typeDemande = :typeDemande";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", p);
        req.setParameter("typeDemande", TypeDemande.PRET);
        List<Demande> result = req.getResultList();
        return result;
    }

    @Override
    public List<Demande> listeDon(Personne p){
        String txt = "SELECT d FROM Demande d WHERE d.utilisateur = :p AND d.typeDemande = :typeDemande";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", p);
        req.setParameter("typeDemande", TypeDemande.DON);
        List<Demande> result = req.getResultList();
        return result;
    }

    @Override
    public Demande creerDemande(Personne personne, Offre offre) {
        Demande demande = new Demande();
        demande.setStatut(StatutDemande.EN_COURS);
        demande.setDateDemande(new Date(System.currentTimeMillis()));
        demande.setUtilisateur(personne);
        demande.setOffre(offre);
        em.persist(demande);
        return demande;
    }
    
    
    
}
