/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Session;

import Entite.Accessoire;
import Entite.Agence;
import Entite.Badge;
import Entite.Demande;
import Entite.Membre;
import Entite.NiveauBadge;
import Entite.Offre;
import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeOffre;
import Entite.TypeSouhait;
import Facade.AccessoireFacadeLocal;
import Facade.BadgeFacadeLocal;
import Facade.DemandeFacadeLocal;
import Facade.MembreFacadeLocal;
import Facade.OffreFacadeLocal;
import Facade.PersonneFacadeLocal;
import Facade.SouhaitFacadeLocal;
import java.sql.Date;
import java.util.ArrayList;
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
    private BadgeFacadeLocal badgeFacade;
    @EJB
    private DemandeFacadeLocal demandeFacade;

    @EJB
    private AccessoireFacadeLocal accessoireFacade;

    @EJB
    private OffreFacadeLocal offreFacade;

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
    
    @Override
    public Membre IdentificationMembre(String log, String mdp){
        Membre user=null; 
        Membre m=membreFacade.IdentificationMembre(log, mdp);
        if(m!=null){
            user=m;
        }
        else {
            user=null;
        }
        return user;
    }
    
    @Override
    public boolean SupprimerMembre(long idmembre){
        boolean resultat=false;
        Membre m=membreFacade.rechercherMembre(idmembre);
        if(m!=null){
            membreFacade.SupprimerMembre(m);
            resultat=true;
        }
        return resultat;
    } 
    
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
    @Override
    public Membre RechercherMembre(long id) {
        return membreFacade.rechercherMembre(id);
    }
    
    /*
        Catalogue Offres
    */
    @Override
    public List<Offre> ConsulterCatalogue(){
        return offreFacade.catalogueOffres();
    } 
    
    @Override
    public void updateEtatOffre(Offre o){
        offreFacade.updateEtatOffre(o);
    }
    
    @Override
    
    public List<Offre> FiltrerCatalogue(String etatEquipement, String categorie, String typeOffre) {
        List<Offre> offresFiltrees = new ArrayList<>();
        List<Offre> toutesLesOffres = offreFacade.catalogueOffres(); 

        for (Offre offre : toutesLesOffres) {
            if (etatEquipement == null || etatEquipement.isEmpty() || offre.getEtat().label.equals(etatEquipement)) {
                if (categorie == null || categorie.isEmpty() || offre.getAccessoire().getTypeAccessoire().label.equals(categorie)) {
                    if (typeOffre == null || typeOffre.isEmpty() || offre.getTypeOffre().label.equals(typeOffre)) {
                        offresFiltrees.add(offre);
                    }
                }
            }
        }

        return offresFiltrees;
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
    
    

    /*
        Offre
    */
    @Override
    public Offre creationOffre (Offre O) {
        return offreFacade.creerOffre(O);
    }

    @Override
    public Accessoire CreerAccessoire(Accessoire a) {
        return accessoireFacade.CreerAccessoire(a);
    }
    
    // Mes equipements
    @Override
    public List<Offre> listeMesEquipements(long Id){
        return offreFacade.MesEquipementDisponible(Id);
    }
    
    /*
        Mes Demandes
    */ 
    @Override
    public List<Demande> listePrêts(Membre m) {
        return demandeFacade.listePrêts(m);
    }

    @Override
    public List<Demande> listeDon(Membre m) {
        return demandeFacade.listeDon(m);
    }
    
    @Override
    public List<Offre> listeMesOffres(Membre m) {
        return offreFacade.listeOffre(m);
    }
    
        @Override
    public List<Souhait> listeMesSouhaits(Membre m) {
        return souhaitFacade.listeSouhaits(m);

    }
    
    @Override
    public Demande CreerDemande(Personne personne, Offre offre){
        return demandeFacade.creerDemande(personne, offre);
    }
    
    @Override
    public boolean verificationBadgeExistant(Personne utilisateur, NiveauBadge niveau){
        return badgeFacade.verificationBadgeExistant(utilisateur, niveau);
    }
    
    @Override
    public Badge creerBadge(NiveauBadge niveau, Personne membre){
        return badgeFacade.creerBadge(niveau, membre);
    }
    
    @Override
    public int getNombreDemandeByMembre(Personne u){
        return demandeFacade.getNombreDemandeByMembre(u);
    }
    @Override
    public int getNombreOffreByMembre(Personne u){
        return offreFacade.getNombreOffreByMembre(u);
    }
    
    @Override
    public List<Badge> getBadgeByMembre(Membre u){
        return badgeFacade.getBadgeByMembre(u);
    }

    @Override
    public boolean SupprimerDemande(long idDemande){
        boolean resultat=false;
        Demande d=demandeFacade.rechercherDemande(idDemande);
        if(d!=null){
            demandeFacade.supprimerDemande(d);
            resultat=true;
        }
        return resultat;
    }
    
    @Override
    public Demande RechercherDemande(long id) {
        return demandeFacade.rechercherDemande(id);
    }

    @Override
    public boolean ModifierOffre(Long idOffre, String intitule, String description, TypeOffre typeOffreEnum, Date dd, Date df){
        Offre o= offreFacade.find(idOffre);
        if (o==null){
            return false;
        }
        else {
            o.setIntitule(intitule);
            o.setDescription(description);
            o.setTypeOffre(typeOffreEnum);
            o.setDateDebut(dd);
            o.setDateFin(df);
            offreFacade.modifierOffre(o);
            return true;
        }

    }
}
