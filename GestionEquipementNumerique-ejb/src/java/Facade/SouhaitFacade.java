/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

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
    
    @Override
    public List<Souhait> getSouhaitByMembre(long idMembre) {
        String txt = "SELECT s FROM Souhait s WHERE s.utilisateur.id = :id";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("id", idMembre);
        List<Souhait> result = req.getResultList();
        
        return result;
    }

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
    
    @Override
    public List<Souhait> listeSouhaits(Personne p){
        String txt = "SELECT s FROM Souhait s WHERE s.utilisateur = :p";
        Query req = getEntityManager().createQuery(txt);
        req.setParameter("p", p);
        List<Souhait> result = req.getResultList();
        return result;
    }
    
}
