/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Agence;
import Entite.Membre;
import Entite.Personne;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.Query;

/**
 *
 * @author loulo
 */
@Stateless
public class MembreFacade extends AbstractFacade<Membre> implements MembreFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MembreFacade() {
        super(Membre.class);
    }
    
    /**
     * Permet à un membre de s'inscrire sur la plateforme avec des informations personnelles.
     *
     * @param login      Le login du membre.
     * @param mdp        Le mot de passe du membre.
     * @param nom        Le nom du membre.
     * @param prenom     Le prénom du membre.
     * @param bureau     Le bureau du membre.
     * @param telephone  Le numéro de téléphone du membre.
     * @param agence     L'agence à laquelle le membre est affilié.
     * @return Le membre créé.
     */
    @Override
    public Membre CreerMembre(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence) {
        Membre utilisateur = new Membre();
        utilisateur.setLogin(login);
        utilisateur.setMdp(mdp);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setBureau(bureau);
        utilisateur.setTelephone(telephone);
        utilisateur.setAgence(agence);
        em.persist(utilisateur);
        return utilisateur;
    }

    /**
     * Permet à un membre de s'identifier sur la plateforme.
     *
     * @param login Le login du membre.
     * @param mdp   Le mot de passe du membre.
     * @return Le membre identifié, s'il existe et que le mot de passe est correct ; sinon, null.
     */
    @Override
    public Membre IdentificationMembre(String login, String mdp) {
        TypedQuery<Membre> query = getEntityManager().createQuery(
                "SELECT m FROM Membre m WHERE m.login = :login", Membre.class);
        query.setParameter("login", login);

        try {
            Membre membre = query.getSingleResult();
            // hacher le mot de passe et vérifier s'il correspond à celui stocké dans la base de données
            if (BCrypt.checkpw(mdp, membre.getMdp())) {
                return membre;
            }
        } catch (NoResultException e) {
            System.err.println("Tentative de connexion avec un login invalide : " + login);
        }
        return null;
    }
    
    /**
     * Supprime un membre de la base de données.
     *
     * @param membre Le membre à supprimer.
     */
    @Override
    public void SupprimerMembre(Membre membre) {
        if (membre != null) {
            em.remove(membre);
        }
    }
    
    /**
     * Obtient une agence à partir de son identifiant.
     *
     * @param agenceId L'identifiant de l'agence à rechercher.
     * @return L'agence correspondant à l'identifiant spécifié, s'il existe ; sinon, null.
     */
    @Override
    public Agence getAgenceById(String agenceId) {
        for (Agence agence : Agence.values()) {
            if (agence.label.equalsIgnoreCase(agenceId)) {
                return agence;
            }
        }
        return null;
    }
    
    /**
    * Modifie les informations d'un membre dans la base de données.
    *
    * @param membre         Le membre à modifier.
    * @param nouveauNom     Le nouveau nom du membre.
    * @param nouveauPrenom  Le nouveau prénom du membre.
    * @param nouvelEmail    Le nouvel e-mail du membre.
    * @param nouveauTelephone Le nouveau numéro de téléphone du membre.
    * @param nouveauBureau  Le nouveau bureau du membre.
    * @param nouvelleAgence La nouvelle agence à laquelle le membre est affilié.
    */
    @Override
    public void ModifierInformations(Membre membre, String nouveauNom, String nouveauPrenom, String nouvelEmail, String nouveauTelephone, String nouveauBureau, Agence nouvelleAgence) {
        Membre membreExistant = em.find(Membre.class, membre.getId());

        if (!Objects.equals(membreExistant.getNom(), nouveauNom)) {
            membreExistant.setNom(nouveauNom);
        }
        if (!Objects.equals(membreExistant.getPrenom(), nouveauPrenom)) {
            membreExistant.setPrenom(nouveauPrenom);
        }
        if (!Objects.equals(membreExistant.getLogin(), nouvelEmail)) {
            membreExistant.setLogin(nouvelEmail);
        }
        if (!Objects.equals(membreExistant.getTelephone(), nouveauTelephone)) {
            membreExistant.setTelephone(nouveauTelephone);
        }
        if (!Objects.equals(membreExistant.getBureau(), nouveauBureau)) {
            membreExistant.setBureau(nouveauBureau);
        }
        if (!Objects.equals(membreExistant.getAgence(), nouvelleAgence)) {
            membreExistant.setAgence(nouvelleAgence);
        }
        em.merge(membreExistant);
    }
    
    /**
    * Recherche un membre dans la base de données par son identifiant.
    *
    * @param id L'identifiant du membre à rechercher.
    * @return Le membre correspondant à l'identifiant spécifié.
    */
    @Override
    public Membre rechercherMembre(long id) {
        Query req = getEntityManager().createQuery("Select m from Membre as m where m.id=:idMembre");
        req.setParameter("idMembre", id);
        Membre result = (Membre) req.getResultList().get(0);
        return result;
    }
    
    /**
     * Obtient la liste de tous les membres enregistrés sur la plateforme.
     *
     * @return La liste de tous les membres.
     */
    @Override
    public List<Membre> ListeMembres() {
        String txt = "SELECT m FROM Membre m";
        Query req = getEntityManager().createQuery(txt);
        List<Membre> result = req.getResultList();
        return result;
    }
    
    /**
    * Obtient la liste des membres appartenant à la même agence qu'un utilisateur donné.
    *
    * @param utilisateur L'utilisateur pour lequel récupérer les membres de la même agence.
    * @return La liste des membres appartenant à la même agence.
    */
    @Override
    public List<Membre> ListeMembresMemeAgence(Personne utilisateur) {
        String txt = "SELECT m FROM Membre m WHERE m.agence = :paramAgence";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("paramAgence", utilisateur.getAgence());
        List<Membre> result = req.getResultList();
        return result;
    }
    
    /**
     * Obtient le nombre total de membres enregistrés sur la plateforme.
     *
     * @return Le nombre total de membres.
     */
    @Override
    public int getNombreMembre() {
        String txt = "SELECT m FROM Membre m";
        Query req = getEntityManager().createQuery(txt);
        List<Membre> result = req.getResultList();
        return result.size();
    }
    
    /**
    * Recherche un membre dans la base de données par son login.
    *
    * @param login Le login du membre à rechercher.
    * @return Le membre correspondant au login spécifié, s'il existe ; sinon, null.
    */
    @Override
    public Membre rechercherMembreParLogin(String login) {
        Query req = getEntityManager().createQuery(
                "SELECT m FROM Membre m WHERE m.login = :login");
        req.setParameter("login", login);
        List<Membre> result = req.getResultList();
        if(result.isEmpty()){
            return null;
        }else{
            return result.get(0);
        }
    }
}
