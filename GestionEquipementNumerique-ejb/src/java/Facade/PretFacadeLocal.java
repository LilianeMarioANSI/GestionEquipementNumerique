/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Pret;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface PretFacadeLocal {

    void create(Pret pret);

    void edit(Pret pret);

    void remove(Pret pret);

    Pret find(Object id);

    List<Pret> findAll();

    List<Pret> findRange(int[] range);

    int count();
    
}
