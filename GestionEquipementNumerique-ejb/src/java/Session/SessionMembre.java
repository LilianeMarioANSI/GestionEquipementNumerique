/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Session;

import Entite.Accessoire;
import Entite.Agence;
import Entite.Demande;
import Entite.EtatOffre;
import Entite.Membre;
import Entite.Offre;
import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeOffre;
import Entite.TypeSouhait;
import Facade.AccessoireFacade;
import Facade.AccessoireFacadeLocal;
import Facade.MembreFacadeLocal;
import Facade.OffreFacadeLocal;
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
    private AccessoireFacadeLocal accessoireFacade;

    @EJB
    private OffreFacadeLocal offreFacade;

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
    
    public Membre RechercherMembre(long id) {
        return membreFacade.rechercherMembre(id);
    }
    /*
        Membre
    */
    @Override
    public Membre InscriptionUtilisateur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence) {
        return membreFacade.CreerMembre(login, mdp, nom, prenom, bureau, telephone, agence);
    }
    
    @Override
    public Membre IdentificationMembre(String log, String mdp){
        Membre m=membreFacade.IdentificationMembre(log, mdp);
        return m;
    }
    
    @Override
    public boolean SupprimerMembre(long idmembre){
        boolean resultat=false;
        Membre m=membreFacade.rechercherMembre(idmembre);
        if(m!=null){
        membreFacade.SupprimerMembre(m);
        resultat=true;
        }
        return resultat;     } 
    
    @Override
    public boolean ModifierMembre (long idmembre, String nouveauNom, String nouveauPrenom, String nouvelEmail, String nouveauTelephone, String nouveauBureau, Agence agence){
        boolean resultat=false;
        Membre m=membreFacade.rechercherMembre(idmembre);
        if(m!=null){
        membreFacade.ModifierInformations(m, nouveauNom, nouveauPrenom, nouvelEmail, nouveauTelephone, nouveauBureau, agence);
        resultat=true;
        }
        return resultat;
    }
    
    @Override
    public Agence getAgenceById(String agenceId){
        return membreFacade.getAgenceById(agenceId);
    } 
    /*
        Catalogue Offres
    */
    @Override
    public List<Offre> ConsulterCatalogue(){
        return offreFacade.catalogueOffres();
    } 
    
    @Override
    public List<Offre> ConsulterCatalogueFiltre(String type, String etat, String categorie){
        return offreFacade.catalogueOffresFiltre(type, etat, categorie);
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
        o = offreFacade.creerOffre(libelle, description, datePublication, typeOffre, dateDebut, dateFin, accesoires, user, EtatOffre.DISPONIBLE);
        return o;
    }

    @Override
    public List<Accessoire> getAllAccesoire() {
        return accessoireFacade.getListeAccessoires();
    }
    

}
