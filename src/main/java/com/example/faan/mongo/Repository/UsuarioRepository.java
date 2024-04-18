package com.example.faan.mongo.Repository;

import com.example.faan.mongo.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByUsername(String username);

    // Puedes agregar métodos de consulta adicionales según tus necesidades

    Usuario findByDniAndUsername(String dni, String username);
    Usuario findByVerificationToken(String verificationToken);


}
