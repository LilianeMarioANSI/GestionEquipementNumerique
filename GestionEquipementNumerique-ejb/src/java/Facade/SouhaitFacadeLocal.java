/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeSouhait;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface SouhaitFacadeLocal {

    void create(Souhait souhait);

    void edit(Souhait souhait);

    void remove(Souhait souhait);

    Souhait find(Object id);

    List<Souhait> findAll();

    List<Souhait> findRange(int[] range);

    int count();
    
    // nos méthodes
    
    List<Souhait> getSouhaitByMembre(long idMembre);

    Souhait creerSouhait(Date datePublication, Date dateDebut, Date dateFin, TypeSouhait typeSouhait, TypeAccessoire typeAccessoire, String description, Personne utilisateur);
    
    List<Souhait> listeSouhaits(Personne p);
}
