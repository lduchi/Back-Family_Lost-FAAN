package com.example.faan.mongo.Auth;

import com.example.faan.mongo.Repository.UsuarioRepository;
import com.example.faan.mongo.jwt.JwtService;
import com.example.faan.mongo.modelos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        UserDetails userDetails=usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow();
        String token =jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
        .build();
    }

    public AuthResponse loginAdmin(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails userDetails = usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow();
        Usuario usuario = usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow();

        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            throw new RuntimeException("User is not an admin");
        }

        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .usuario(usuario)
                .build();
    }
    public AuthResponse register(RegisterRequest request){
        Usuario usuario=Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .dni(request.getDni())
                .role(request.getRole())//desde el Enum
                .build();
        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }

    public boolean isusernameAlreadyRegistered(String username) {
        // Realiza la consulta en la base de datos para verificar si existe un usuario con el mismo correo electrónico
        Optional<Usuario> userOptional = usuarioRepository.findByUsername(username);
        return userOptional.isPresent();
    }

    public String encriptaPass(String pass){
        return passwordEncoder.encode(pass);
    }


}
