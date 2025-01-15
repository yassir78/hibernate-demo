package org.chaosmaker.models;

import jakarta.persistence.*;

@Entity
@Table(
        name = "USERS",
        uniqueConstraints =
        @UniqueConstraint(columnNames = "USERNAME")
)
// This setting tells Hibernate that it may load up to 10 User proxies if one has to be loaded
@org.hibernate.annotations.BatchSize(size = 10)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_generator")
    @SequenceGenerator(
            name = "users_generator",
            sequenceName = "USERS_GENERATOR_SEQUENCE",
            initialValue = 15
    )
    private Long id;
    private String username;


    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof User)) return false;
        User that = (User) other;
        return this.getUsername().equals(that.getUsername());
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
