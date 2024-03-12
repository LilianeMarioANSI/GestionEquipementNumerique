/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Agence;
import Entite.RoleUtilisateur;
import Entite.Personne;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author loulo
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Personne> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Personne.class);
    }
    
    @Override
    public Personne CreerUtilisateur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence, RoleUtilisateur roleUtilisateur) {
        Personne utilisateur = new Personne();
        utilisateur.setLogin(login);
        utilisateur.setMdp(mdp);
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setBureau(bureau);
        utilisateur.setTelephone(telephone);
        utilisateur.setAgence(agence);
        utilisateur.setRoleUtilisateur(roleUtilisateur);
        em.persist(utilisateur);
        return utilisateur;
    }
    
}
