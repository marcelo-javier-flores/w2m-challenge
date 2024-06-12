package com.w2m.challenge.model;

import jakarta.persistence.*;

@Entity
@Table(name="spaceship")
public class Spaceship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    public Spaceship(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Spaceship() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
