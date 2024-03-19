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
    public Agence getAgenceById(String agenceId) {
    for (Agence agence : Agence.values()) {
        if (agence.label.equalsIgnoreCase(agenceId)) {
            return agence;
        }
    }
    return null; // Aucune agence trouvée avec le nom donné
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
    
    @Override
    public void SupprimerMembre(Membre membre) {
        em.remove(membre);
    }
    
    
    @Override
    public Membre rechercherMembre(long id) {
        Query req = getEntityManager().createQuery("Select m from Membre as m where m.id=:idMembre");
        req.setParameter("idMembre", id);
        Membre result = (Membre) req.getResultList().get(0);
        return result;
    }
    
    @Override
    public void ModifierInformations(Membre membre, String nouveauNom, String nouveauPrenom, String nouvelEmail, String nouveauTelephone, String nouveauBureau, Agence nouvelleAgence) {
    // Récupérer le membre existant dans la base de données
    Membre membreExistant = em.find(Membre.class, membre.getId());
    
    // Mettre à jour les champs modifiés
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
    
    // Effectuer la mise à jour dans la base de données
    em.merge(membreExistant);
}
}
