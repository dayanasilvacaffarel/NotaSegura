package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_policies",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_policy")
    )
    private Set<Policy> policies = new HashSet<>();

    @OneToMany(mappedBy = "products",fetch =FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Image> images =new HashSet<>();

    @OneToMany(mappedBy = "products",fetch =FetchType.LAZY)
    private Set<Reservation> reservations =new HashSet<>();

    @OneToMany(mappedBy = "scores",fetch = FetchType.LAZY)
    private Set<Score> scores =new HashSet<>();
}
