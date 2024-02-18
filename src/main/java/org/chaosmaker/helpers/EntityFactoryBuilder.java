package org.chaosmaker.helpers;

import jakarta.persistence.EntityManagerFactory;

import java.util.Objects;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class EntityFactoryBuilder {

    private static EntityManagerFactory entityManagerFactory;
    private static final String PERSISTENCE_UNIT_NAME = "UP_SHOPIX";
    private EntityFactoryBuilder(){}

    public static EntityManagerFactory getEntityManagerFactory(){
        if(Objects.isNull(entityManagerFactory)){
            entityManagerFactory = createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }
}
