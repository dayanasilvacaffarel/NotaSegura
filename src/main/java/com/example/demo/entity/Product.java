package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Boolean active;

    @Column
    private Double price;

    @Column
    private String brand;

    @Column
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories", referencedColumnName = "id")
    private Category categories;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_policies",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "policy_id")
    )
    private Set<Policy> policies = new HashSet<>();

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Score> scores = new HashSet<>();


}
