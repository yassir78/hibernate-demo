package org.chaosmaker.models;

import jakarta.persistence.*;
import org.chaosmaker.listeners.PersistEntityListener;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

/*
When using LazyCollectionOption.EXTRA:
** The collection is initially uninitialized.
** Basic operations like size() and contains() can be performed without loading the entire collection5.
** Elements are fetched individually from the database as needed, rather than loading the entire collection at once.
 */
@Entity
@Table(
        name = "ITEM"
)
// An entity graph is a declaration of entity nodes and attributes
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "ItemSeller",
                attributeNodes = {
                        @NamedAttributeNode("seller")
                }
        )
})
@EntityListeners(
        PersistEntityListener.class
)
public class Item {
    public static final String PROFILE_JOIN_SELLER = "JoinSeller";
    public static final String PROFILE_JOIN_BIDS = "JoinBids";
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
    protected Set<Bid> bids;


    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @Column(name = "FILENAME")
    protected Set<String> images = new HashSet();

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

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
