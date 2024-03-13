/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.SuperAdministrateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface SuperAdministrateurFacadeLocal {

    void create(SuperAdministrateur superAdministrateur);

    void edit(SuperAdministrateur superAdministrateur);

    void remove(SuperAdministrateur superAdministrateur);

    SuperAdministrateur find(Object id);

    List<SuperAdministrateur> findAll();

    List<SuperAdministrateur> findRange(int[] range);

    int count();
    
}
