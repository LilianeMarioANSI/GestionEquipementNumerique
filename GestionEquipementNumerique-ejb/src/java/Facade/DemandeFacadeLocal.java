/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Demande;
import java.sql.Date;
import Entite.Personne;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author loulo
 */
@Local
public interface DemandeFacadeLocal {

    void create(Demande demande);

    void edit(Demande demande);

    void remove(Demande demande);

    Demande find(Object id);

    List<Demande> findAll();

    List<Demande> findRange(int[] range);

    int count();   
    
    List<Demande> listePrêts(Personne p);

    List<Demande> listeDon(Personne p); 
    
    
    int getNombreMembreAvecDemandeByPeriode(Date dateDebut, Date dateFin);
    
    
}
