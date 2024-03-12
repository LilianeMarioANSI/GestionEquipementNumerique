/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Offre;
import java.sql.Date;
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
public class OffreFacade extends AbstractFacade<Offre> implements OffreFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OffreFacade() {
        super(Offre.class);
    }
    
    @Override
    public List<Offre> getOffresParPeriode(Date dateDebut, Date dateFin) {
    List<Offre> result;
    String txt = "SELECT offre FROM Offre offre WHERE offre.datePublication BETWEEN :dateDebut AND :dateFin";
    Query req = getEntityManager().createQuery(txt);
    req.setParameter("dateDebut", dateDebut);
    req.setParameter("dateFin", dateFin);
    result = req.getResultList();
    return result;
}
    
}
