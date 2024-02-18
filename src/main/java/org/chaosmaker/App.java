package org.chaosmaker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.chaosmaker.helpers.EntityFactoryBuilder;
import org.chaosmaker.models.Message;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = EntityFactoryBuilder.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            // logic
            Message message = new Message();
            message.setText("test text 2");
            em.persist(message);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }

        // interact with our database
    }
}
