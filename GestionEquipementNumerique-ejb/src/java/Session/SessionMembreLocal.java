/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Session;

import Entite.Agence;
import Entite.Membre;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface SessionMembreLocal {
    
    Membre InscriptionUtilisateur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence);
}
