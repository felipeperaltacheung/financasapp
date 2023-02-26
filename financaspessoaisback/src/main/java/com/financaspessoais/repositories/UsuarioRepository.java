package com.financaspessoais.repositories;

import com.financaspessoais.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Aplicar um query method p/ buscar um usuario por email
    //optinal se exister vai trazer o usuario, se nao um optional vazio
    //Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

}
