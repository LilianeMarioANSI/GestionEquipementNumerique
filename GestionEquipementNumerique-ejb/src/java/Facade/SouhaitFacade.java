/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Membre;
import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeSouhait;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author loulo
 */
@Stateless
public class SouhaitFacade extends AbstractFacade<Souhait> implements SouhaitFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SouhaitFacade() {
        super(Souhait.class);
    }
    
    /**
     * Obtient la liste des souhaits d'un membre.
     *
     * @param idMembre L'identifiant du membre.
     * @return La liste des souhaits du membre spécifié.
     */
    @Override
    public List<Souhait> getSouhaitByMembre(long idMembre) {
        String txt = "SELECT s FROM Souhait s WHERE s.utilisateur.id = :id";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("id", idMembre);
        List<Souhait> result = req.getResultList();
        return result;
    }

    /**
     * Crée un nouveau souhait.
     *
     * @param datePublication La date de publication du souhait.
     * @param dateDebut       La date de début du souhait.
     * @param dateFin         La date de fin du souhait.
     * @param typeSouhait     Le type de souhait.
     * @param typeAccessoire  Le type d'accessoire souhaité.
     * @param description     La description du souhait.
     * @param utilisateur     L'utilisateur associé au souhait.
     * @return Le souhait créé.
     */
    @Override
    public Souhait creerSouhait(Date datePublication, Date dateDebut, Date dateFin, TypeSouhait typeSouhait, TypeAccessoire typeAccessoire, String description, Personne utilisateur) {
        Souhait s = new Souhait();
        s.setDatePublication(datePublication);
        s.setDateDebut(dateDebut);
        s.setDateFin(dateFin);
        s.setTypeSouhait(typeSouhait);
        s.setTypeAccessoire(typeAccessoire);
        s.setDescription(description);
        s.setUtilisateur(utilisateur);
        em.persist(s);
        return s;
    }
  
    /**
     * Obtient la liste des souhaits d'un membre.
     *
     * @param m Le membre dont on souhaite obtenir les souhaits.
     * @return La liste des souhaits du membre spécifié.
     */
    @Override
    public List<Souhait> listeSouhaits(Membre m){
        String txt = "SELECT s FROM Souhait s WHERE s.utilisateur.id = :p";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", m.getId());
        List<Souhait> result = req.getResultList();
        return result;
    }
}
