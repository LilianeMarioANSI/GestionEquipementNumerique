/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Souhait;
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
public class SouhaitFacade extends AbstractFacade<Souhait> implements SouhaitFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SouhaitFacade() {
        super(Souhait.class);
    }
    
    @Override
    public List<Souhait> getSouhaitByMembre(long idMembre) {
        String txt = "SELECT s FROM Souhait s WHERE s.utilisateur.id = :id";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("id", idMembre);
        List<Souhait> result = req.getResultList();
        
        return result;
    }
    
}
