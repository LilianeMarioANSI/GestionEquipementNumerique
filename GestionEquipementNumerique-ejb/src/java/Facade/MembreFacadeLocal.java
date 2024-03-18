/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Agence;
import Entite.Membre;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface MembreFacadeLocal {

    void create(Membre membre);

    void edit(Membre membre);

    void remove(Membre membre);

    Membre find(Object id);

    List<Membre> findAll();

    List<Membre> findRange(int[] range);

    int count();
    
    Membre CreerMembre(String login, String mdp, String nom, String prenom, String bureau, String telephone, Agence agence);

    int getNombreMembre();
}
