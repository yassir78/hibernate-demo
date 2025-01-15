package org.chaosmaker;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.chaosmaker.helpers.EntityFactoryBuilder;
import org.chaosmaker.models.Bid;
import org.chaosmaker.models.Item;
import org.chaosmaker.models.Item_;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import java.util.HashMap;
import java.util.Map;
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
            EntityGraph<Item> itemGraph = em.createEntityGraph(Item.class);
            itemGraph.addAttributeNodes(Item_.seller);

            Map<String, Object> properties = new HashMap<>();
            properties.put("javax.persistence.loadgraph", itemGraph);
            Item item = em.find(Item.class, 15, properties);
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
