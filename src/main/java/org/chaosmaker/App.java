package org.chaosmaker;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.chaosmaker.helpers.EntityFactoryBuilder;
import org.chaosmaker.interceptors.AuditLogInterceptor;
import org.chaosmaker.models.AuctionType;
import org.chaosmaker.models.Bid;
import org.chaosmaker.models.Item;
import org.chaosmaker.models.User;
import org.hibernate.Session;

import java.math.BigDecimal;
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

            // select b from Bid b where b.amount > 100
            Root<Bid> b = criteria.from(Bid.class);
            criteria.select(b).where(
                    cb.gt(
                            b.<BigDecimal>get("amount"),
                            new BigDecimal("100")
                    )
            );
            // select u from User u where u.username in ('johndoe', 'janeroe')
            Root<User> u = criteria.from(User.class);
            criteria.select(u).where(
                    cb.in(u.<String>get("username"))
                            .value("johndoe")
                            .value("janeroe")
            );

            // select i from Item i
            // where i.auctionType = org.chaosmaker.models.AuctionType
            Root<Item> i = criteria.from(Item.class);
            criteria.select(i).where(
                    cb.equal(
                            i.<AuctionType>get("auctionType"),
                            AuctionType.HIGHEST_BID
                    )
            );
            // select i from Item i where i.buyNowPrice is null
            Root<Item> i1 = criteria.from(Item.class);
            criteria.select(i).where(
                    cb.isNull(i.get("buyNowPrice"))
            );
            // select i from Item i where i.buyNowPrice is not null
            Root<Item> i2 = criteria.from(Item.class);
            criteria.select(i).where(
                    cb.isNotNull(i.get("buyNowPrice"))
            );
            // select u from User u where u.username like 'john%'
            Root<User> u1 = criteria.from(User.class);
            criteria.select(u).where(
                    cb.like(u.get("username"), "john%")
            );
            // select u from User u where u.username not like 'john%'
            Root<User> u2 = criteria.from(User.class);
            criteria.select(u).where(
                    cb.like(u.get("username"), "john%").not()
            );
            // select u from User u where u.username like '%oe%'
            Root<User> u3 = criteria.from(User.class);
            criteria.select(u).where(
                    cb.like(u.<String>get("username"), "%oe%")
            );
            // select i from Item i
            // where i.name like 'Name\_with\_underscores' escape :escapeChar
            //query.setParameter("escapeChar", "\\");
            Root<Item> i3 = criteria.from(Item.class);
            criteria.select(i).where(
                    cb.like(i.get("name"), "Name\\_with\\_underscores", '\\')
            );

            // select b from Bid b where (b.amount / 2) - 0.5 > 49
            Root<Bid> b1 = criteria.from(Bid.class);
            criteria.select(b).where(
                    cb.gt(
                            cb.diff(
                                    cb.quot(b.<BigDecimal>get("amount"), 2),
                                    0.5
                            ),
                            49
                    )
            );
            // select i from Item i
            // where (i.name like 'Fo%' and i.buyNowPrice is not null)
            // or i.name = 'Bar'/
            Root<Item> i4 = criteria.from(Item.class);
            Predicate predicate = cb.and(
                    cb.like(i.<String>get("name"), "Fo%"),
                    cb.isNotNull(i.get("buyNowPrice"))
            );

            predicate = cb.or(
                    predicate,
                    cb.equal(i.<String>get("name"), "Bar")
            );

            criteria.select(i).where(predicate);


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
