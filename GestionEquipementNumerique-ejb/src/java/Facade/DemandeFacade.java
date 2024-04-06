/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Demande;
import Entite.Membre;
import Entite.Offre;
import Entite.Personne;
import Entite.StatutDemande;
import Entite.TypeOffre;
import java.util.List;
import java.sql.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author loulo
 */
@Stateless
public class DemandeFacade extends AbstractFacade<Demande> implements DemandeFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeFacade() {
        super(Demande.class);
    }
    
    /**
    * Obtient le nombre de membres ayant effectué des demandes dans une période de temps spécifiée.
    *
    * @param dateDebut La date de début de la période.
    * @param dateFin   La date de fin de la période.
    * @return Le nombre de membres ayant effectué des demandes dans la période spécifiée.
    */
    @Override
    public int getNombreMembreAvecDemandeByPeriode(Date dateDebut, Date dateFin) {
        String txt = "SELECT COUNT(DISTINCT d.utilisateur) FROM Demande d WHERE d.dateDemande BETWEEN :dateDebut AND :dateFin";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("dateDebut", dateDebut);
        req.setParameter("dateFin", dateFin);
        Long count = (Long) req.getSingleResult();
        return count.intValue();
    }
    
    /**
     * Crée une nouvelle demande sur une offre donnée par une personne donnée.
     *
     * @param personne La personne qui fait la demande.
     * @param offre    L'offre pour laquelle la demande est faite.
     * @return La demande créée.
     */
    @Override
    public Demande creerDemande(Personne personne, Offre offre) {
        Demande demande = new Demande();
        demande.setStatut(StatutDemande.EN_COURS);
        demande.setDateDemande(new Date(System.currentTimeMillis()));
        demande.setUtilisateur(personne);
        demande.setOffre(offre);
        em.persist(demande);
        return demande;
    }
    
    /**
     * Obtient la liste des prêts pour un membre donné.
     *
     * @param m Le membre pour lequel récupérer les prêts.
     * @return La liste des prêts du membre.
     */
    @Override
    public List<Demande> listePrêts(Membre m){
        String txt = "SELECT d FROM Demande d WHERE d.utilisateur.id = :p AND d.offre.typeOffre = :typeOffre";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", m.getId());
        req.setParameter("typeOffre", TypeOffre.PRET);
        List<Demande> result = req.getResultList();
        return result;
    }

    /**
     * Obtient la liste des dons pour un membre donné.
     *
     * @param m Le membre pour lequel récupérer les dons.
     * @return La liste des dons du membre.
     */
    @Override
    public List<Demande> listeDon(Membre m){
        String txt = "SELECT d FROM Demande d WHERE d.utilisateur.id = :p AND d.offre.typeOffre = :typeOffre";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", m.getId());
        req.setParameter("typeOffre",TypeOffre.DON);
        List<Demande> result = req.getResultList();
        return result;
    }
    
    /**
     * Obtient le nombre de demandes effectuées par un utilisateur donné.
     *
     * @param u L'utilisateur pour lequel compter les demandes.
     * @return Le nombre de demandes effectuées par l'utilisateur.
     */
    @Override
    public int getNombreDemandeByMembre(Personne u) {
        String txt = "SELECT COUNT(d) FROM Demande d WHERE d.utilisateur.id = :idUtilisateur";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("idUtilisateur", u.getId());
        Long count = (Long) req.getSingleResult();
        return count.intValue();
    }
    
    
    /**
     * Supprime complètement une demande de la base de données.
     *
     * @param demande La demande à supprimer.
     */
    @Override
    public void supprimerDemande(Demande demande) {
        em.remove(demande);
    }
        
    /**
     * Recherche une demande spécifique dans la base de données par son identifiant.
     *
     * @param idDemande L'identifiant de la demande à rechercher.
     * @return La demande correspondant à l'identifiant spécifié.
     */
    @Override
    public Demande rechercherDemande(long idDemande){
        Query req = getEntityManager().createQuery("Select d from Demande as d where d.id=:idDemande");
        req.setParameter("idDemande", idDemande);
        Demande result = (Demande) req.getResultList().get(0);
        return result;
    }
}
