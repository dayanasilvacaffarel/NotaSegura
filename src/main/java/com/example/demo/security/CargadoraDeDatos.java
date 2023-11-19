package com.example.demo.security;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.AppUserRole;
import com.example.demo.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargadoraDeDatos implements ApplicationRunner {

    private AppUserService appUserService;

    @Autowired
    public CargadoraDeDatos(AppUserService appUsuarioService) {
        this.appUserService = appUsuarioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String password= "password";
        String passCifrada= passwordEncoder.encode(password);
        System.out.println("Contraseña cifrada: " + passCifrada + "Contraseña: " + password);
        appUserService.guardarUsuario(new AppUser("Grupo3", "Camada13", "grupo3c13@gmail.com",passCifrada, true, "Uruguay", AppUserRole.ROLE_USER ));

    }
}
