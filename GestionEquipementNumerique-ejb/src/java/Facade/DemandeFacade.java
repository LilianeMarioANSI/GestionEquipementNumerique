/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Demande;
import java.sql.Date;
import Entite.Personne;
import Entite.TypeDemande;
import java.util.List;
import javax.ejb.Stateless;
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
    
    /*
        Tableau de bord
    */
    @Override
    public int getNombreMembreAvecDemandeByPeriode(Date dateDebut, Date dateFin) {
        String txt = "SELECT COUNT(DISTINCT d.utilisateur) FROM Demande d WHERE d.dateDemande BETWEEN :dateDebut AND :dateFin";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("dateDebut", dateDebut);
        req.setParameter("dateFin", dateFin);
        Long count = (Long) req.getSingleResult();
        return count.intValue();
    }
    
    /*
        Mes Prêts
    */
    @Override
    public List<Demande> listePrêts(Personne p){
        String txt = "SELECT d FROM Demande d WHERE d.utilisateur = :p AND d.typeDemande = :typeDemande";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", p);
        req.setParameter("typeDemande", TypeDemande.PRET);
        List<Demande> result = req.getResultList();
        return result;
    }

    /*
        Mes Dons
    */
    @Override
    public List<Demande> listeDon(Personne p){
        String txt = "SELECT d FROM Demande d WHERE d.utilisateur = :p AND d.typeDemande = :typeDemande";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", p);
        req.setParameter("typeDemande", TypeDemande.DON);
        List<Demande> result = req.getResultList();
        return result;
    }
    
    /*
        Supprimer Demande
    */
    @Override
    public void supprimerDemande(Demande demande) {
        em.remove(demande);
    }
        
    @Override
    public Demande rechercherDemande(long idDemande){
        Query req = getEntityManager().createQuery("Select d from Demande as d where d.id=:idDemande");
        req.setParameter("idDemande", idDemande);
        Demande result = (Demande) req.getResultList().get(0);
        return result;
    }
}
