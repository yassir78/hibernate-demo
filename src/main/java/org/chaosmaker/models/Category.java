package org.chaosmaker.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_category_generator")
    @SequenceGenerator(
            name = "custom_category_generator",
            sequenceName = "GENERATOR_SEQUENCE",
            initialValue = 15
    )
    protected Long id;

    @NotNull
    protected String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    @org.hibernate.annotations.Fetch(
            org.hibernate.annotations.FetchMode.SUBSELECT
    )
    protected Set<Item> items = new HashSet();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = this.items;
    }

}
