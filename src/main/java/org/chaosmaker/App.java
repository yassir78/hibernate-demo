package org.chaosmaker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.transaction.Transactional;
import org.chaosmaker.helpers.EntityFactoryBuilder;
import org.chaosmaker.models.Bid;
import org.chaosmaker.models.Item;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import java.util.Objects;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = EntityFactoryBuilder.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        PersistenceUnitUtil persistenceUnitUtil = entityManagerFactory.getPersistenceUnitUtil();

        transaction.rollback();
        try {
            transaction.begin();
            // uses the default profile
            Item item = em.find(Item.class, 15);
            System.out.println("*******************************************");
            // enables the first profile fetch type
            em.unwrap(Session.class).enableFetchProfile(Item.PROFILE_JOIN_SELLER);
            item = em.find(Item.class, 15);
            System.out.println("*******************************************");
            em.clear();
            System.out.println("*******************************************");
            // enables the second profile fetch type
            em.unwrap(Session.class).enableFetchProfile(Item.PROFILE_JOIN_BIDS);
            item = em.find(Item.class, 15);
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            if (Objects.nonNull(em) && em.isOpen())
                em.close();
        }

        // interact with our database
    }

}
