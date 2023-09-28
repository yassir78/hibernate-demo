package com.chaosmaker.hibernate.helpers;

import jakarta.persistence.EntityManagerFactory;

import java.util.Objects;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class EntityFactoryBuilder {
    static EntityManagerFactory entityManagerFactory;

    private EntityFactoryBuilder() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (Objects.isNull(entityManagerFactory)) {
            entityManagerFactory = createEntityManagerFactory("UP_CAVEAT_EMPTOR");
        }
        return entityManagerFactory;
    }
}
