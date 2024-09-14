package com.example.hmg.wishlistBE.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wishes")
public class Wish {
    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates the primary key value
    private Long id;

    @Column(nullable = false)
    private Long personId; //
    @Column(nullable = false)// Identifier for the person who made the wish
    private String wishName;
    @Column
    private String wishDescription;

    public Wish() {}

    public Wish(Long personId, String wishName, String wishDescription) {
        super();
        this.personId = personId;
        this.wishName = wishName;
        this.wishDescription = wishDescription;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public Long getId() { return this.id; }
    public Long getPersonId() { return this.personId; }
    public String getWishName() { return this.wishName; }
    public String getWishDescription() { return this.wishDescription; }
}