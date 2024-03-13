/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Facade;

import Entite.Badge;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author loulo
 */
@Stateless
public class BadgeFacade extends AbstractFacade<Badge> implements BadgeFacadeLocal {

    @PersistenceContext(unitName = "GestionEquipementNumerique-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BadgeFacade() {
        super(Badge.class);
    }
    
}
