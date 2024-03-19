/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Session;

import Entite.Accessoire;
import Entite.EtatAccessoire;
import Entite.Offre;
import Entite.TypeOffre;
import Facade.AccessoireFacadeLocal;
import Facade.DemandeFacadeLocal;
import Facade.MembreFacadeLocal;
import Facade.OffreFacadeLocal;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author loulo
 */
@Stateless
public class SessionAdministrateur implements SessionAdministrateurLocal {

    @EJB
    private AccessoireFacadeLocal accessoireFacade;

    @EJB
    private DemandeFacadeLocal demandeFacade;

    @EJB
    private MembreFacadeLocal membreFacade;

    @EJB
    private OffreFacadeLocal offreFacade;
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List <String> getOffresParPeriode_Json(Date dateDebut, Date dateFin) {
        return offreFacade.getOffresParPeriode_Json(dateDebut, dateFin);
    }
    
    @Override
    public int getNombreMembre() {
        return membreFacade.getNombreMembre();
    }
    
    @Override
    public int getNombreOffrePublic() {
        return offreFacade.getNombreOffrePublic();
    }
    
    @Override
    public int getNombreOffrePublicByType(TypeOffre type) {
        return offreFacade.getNombreOffrePublicByType(type);
    }
    
    
    @Override
    public int getNombreMembreAvecOffreByPeriode(Date dateDebut, Date dateFin){
        return offreFacade.getNombreMembreAvecOffreByPeriode(dateDebut, dateFin);
    }
    
    @Override
    public int getNombreMembreAvecDemandeByPeriode(Date dateDebut, Date dateFin){
        return demandeFacade.getNombreMembreAvecDemandeByPeriode(dateDebut, dateFin);
    }
    
    @Override
    public List<Accessoire> getAccessoireByEtat(EtatAccessoire etat){
        return accessoireFacade.getAccessoireByEtat(etat);
    }
    
    @Override
    public List<String> getTop5AgenceByOffre(Date dateDebut, Date dateFin){
        return offreFacade.getTop5AgenceByOffre(dateDebut, dateFin);
    }
}
