/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Session;

import Entite.Agence;
import Entite.Membre;
import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeSouhait;
import Facade.MembreFacadeLocal;
import Facade.PersonneFacadeLocal;
import Facade.SouhaitFacadeLocal;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author loulo
 */
@Stateless
public class SessionMembre implements SessionMembreLocal {

    @EJB
    private PersonneFacadeLocal personneFacade;

    @EJB
    private SouhaitFacadeLocal souhaitFacade;
    
    @EJB
    private MembreFacadeLocal membreFacade;
    
    
    
    /*
        Personne
    */
    
    @Override
    public Personne RechercherPersonne(long id) {
        return personneFacade.rechercherPersonne(id);
    }
    
    /*
        Membre
    */
    @Override
    public Membre InscriptionUtilisateur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence) {
        return membreFacade.CreerMembre(login, mdp, nom, prenom, bureau, telephone, agence);
    }
    
    /*
        Souhait
    */
    
    @Override
    public List<Souhait> GetSouhaitByMembre(long idMembre){
        return souhaitFacade.getSouhaitByMembre(idMembre);
    }

    @Override
    public Souhait CreerSouhait(Date datePublication, Date dateDebut, Date dateFin, TypeSouhait typeSouhait, TypeAccessoire typeAccessoire, String description, Personne utilisateur) {
        return souhaitFacade.creerSouhait(datePublication, dateDebut, dateFin, typeSouhait, typeAccessoire, description, utilisateur);
    }
    
    @Override
    public void SupprimerSouhait(Souhait souhait) {
        souhaitFacade.supprimerSouhait(souhait);
    }
    
    
}
