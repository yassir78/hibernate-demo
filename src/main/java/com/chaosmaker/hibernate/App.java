package com.chaosmaker.hibernate;

import com.chaosmaker.hibernate.models.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.metamodel.ManagedType;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.persistence.metamodel.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = createEntityManagerFactory("UP_CAVEAT_EMPTOR");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Item item = new Item();
            item.setName("sss");
            item.setInitialPrice(BigDecimal.ZERO);
            LocalDate localdate = LocalDate.now().plusDays(1);
            item.setAuctionEnd(Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            em.persist(item);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        System.out.println("Hello World!");
    }
}
