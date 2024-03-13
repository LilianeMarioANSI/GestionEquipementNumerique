package Entite;

import Entite.Offre;
import Entite.Personne;
import Entite.StatutDemande;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-13T11:12:28")
@StaticMetamodel(Demande.class)
public class Demande_ { 

    public static volatile SingularAttribute<Demande, Personne> utilisateur;
    public static volatile SingularAttribute<Demande, Long> id;
    public static volatile SingularAttribute<Demande, Date> dateDemande;
    public static volatile SingularAttribute<Demande, StatutDemande> statut;
    public static volatile SingularAttribute<Demande, Offre> offre;

}