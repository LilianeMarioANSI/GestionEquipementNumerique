/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Badge;
import Entite.Membre;
import Entite.NiveauBadge;
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
public class BadgeFacade extends AbstractFacade<Badge> implements BadgeFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BadgeFacade() {
        super(Badge.class);
    }

    @Override
    public Badge creerBadge(NiveauBadge niveau, Membre utilisateur) {
        Badge b = new Badge();
        b.setNiveau(niveau);
        b.setUtilisateur(utilisateur);
        em.persist(b);
        return b;
    }
    
    @Override
    public boolean verificationBadgeExistant(Membre utilisateur, NiveauBadge niveau){
        
        String txt = "SELECT b FROM Badge b WHERE b.getUtilisateur.id= :idUtilisateur AND b.niveau = :niveauBadge";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("idUtilisateur", utilisateur.getId());
        req.setParameter("niveauBadge", niveau);
        List<Badge> result = req.getResultList();
        return result.isEmpty();
    }
    
    @Override
    public List<Badge> getBadgeByMembre(Membre utilisateur){
        String txt = "SELECT b FROM Badge b WHERE b.utilisateur.id= :idUtilisateur";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("idUtilisateur", utilisateur.getId());
        List<Badge> result = req.getResultList();
        return result;
    }
    
    
}
