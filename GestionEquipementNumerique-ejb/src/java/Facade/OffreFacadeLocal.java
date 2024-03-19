/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Facade;

import Entite.Accessoire;
import Entite.EtatOffre;
import Entite.Offre;
import Entite.TypeOffre;
import Entite.Personne;
import Entite.TypeOffre;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author loulo
 */
@Local
public interface OffreFacadeLocal {

    void create(Offre offre);

    void edit(Offre offre);

    void remove(Offre offre);

    Offre find(Object id);

    List<Offre> findAll();

    List<Offre> findRange(int[] range);

    int count();
    
    List<String> getOffresParPeriode_Json(Date dateDebut, Date dateFin);
    
    List<Offre> catalogueOffres();
    
    List<Offre> catalogueOffresFiltre(String type, String etat, String categorie);
    int getNombreOffrePublic();
    
    int getNombreOffrePublicByType(TypeOffre type);
    
    Offre creerOffre (String libelle, String description, Date datePublication, TypeOffre typeOffre, Date dateDebut, Date dateFin, Accessoire accesoires, Personne user, EtatOffre etatOffre);
    
    int getNombreMembreAvecOffreByPeriode(Date dateDebut, Date dateFin);
    
    List<String> getTop5AgenceByOffre(Date dateDebut, Date dateFin);
}
