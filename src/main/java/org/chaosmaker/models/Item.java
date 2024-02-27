package org.chaosmaker.models;

import jakarta.persistence.*;


@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom")
    @TableGenerator(
            name = "custom",
            table = "GENERATOR_TABLE",
            pkColumnName = "name",
            pkColumnValue = "next",
            valueColumnName = "item_test",
            initialValue = 30
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
