package com.example.faan.mongo.Service;

import com.example.faan.mongo.Repository.UsuarioRepository;
import com.example.faan.mongo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Este bean se define en SecurityConfig

    public Usuario saveUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario findByUsername(String username) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
        return optionalUsuario.orElse(null); // O manejarlo de otra manera si prefieres
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
