package com.example.faan.mongo.Auth;

import com.example.faan.mongo.Repository.UsuarioRepository;
import com.example.faan.mongo.modelos.AuthResponse;
import com.example.faan.mongo.modelos.LoginRequest;
import com.example.faan.mongo.modelos.RegisterRequest;
import com.example.faan.mongo.modelos.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://10.0.2.2:8080"})
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
        private final UsuarioRepository usuarioRepository;


    @PostMapping("/v1/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest1) {

        AuthResponse authResponse = authService.loginAdmin(loginRequest1);
        Usuario usuario = usuarioRepository.findByUsername(loginRequest1.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario not found"));

        authResponse.setUsuario(usuario);

        return ResponseEntity.ok(authResponse);

    }

    @PostMapping("/v0/signin")
    public ResponseEntity<AuthResponse> v0signIn(@RequestBody LoginRequest loginRequest) {


        return ResponseEntity.ok(authService.loginAdmin(loginRequest));

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> movilSignIn(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        Usuario usuario = usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("usuario not found"));

        authResponse.setUsuario(usuario);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping(value = "/register")
    private ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (authService.isusernameAlreadyRegistered(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("usuario " + request.getUsername() + " ya registrado", null));
            //throw new EmailAlreadyRegisteredException("El correo electrónico ya está registrado");
        }
        return ResponseEntity.ok(authService.register(request));
    }
}