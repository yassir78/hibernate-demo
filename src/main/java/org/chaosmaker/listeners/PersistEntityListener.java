package org.chaosmaker.listeners;

import jakarta.persistence.PostPersist;

public class PersistEntityListener {

    @PostPersist
    public void notify(Object entityInstance) {
        System.out.printf(" Entity instance %s", entityInstance);
    }
}
