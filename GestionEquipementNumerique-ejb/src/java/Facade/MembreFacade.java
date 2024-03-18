/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Agence;
import Entite.Membre;
import Entite.Personne;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public int getNombreMembre() {
        List<String> resultList = new ArrayList<>();
        String txt = "SELECT m FROM Membre m";
        Query req = getEntityManager().createQuery(txt);
        List<Membre> result = req.getResultList();
        return result.size();
    }
    
    
    
    
    
    
    @Override
    public Membre IdentificationMembre(String login, String mdp) {
    TypedQuery<Membre> query = getEntityManager().createQuery(
            "SELECT m FROM Membre m WHERE m.login = :login", Membre.class);
    query.setParameter("login", login);
    
    try {
        Membre membre = query.getSingleResult();
        if (BCrypt.checkpw(mdp, membre.getMdp())) {
            return membre;
        }
    } catch (NoResultException e) {
        System.err.println("Tentative de connexion avec un login invalide : " + login);
    }
    
    return null;
}
}
