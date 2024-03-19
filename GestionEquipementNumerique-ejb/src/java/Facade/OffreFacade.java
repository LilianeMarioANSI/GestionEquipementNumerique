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
import javax.persistence.TypedQuery;
import Entite.Accessoire;
import Entite.EtatOffre;
import Entite.Personne;
import Entite.TypeOffre;


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
    public Offre creerOffre (Offre o) {
        em.persist(o);
        return o;
    }
    
    @Override
    public List<Offre> catalogueOffres() {
        TypedQuery<Offre> query = getEntityManager().createQuery(
                "SELECT o FROM Offre o", Offre.class);
        return query.getResultList();
    }
    
    @Override
    public List<Offre> catalogueOffresFiltre(String type, String etat, String categorie) {
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Offre o WHERE 1=1");

        if (type != null && !type.isEmpty()) {
            queryBuilder.append(" AND o.TypeOffre = :type");
        }
        if (etat != null && !etat.isEmpty()) {
            queryBuilder.append(" AND o.etat = :etat");
        }
        if (categorie != null && !categorie.isEmpty()) {
            queryBuilder.append(" AND o.accesoire.TypeAccessoire = :categorie");
        }

        TypedQuery<Offre> query = getEntityManager().createQuery(queryBuilder.toString(), Offre.class);

        if (type != null && !type.isEmpty()) {
            query.setParameter("type", type);
        }
        if (etat != null && !etat.isEmpty()) {
            query.setParameter("etat", etat);
        }
        if (categorie != null && !categorie.isEmpty()) {
            query.setParameter("categorie", categorie);
        }

        return query.getResultList();
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
