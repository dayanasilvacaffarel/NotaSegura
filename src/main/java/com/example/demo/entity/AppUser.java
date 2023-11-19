package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import lombok.*;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Getter
@Setter

public class AppUser implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean active;

    @Column
    private String address;

    private AppUserRole appUserRole;

    public AppUser() {
    }

    public AppUser(String name, String lastName, String email, String password, Boolean active, String address, AppUserRole appUserRole) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.address = address;
        this.appUserRole = appUserRole;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority=new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties(value = {"user"})
//    private Set<Reservation> reservations = new HashSet<>();
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "roles", referencedColumnName = "id")
//    private Role role;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties(value = {"user"})
//    private Set<Score> scores = new HashSet<>();
}



