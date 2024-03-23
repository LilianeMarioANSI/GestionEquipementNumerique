/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
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
    
    void SupprimerSouhait(Souhait souhait);
    
    Membre IdentificationMembre(String log, String mdp);
    
    List<Offre> ConsulterCatalogue();
    
    List<Offre> FiltrerCatalogue(String etatEquipement, String categorie, String typeOffre);

    Agence getAgenceById(String agenceId);
    
    Offre creationOffre (Offre O);
    
    Accessoire CreerAccessoire(Accessoire a);

    List<Demande> listeDon(Personne p);
    
    List<Demande> listePrêts(Personne p);
    
    List<Offre> listeMesEquipements(long Id);
    
    boolean SupprimerDemande(long idDemande);
    
    Demande RechercherDemande(long id);
}
