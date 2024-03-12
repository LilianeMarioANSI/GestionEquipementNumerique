/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Session;

import Entite.Agence;
import Entite.RoleUtilisateur;
import Entite.Personne;
import Facade.UtilisateurFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author loulo
 */
@Stateless
public class SessionStandard implements SessionStandardLocal {
    
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    
    @Override
    public Personne InscriptionUtilisateur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence, RoleUtilisateur roleUtilisateur) {
        return utilisateurFacade.CreerUtilisateur(login, mdp, nom, prenom, bureau, telephone, agence, roleUtilisateur);
    }
}
