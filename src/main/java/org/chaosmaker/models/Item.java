package org.chaosmaker.models;

import jakarta.persistence.*;


@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom")
    @SequenceGenerator(
            name = "custom",
            sequenceName = "GENERATOR_SEQUENCE",
            initialValue = 15
    )
    private Long id;
    private String name;

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
