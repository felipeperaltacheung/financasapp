package com.financaspessoais.services;

import com.financaspessoais.exceptions.RegraNegocioException;
import com.financaspessoais.model.entities.Usuario;
import com.financaspessoais.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImplementation implements UsuarioService{


    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplementation(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
        if(existe) {
            throw new RegraNegocioException("Ja existe um usuario cadastrado com este email");
        }
    }
}
