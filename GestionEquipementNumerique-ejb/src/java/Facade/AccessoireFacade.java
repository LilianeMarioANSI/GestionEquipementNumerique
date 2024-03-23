/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Accessoire;
import Entite.EtatAccessoire;
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
public class AccessoireFacade extends AbstractFacade<Accessoire> implements AccessoireFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccessoireFacade() {
        super(Accessoire.class);
    }

    
    /*
        Creation Accessoire pour créer offre
    */
    @Override
    public Accessoire CreerAccessoire(Accessoire a) {
        em.persist(a);
        return a;
    }
    
    /*
        Tableau de bord
    */
    @Override
    public List<Accessoire> getAccessoireByEtat(EtatAccessoire etat) {
        String txt = "SELECT a FROM Accessoire a WHERE a.etat = :etatAccessoire";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("etatAccessoire", etat);
        List<Accessoire> result = req.getResultList();
        return result;
    }
    
}
