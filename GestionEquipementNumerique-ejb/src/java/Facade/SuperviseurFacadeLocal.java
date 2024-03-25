/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Agence;
import Entite.Superviseur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface SuperviseurFacadeLocal {

    void create(Superviseur administrateur);

    void edit(Superviseur administrateur);

    void remove(Superviseur administrateur);

    Superviseur find(Object id);

    List<Superviseur> findAll();

    List<Superviseur> findRange(int[] range);

    int count();
    
    // nos méthodes
    
    Superviseur IdentificationSuperviseur(String login, String mdp);
    
    Superviseur CreerSuperviseur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence);
    
}
