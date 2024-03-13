package Entite;

import Entite.NiveauBadge;
import Entite.Personne;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-13T13:23:09")
@StaticMetamodel(Badge.class)
public class Badge_ { 

    public static volatile SingularAttribute<Badge, Personne> utilisateur;
    public static volatile SingularAttribute<Badge, Long> id;
    public static volatile SingularAttribute<Badge, NiveauBadge> niveau;

}