package Entite;

import Entite.Accessoire;
import Entite.Agence;
import Entite.Badge;
import Entite.Demande;
import Entite.Offre;
import Entite.Souhait;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-13T11:12:28")
@StaticMetamodel(Personne.class)
public class Personne_ { 

    public static volatile ListAttribute<Personne, Offre> offres;
    public static volatile SingularAttribute<Personne, String> telephone;
    public static volatile SingularAttribute<Personne, String> login;
    public static volatile ListAttribute<Personne, Demande> demandes;
    public static volatile SingularAttribute<Personne, String> nom;
    public static volatile SingularAttribute<Personne, Agence> agence;
    public static volatile ListAttribute<Personne, Badge> badges;
    public static volatile ListAttribute<Personne, Accessoire> accessoires;
    public static volatile SingularAttribute<Personne, String> mdp;
    public static volatile SingularAttribute<Personne, Long> id;
    public static volatile SingularAttribute<Personne, String> bureau;
    public static volatile SingularAttribute<Personne, String> prenom;
    public static volatile ListAttribute<Personne, Souhait> souhaits;

}