/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Demande;
import Entite.Offre;
import Entite.Personne;
import Entite.TypeDemande;
import Entite.TypeOffre;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
