package Entite;

import Entite.Accessoire;
import Entite.Demande;
import Entite.EtatOffre;
import Entite.Personne;
import Entite.TypeOffre;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-13T11:12:28")
@StaticMetamodel(Offre.class)
public class Offre_ { 

    public static volatile SingularAttribute<Offre, String> Description;
    public static volatile SingularAttribute<Offre, Date> dateDebut;
    public static volatile SingularAttribute<Offre, Personne> utilisateur;
    public static volatile SingularAttribute<Offre, Date> datePublication;
    public static volatile SingularAttribute<Offre, TypeOffre> TypeOffre;
    public static volatile SingularAttribute<Offre, Long> id;
    public static volatile SingularAttribute<Offre, Date> dateFin;
    public static volatile SingularAttribute<Offre, Accessoire> accessoire;
    public static volatile ListAttribute<Offre, Demande> demandes;
    public static volatile SingularAttribute<Offre, String> intitule;
    public static volatile SingularAttribute<Offre, EtatOffre> etat;

}