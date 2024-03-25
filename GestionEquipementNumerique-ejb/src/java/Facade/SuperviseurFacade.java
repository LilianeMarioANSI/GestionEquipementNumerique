/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Agence;
import Entite.Membre;
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
    
    @Override
    public Superviseur IdentificationSuperviseur(String login, String mdp) {
        TypedQuery<Superviseur> query = getEntityManager().createQuery(
                "SELECT s FROM Superviseur s WHERE s.login = :login", Superviseur.class);
        query.setParameter("login", login);

        try {
            Superviseur superviseur = query.getSingleResult();
            if (BCrypt.checkpw(mdp, superviseur.getMdp())) {
                return superviseur;
            }
        } catch (NoResultException e) {
            System.err.println("Tentative de connexion avec un login invalide : " + login);
        }

        return null;
    }
    
    
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
