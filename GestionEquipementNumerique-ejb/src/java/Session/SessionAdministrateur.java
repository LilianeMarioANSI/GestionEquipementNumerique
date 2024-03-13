/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Session;

import Entite.Offre;
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
    private OffreFacadeLocal offreFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List <Offre> GetOffresParPeriode(Date dateDebut, Date dateFin) {
        return offreFacade.getOffresParPeriode(dateDebut, dateDebut);
    }
}
