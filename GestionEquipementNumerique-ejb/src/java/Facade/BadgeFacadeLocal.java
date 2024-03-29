/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Badge;
import Entite.Membre;
import Entite.NiveauBadge;
import Entite.Personne;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface BadgeFacadeLocal {

    void create(Badge badge);

    void edit(Badge badge);

    void remove(Badge badge);

    Badge find(Object id);

    List<Badge> findAll();

    List<Badge> findRange(int[] range);

    int count();
    
    boolean verificationBadgeExistant(Personne utilisateur, NiveauBadge niveau);
    
    List<Badge> getBadgeByMembre(Personne utilisateur);

    Badge creerBadge(NiveauBadge niveau, Personne utilisateur);
}
