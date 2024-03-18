/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Accessoire;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author loulo
 */
@Local
public interface AccessoireFacadeLocal {

    void create(Accessoire accessoire);

    void edit(Accessoire accessoire);

    void remove(Accessoire accessoire);

    Accessoire find(Object id);

    List<Accessoire> findAll();

    List<Accessoire> findRange(int[] range);

    int count();
    Accessoire CreerAccessoire(Accessoire a);
    
}
