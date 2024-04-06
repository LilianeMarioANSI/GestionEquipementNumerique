/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Agence;
import Entite.Superviseur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author loulo
 */
@Stateless
public class SuperviseurFacade extends AbstractFacade<Superviseur> implements SuperviseurFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuperviseurFacade() {
        super(Superviseur.class);
    }
    
    /**
     * Authentifie un superviseur sur la plateforme.
     *
     * @param login L'adresse mail du superviseur.
     * @param mdp   Le mot de passe du superviseur (haché).
     * @return Le superviseur authentifié s'il existe et que les informations de connexion sont correctes, sinon null.
     */
    @Override
    public Superviseur IdentificationSuperviseur(String login, String mdp) {
        TypedQuery<Superviseur> query = getEntityManager().createQuery(
                "SELECT s FROM Superviseur s WHERE s.login = :login", Superviseur.class);
        query.setParameter("login", login);

        try {
            Superviseur superviseur = query.getSingleResult();
            // hacher le mot de passe et vérifier s'il correspond à celui stocké dans la base de données
            if (BCrypt.checkpw(mdp, superviseur.getMdp())) {
                return superviseur;
            }
        } catch (NoResultException e) {
            System.err.println("Tentative de connexion avec un login invalide : " + login);
        }
        return null;
    }
    
    /**
     * Crée un nouveau superviseur sur la plateforme.
     * <p>
     * Cette méthode n'est accessible que par le super administrateur.
     *
     * @param login     Le nom d'utilisateur du superviseur.
     * @param mdp       Le mot de passe du superviseur (haché).
     * @param nom       Le nom du superviseur.
     * @param prenom    Le prénom du superviseur.
     * @param bureau    Le bureau du superviseur.
     * @param telephone Le numéro de téléphone du superviseur.
     * @param agence    L'agence à laquelle le superviseur est associé.
     * @return Le superviseur créé.
     */
    @Override
    public Superviseur CreerSuperviseur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence) {
        Superviseur superviseur = new Superviseur();
        superviseur.setLogin(login);
        superviseur.setMdp(mdp);
        superviseur.setNom(nom);
        superviseur.setPrenom(prenom);
        superviseur.setBureau(bureau);
        superviseur.setTelephone(telephone);
        superviseur.setAgence(agence);
        em.persist(superviseur);
        return superviseur;
    }
}
