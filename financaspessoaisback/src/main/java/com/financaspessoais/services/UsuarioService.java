package com.financaspessoais.services;

import com.financaspessoais.model.entities.Usuario;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);

}
