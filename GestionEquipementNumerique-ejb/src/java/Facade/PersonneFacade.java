/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

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
public class PersonneFacade extends AbstractFacade<Personne> implements PersonneFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneFacade() {
        super(Personne.class);
    }
    
    @Override
    public Personne rechercherPersonne(long id) {
        Query req = getEntityManager().createQuery("Select p from Personne as p where p.id=:idPersonne");
        req.setParameter("idPersonne", id);
        Personne result = (Personne) req.getResultList().get(0);
        return result;
    }
}
