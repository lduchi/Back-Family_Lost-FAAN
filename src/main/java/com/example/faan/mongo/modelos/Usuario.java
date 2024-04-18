package com.example.faan.mongo.modelos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class Usuario implements UserDetails {
    @Id
    private String id;
    private String verificationToken;
    private String nombre;
    private String username;
    private String password;
    private String dni;
    private String apellido;
    private String direccion;
    private String telefono;

    private Role role;

    public Usuario(String nombre, String username, String password, String dni, String apellido, String direccion, String telefono, Role role) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.dni = dni;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.role = role;
    }

    // Getters y setters

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
