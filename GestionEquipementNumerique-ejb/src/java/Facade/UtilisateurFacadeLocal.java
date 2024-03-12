/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Agence;
import Entite.RoleUtilisateur;
import Entite.Personne;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface UtilisateurFacadeLocal {

    void create(Personne utilisateur);

    void edit(Personne utilisateur);

    void remove(Personne utilisateur);

    Personne find(Object id);

    List<Personne> findAll();

    List<Personne> findRange(int[] range);

    int count();
    
    Personne CreerUtilisateur(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence, RoleUtilisateur roleUtilisateur);
    
}
