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
import javax.persistence.TypedQuery;
import Entite.Agence;
import Entite.EtatOffre;
import Entite.Personne;
import Entite.Membre;
import Entite.TypeOffre;
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
    
    /**
     * Obtient une liste d'offres publiées pendant une période donnée, formatée en JSON.
     *
     * @param dateDebut La date de début de la période.
     * @param dateFin   La date de fin de la période.
     * @return La liste d'offres publiées pendant la période spécifiée, formatée en JSON.
     */
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
    
    /**
    * Obtient le nombre total d'offres publiques.
    *
    * @return Le nombre total d'offres publiques.
    */
    @Override
    public int getNombreOffrePublic() {
        String txt = "SELECT o FROM Offre o WHERE o.etat = :disponible OR o.etat = :enCours";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("disponible", EtatOffre.DISPONIBLE);
        req.setParameter("enCours", EtatOffre.EN_COURS);
        List<Offre> result = req.getResultList();
        return result.size();
    }
    
    /**
    * Obtient le nombre total d'offres publiques d'un type spécifique.
    *
    * @param type Le type d'offre.
    * @return Le nombre total d'offres publiques du type spécifié.
    */
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
    
    /**
    * Obtient le nombre de membres ayant publié des offres pendant une période donnée.
    *
    * @param dateDebut La date de début de la période.
    * @param dateFin   La date de fin de la période.
    * @return Le nombre de membres ayant publié des offres pendant la période spécifiée.
    */
    @Override
    public int getNombreMembreAvecOffreByPeriode(Date dateDebut, Date dateFin) {
        String txt = "SELECT COUNT(DISTINCT o.utilisateur) FROM Offre o WHERE o.datePublication BETWEEN :dateDebut AND :dateFin";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("dateDebut", dateDebut);
        req.setParameter("dateFin", dateFin);
        Long count = (Long) req.getSingleResult();
        return count.intValue();
    }

    /**
    * Obtient les cinq meilleures agences ayant publié le plus d'offres pendant une période donnée.
    *
    * @param dateDebut La date de début de la période.
    * @param dateFin   La date de fin de la période.
    * @return La liste des cinq meilleures agences ayant publié le plus d'offres pendant la période spécifiée.
    */
    @Override
    public List<String> getTop5AgenceByOffre(Date dateDebut, Date dateFin) {
        List<String> resultList = new ArrayList<>();

        String txt = "SELECT o.utilisateur.agence, COUNT(o) FROM Offre o WHERE o.datePublication BETWEEN :dateDebut AND :dateFin GROUP BY o.utilisateur.agence ORDER BY COUNT(o) DESC";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("dateDebut", dateDebut);
        req.setParameter("dateFin", dateFin);

        List<Object[]> result = req.setMaxResults(5).getResultList();

        for (Object[] row : result) {
            Agence agence = (Agence) row[0];

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("agence", agence.label);
            jsonObject.addProperty("nombre_offres", (Long) row[1]);

            resultList.add(jsonObject.toString());
        }
        return resultList;
    }

    /**
     * Crée une nouvelle offre.
     *
     * @param o L'offre à créer.
     * @return L'offre créée.
     */
    @Override
    public Offre creerOffre (Offre o) {
        em.persist(o);
        return o;
    }
    
    /**
     * Met à jour l'état d'une offre.
     *
     * @param o L'offre à mettre à jour.
     */
    @Override
    public void updateEtatOffre(Offre o){
        if(o.getTypeOffre() == TypeOffre.DON){
            o.setEtat(EtatOffre.TERMINEE);
        }else if(o.getTypeOffre() == TypeOffre.PRET){
            o.setEtat(EtatOffre.EN_COURS);
        }
        em.merge(o);
    }
    
    /**
    * Obtient une liste des offres publiques disponibles.
    *
    * @return La liste des offres publiques disponibles.
    */
    @Override
    public List<Offre> catalogueOffres() {
        TypedQuery<Offre> query = getEntityManager().createQuery(
                "SELECT o FROM Offre o WHERE o.utilisateur IS NOT NULL AND o.etat = :etatOffre", Offre.class);
        query.setParameter("etatOffre", EtatOffre.DISPONIBLE);
        return query.getResultList();
    }
    
    /**
    * Obtient une liste des offres en fonction des filtres spécifiés.
    *
    * @param type      Le type d'offre.
    * @param etat      L'état de l'offre.
    * @param categorie La catégorie de l'offre.
    * @return La liste des offres correspondant aux filtres spécifiés.
    */
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
    
    /**
     * Obtient la liste des équipements disponibles pour un membre.
     *
     * @param idPersonne L'identifiant du membre.
     * @return La liste des équipements disponibles pour le membre spécifié.
     */
    @Override
    public List<Offre> MesEquipementDisponible(Long idPersonne) {
        TypedQuery<Offre> query = getEntityManager().createQuery(
                "SELECT o FROM Offre o WHERE o.utilisateur.id = :idPersonne", Offre.class);
        query.setParameter("idPersonne", idPersonne);
        return query.getResultList();
    }
    
    /**
    * Obtient la liste des offres publiées par un membre.
    *
    * @param m Le membre dont on souhaite obtenir les offres publiées.
    * @return La liste des offres publiées par le membre spécifié.
    */
    @Override
    public List<Offre> listeOffre(Membre m){
        String txt = "SELECT o FROM Offre o WHERE o.utilisateur.id = :p";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", m.getId());
        List<Offre> result = req.getResultList();
        return result;
    }
    
    /**
     * Obtient le nombre total d'offres publiées par un membre.
     *
     * @param u La personne dont on souhaite obtenir le nombre d'offres publiées.
     * @return Le nombre total d'offres publiées par la personne spécifiée.
     */
    @Override
    public int getNombreOffreByMembre(Personne u) {
        String txt = "SELECT COUNT(o) FROM Offre o WHERE o.utilisateur.id = :idUtilisateur";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("idUtilisateur", u.getId());
        Long count = (Long) req.getSingleResult();
        return count.intValue();
    }

    /**
     * Modifie une offre existante.
     *
     * @param o L'offre à modifier.
     * @return true si l'offre a été modifiée avec succès, sinon false.
     */
    @Override
    public Boolean modifierOffre(Offre o) {
        em.merge(o);
        return true;
    }
}
