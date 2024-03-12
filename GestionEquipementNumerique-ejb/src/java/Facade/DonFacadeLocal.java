/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Don;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface DonFacadeLocal {

    void create(Don don);

    void edit(Don don);

    void remove(Don don);

    Don find(Object id);

    List<Don> findAll();

    List<Don> findRange(int[] range);

    int count();
    
}
