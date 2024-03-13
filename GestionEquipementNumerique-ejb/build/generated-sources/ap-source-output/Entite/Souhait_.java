package Entite;

import Entite.Personne;
import Entite.TypeAccessoire;
import Entite.TypeSouhait;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-13T13:23:09")
@StaticMetamodel(Souhait.class)
public class Souhait_ { 

    public static volatile SingularAttribute<Souhait, Personne> utilisateur;
    public static volatile SingularAttribute<Souhait, Date> datePublication;
    public static volatile SingularAttribute<Souhait, TypeAccessoire> TypeAccessoire;
    public static volatile SingularAttribute<Souhait, Long> id;
    public static volatile SingularAttribute<Souhait, TypeSouhait> typeSouhait;

}