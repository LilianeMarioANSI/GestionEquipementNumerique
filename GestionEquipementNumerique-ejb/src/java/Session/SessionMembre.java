/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Session;

import Entite.Accessoire;
import Entite.Agence;
import Entite.EtatOffre;
import Entite.Membre;
import Entite.Offre;
import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeOffre;
import Entite.TypeSouhait;
import Facade.AccessoireFacade;
import Facade.MembreFacadeLocal;
import Facade.OffreFacade;
import Facade.PersonneFacadeLocal;
import Facade.SouhaitFacadeLocal;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.print.attribute.standard.DateTimeAtCreation;


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

    @EJB
    private OffreFacade OffreFacade;
    
    @EJB
    private AccessoireFacade accessoireFacade;


    
    
    
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
    
    @Override
    public Offre creationOffre (String libelle, String description,TypeOffre typeOffre, Date dateDebut, Date dateFin, Accessoire accesoires, Personne user, EtatOffre etatOffre) {
        Offre o= null;
        Date datePublication = new Date(System.currentTimeMillis());
        o = OffreFacade.creerOffre(libelle, description, datePublication, typeOffre, dateDebut, dateFin, accesoires, user, EtatOffre.DISPONIBLE);
        return o;
    }

    @Override
    public List<Accessoire> getAllAccesoire() {
        return accessoireFacade.getListeAccessoires();
    }
    
}
