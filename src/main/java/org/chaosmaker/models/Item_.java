package org.chaosmaker.models;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.math.BigDecimal;

@StaticMetamodel(Item.class)
public class Item_ {
    public static volatile SingularAttribute<Item, Long> id;
    public static volatile SingularAttribute<Item, String> name;
    public static volatile SingularAttribute<Item, User> seller;
    public static volatile SetAttribute<Item, Category> items;
    public static volatile SetAttribute<Item, Bid> bids;
    public static volatile SingularAttribute<Item, BigDecimal> totalCost;
}
