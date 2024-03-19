/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Session;

import Entite.Accessoire;
import Entite.EtatAccessoire;
import Entite.Offre;
import Entite.TypeOffre;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface SessionAdministrateurLocal {
    
    List <String> getOffresParPeriode_Json(Date dateDebut, Date dateFin);
    
    public int getNombreMembre();
    
    int getNombreOffrePublic();
    
    int getNombreOffrePublicByType(TypeOffre type);
    
    int getNombreMembreAvecOffreByPeriode(Date dateDebut, Date dateFin);
    
    int getNombreMembreAvecDemandeByPeriode(Date dateDebut, Date dateFin);
    
    List<Accessoire> getAccessoireByEtat(EtatAccessoire etat);
    
    List<String> getTop5AgenceByOffre(Date dateDebut, Date dateFin);
}
