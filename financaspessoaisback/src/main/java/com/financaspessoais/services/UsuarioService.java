package com.financaspessoais.services;

import com.financaspessoais.model.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);

}
