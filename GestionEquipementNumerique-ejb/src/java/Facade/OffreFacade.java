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
import Entite.Agence;
import Entite.EtatOffre;
import Entite.Personne;
import Entite.TypeOffre;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


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
    public void updateEtatOffre(Offre o){
        if(o.getTypeOffre() == TypeOffre.DON){
            o.setEtat(EtatOffre.TERMINEE);
        }else if(o.getTypeOffre() == TypeOffre.PRET){
            o.setEtat(EtatOffre.EN_COURS);
        }
        
        em.merge(o);
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
        String txt = "SELECT o FROM Offre o WHERE o.etat = :disponible OR o.etat = :enCours";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("disponible", EtatOffre.DISPONIBLE);
        req.setParameter("enCours", EtatOffre.EN_COURS);
        List<Offre> result = req.getResultList();
        return result.size();
    }
    
    @Override
    public int getNombreOffrePublicByType(TypeOffre type) {
        String txt = "SELECT o FROM Offre o WHERE o.typeOffre = :typeOffre AND (o.etat = :disponible OR o.etat = :enCours)";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("disponible", EtatOffre.DISPONIBLE);
        req.setParameter("enCours", EtatOffre.EN_COURS);
        req.setParameter("typeOffre", type);
        List<Offre> result = req.getResultList();
        return result.size();
    }
    
    @Override
    public int getNombreMembreAvecOffreByPeriode(Date dateDebut, Date dateFin) {
        String txt = "SELECT COUNT(DISTINCT o.utilisateur) FROM Offre o WHERE o.datePublication BETWEEN :dateDebut AND :dateFin";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("dateDebut", dateDebut);
        req.setParameter("dateFin", dateFin);
        Long count = (Long) req.getSingleResult();
        return count.intValue();
    }
    
    @Override
    public List<String> getTop5AgenceByOffre(Date dateDebut, Date dateFin) {
    List<String> resultList = new ArrayList<>();

    String txt = "SELECT o.utilisateur.agence, COUNT(o) FROM Offre o WHERE o.datePublication BETWEEN :dateDebut AND :dateFin GROUP BY o.utilisateur.agence ORDER BY COUNT(o) DESC";
    Query req = getEntityManager().createQuery(txt);
    req.setParameter("dateDebut", dateDebut);
    req.setParameter("dateFin", dateFin);
    
    List<Object[]> result = req.setMaxResults(5).getResultList();
    
    for (Object[] row : result) {
        // Construction de l'objet JSON représentant le résultat
        JsonObject jsonObject = new JsonObject();
        System.out.println(row[0].toString());
        jsonObject.addProperty("agence", row[0].toString()); // Nom de l'agence
        jsonObject.addProperty("nombre_offres", (Long) row[1]); // Nombre d'offres publiées

        // Conversion de l'objet JSON en chaîne JSON et ajout à la liste des résultats
        resultList.add(jsonObject.toString());
    }

    return resultList;
}
        @Override
    public List<Offre> listeOffre(Personne p){
        String txt = "SELECT o FROM Offre o WHERE o.utilisateur = :p";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", p);
        List<Offre> result = req.getResultList();
        return result;
    }
}
