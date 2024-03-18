/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Offre;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public Offre creerOffre (String libelle, String description, Date datePublication, TypeOffre typeOffre, Date dateDebut, Date dateFin, Accessoire accesoires, Personne user, EtatOffre etatOffre) {
        Offre o = new Offre();
        o.setIntitule(libelle);
        o.setDescription(description);
        o.setDatePublication(datePublication);
        o.setTypeOffre(typeOffre);
        o.setDateDebut(dateDebut);
        o.setDateFin(dateFin);
        o.setAccessoire(accesoires);
        o.setUtilisateur(user);
        o.setEtat(etatOffre.DISPONIBLE);
        em.persist(o);
        user.getOffres().add(o);
        return o;
    }
    
}
