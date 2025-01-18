package org.chaosmaker;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.chaosmaker.helpers.EntityFactoryBuilder;
import org.chaosmaker.interceptors.AuditLogInterceptor;
import org.chaosmaker.models.Item;
import org.hibernate.Session;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = EntityFactoryBuilder.getEntityManagerFactory();
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(
                org.hibernate.cfg.AvailableSettings.SESSION_SCOPED_INTERCEPTOR,
                AuditLogInterceptor.class.getName()
        );
        EntityManager em = entityManagerFactory.createEntityManager(properties);
        EntityTransaction transaction = em.getTransaction();
        transaction.rollback();
        try {
            transaction.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery criteria = cb.createQuery(Item.class);

            ParameterExpression<String> itemNameParameter =
                    cb.parameter(String.class);

            Root<Item> i = criteria.from(Item.class);
            Query query = em.createQuery(
                    criteria.select(i).where(
                            cb.equal(
                                    i.get("name"),
                                    itemNameParameter
                            )
                    )
            ).setParameter(itemNameParameter, "value");

            query.setFirstResult(40).setMaxResults(10);

            List<Item> items = query.getResultList();



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
