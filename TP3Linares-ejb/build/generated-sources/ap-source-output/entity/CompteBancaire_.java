package entity;

import entity.OperationBancaire;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-29T08:28:02")
@StaticMetamodel(CompteBancaire.class)
public class CompteBancaire_ { 

    public static volatile CollectionAttribute<CompteBancaire, OperationBancaire> operations;
    public static volatile SingularAttribute<CompteBancaire, Double> solde;
    public static volatile SingularAttribute<CompteBancaire, Long> id;
    public static volatile SingularAttribute<CompteBancaire, String> nom;

}