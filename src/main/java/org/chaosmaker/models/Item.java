package org.chaosmaker.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(
        name = "ITEM"
)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom")
    @SequenceGenerator(
            name = "custom",
            sequenceName = "GENERATOR_SEQUENCE",
            initialValue = 15
    )
    protected Long id;
    protected String name;
    @ManyToOne(fetch = FetchType.LAZY)
    protected User seller;
    @ManyToMany(mappedBy = "items")
    protected Set<Category> categories = new HashSet();
    @OneToMany(mappedBy = "item")
    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.EXTRA
    )
    protected Set<Bid> bids = new HashSet<>();

    public User getSeller() {
        return seller;
    }


    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
