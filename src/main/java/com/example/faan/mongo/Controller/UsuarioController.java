package com.example.faan.mongo.Controller;

import com.example.faan.mongo.Service.UsuarioService;
import com.example.faan.mongo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        // Verificar si el usuario ya existe
        if (usuarioService.findByUsername(usuario.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }

        // Codificar la contraseña antes de guardarla en la base de datos
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Guardar el usuario en la base de datos
        usuarioService.saveUsuario(usuario);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }


    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        Usuario usuarioEncontrado = usuarioService.findByUsername(usuario.getUsername());
        if (usuarioEncontrado != null && passwordEncoder.matches(usuario.getPassword(), usuarioEncontrado.getPassword())) {
            return usuarioEncontrado;
        }
        return null; // Cambiar esto según el manejo de errores que desees
    }
}
