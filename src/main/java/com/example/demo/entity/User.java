package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;




}
