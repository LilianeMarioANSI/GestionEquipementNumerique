package Entite;

import Entite.EtatAccessoire;
import Entite.Offre;
import Entite.Personne;
import Entite.TypeAccessoire;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-13T13:23:09")
@StaticMetamodel(Accessoire.class)
public class Accessoire_ { 

    public static volatile ListAttribute<Accessoire, Personne> utilisateurs;
    public static volatile SingularAttribute<Accessoire, Boolean> disponibilite;
    public static volatile SingularAttribute<Accessoire, TypeAccessoire> TypeAccessoire;
    public static volatile SingularAttribute<Accessoire, String> description;
    public static volatile SingularAttribute<Accessoire, Long> id;
    public static volatile SingularAttribute<Accessoire, String> designation;
    public static volatile SingularAttribute<Accessoire, EtatAccessoire> etat;
    public static volatile SingularAttribute<Accessoire, Offre> offre;

}