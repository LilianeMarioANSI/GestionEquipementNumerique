/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Badge;
import Entite.NiveauBadge;
import Entite.Personne;
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

    /**
     * Crée un nouveau badge pour un utilisateur donné avec le niveau spécifié.
     *
     * @param niveau      Le niveau du badge à créer.
     * @param utilisateur L'utilisateur associé au badge.
     * @return Le badge créé.
     */
    @Override
    public Badge creerBadge(NiveauBadge niveau, Personne utilisateur) {
        Badge b = new Badge();
        b.setNiveau(niveau);
        b.setUtilisateur(utilisateur);
        em.persist(b);
        return b;
    }
    
    /**
     * Vérifie si un badge existe pour un utilisateur donné avec le niveau spécifié.
     *
     * @param utilisateur L'utilisateur pour lequel vérifier l'existence du badge.
     * @param niveau      Le niveau du badge à vérifier.
     * @return true si le badge existe pour l'utilisateur donné avec le niveau spécifié, sinon false.
     */
    @Override
    public boolean verificationBadgeExistant(Personne utilisateur, NiveauBadge niveau){
        
        String txt = "SELECT b FROM Badge b WHERE b.utilisateur.id= :idUtilisateur AND b.niveau = :niveauBadge";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("idUtilisateur", utilisateur.getId());
        req.setParameter("niveauBadge", niveau);
        List<Badge> result = req.getResultList();
        return result.isEmpty();
    }
    
    /**
     * Récupère la liste des badges d'un utilisateur pour affichage dans son profil.
     *
     * @param utilisateur L'utilisateur pour lequel récupérer les badges.
     * @return La liste des badges de l'utilisateur.
     */
    @Override
    public List<Badge> getBadgeByMembre(Personne utilisateur){
        String txt = "SELECT b FROM Badge b WHERE b.utilisateur.id= :idUtilisateur";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("idUtilisateur", utilisateur.getId());
        List<Badge> result = req.getResultList();
        return result;
    }
}
