/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Session;

import Entite.Accessoire;
import Entite.Agence;
import Entite.Badge;
import Entite.Demande;
import Entite.EtatOffre;
import Entite.Membre;
import Entite.NiveauBadge;
import Entite.Offre;
import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeOffre;
import Entite.TypeSouhait;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface SessionMembreLocal {
    
    Membre InscriptionUtilisateur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence);
    
    List<Souhait> GetSouhaitByMembre(long idMembre);

    Souhait CreerSouhait(Date datePublication, Date dateDebut, Date dateFin, TypeSouhait typeSouhait, TypeAccessoire typeAccessoire, String description, Personne utilisateur);

    public Personne RechercherPersonne(long id);
    
    boolean SupprimerMembre(long idmembre);
    
    boolean ModifierMembre (long idmembre, String nouveauNom, String nouveauPrenom, String nouvelEmail, String nouveauTelephone, String nouveauBureau, Agence agence);
    
    
    Membre IdentificationMembre(String log, String mdp);
    
    List<Offre> ConsulterCatalogue();
    
    List<Offre> FiltrerCatalogue(String etatEquipement, String categorie, String typeOffre);

    Agence getAgenceById(String agenceId);
<<<<<<< HEAD
    
    Offre creationOffre (Offre O);
    
    Accessoire CreerAccessoire(Accessoire a);
    
    List<Demande> listeDon(Membre m);
    List<Demande> listePrêts(Membre m);
    List<Offre> listeMesOffres(Membre m) ;
    List<Souhait> listeMesSouhaits(Membre m) ;
=======
    
    Offre creationOffre (Offre O);
    
    Accessoire CreerAccessoire(Accessoire a);
    
    List<Demande> listeDon(Personne p);
    
    List<Demande> listePrêts(Personne p);
    
    List<Offre> listeMesOffres(Personne p) ;
    
    List<Souhait> listeMesSouhaits(Personne p) ;
>>>>>>> 3b5c20f (Badge + vérification date création offre)
    
    Demande CreerDemande(Personne personne, Offre offre);
    
    void updateEtatOffre(Offre o);
    
<<<<<<< HEAD
    
    List<Offre> listeMesEquipements(long Id);
    
    boolean SupprimerDemande(long idDemande);
    
    Demande RechercherDemande(long id);
=======
    int getNombreDemandeByMembre(Personne u);
    
    int getNombreOffreByMembre(Personne u);

    boolean verificationBadgeExistant(Personne utilisateur, NiveauBadge niveau);
    
    Badge creerBadge(NiveauBadge niveau, Personne membre);

    public List<Badge> getBadgeByMembre(Personne u);
    
>>>>>>> 3b5c20f (Badge + vérification date création offre)
}
