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

    /**
    * Cette méthode permet de créer un nouvel accessoire dans la base de données.
    * Elle est utilisée dans le servlet pour créer une offre.
    *
    * @param a L'accessoire à créer.
    * @return L'accessoire créé.
    */
    @Override
    public Accessoire CreerAccessoire(Accessoire a) {
        em.persist(a);
        return a;
    }
    
    /**
    * Cette méthode permet d'obtenir une liste d'accessoires en fonction de leur état.
    * Elle est utilisée pour afficher les accessoires dans le tableau de bord.
    *
    * @param etat L'état des accessoires à récupérer.
    * @return Une liste d'accessoires correspondant à l'état spécifié.
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
