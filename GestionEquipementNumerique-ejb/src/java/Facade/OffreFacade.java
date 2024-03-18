/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.EtatOffre;
import Entite.Offre;
import Entite.TypeOffre;
import java.sql.Date;
import java.util.ArrayList;
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
    public List<String> getOffresParPeriode_Json(Date dateDebut, Date dateFin) {
        List<String> resultList = new ArrayList<>();
        String txt = "SELECT o FROM Offre o WHERE o.datePublication BETWEEN :dateDebut AND :dateFin ORDER BY o.datePublication ASC";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("dateDebut", dateDebut);
        req.setParameter("dateFin", dateFin);
        List<Offre> result = req.getResultList();
        for (Offre offre : result) {
            resultList.add(offre.toJson());
        }
        
        return resultList;
    }
    
    @Override
    public int getNombreOffrePublic() {
        List<String> resultList = new ArrayList<>();
        String txt = "SELECT o FROM Offre o WHERE o.etat = :disponible OR o.etat = :enCours";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("disponible", EtatOffre.DISPONIBLE);
        req.setParameter("enCours", EtatOffre.EN_COURS);
        List<Offre> result = req.getResultList();
        return result.size();
    }
    
    @Override
    public int getNombreOffrePublicByType(TypeOffre type) {
        List<String> resultList = new ArrayList<>();
        String txt = "SELECT o FROM Offre o WHERE o.TypeOffre = :typeOffre AND (o.etat = :disponible OR o.etat = :enCours)";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("disponible", EtatOffre.DISPONIBLE);
        req.setParameter("enCours", EtatOffre.EN_COURS);
        req.setParameter("typeOffre", type);
        List<Offre> result = req.getResultList();
        return result.size();
    }
    
    
    
}
