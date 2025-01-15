package org.chaosmaker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.transaction.Transactional;
import org.chaosmaker.helpers.EntityFactoryBuilder;
import org.chaosmaker.models.Item;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import java.util.Objects;

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

            Item item = em.find(Item.class, 15);


            // assertFalse(Hibernate.isInitialized(item.getSeller()));

            // initializes the proxy's data.
            // Hibernate.initialize(item);


//            boolean isInPersistenceContext = em.contains(item);
//
//            Object identifier = persistenceUnitUtil.getIdentifier(item);
//            System.out.printf("identifier %s", identifier);

            transaction.commit();
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
