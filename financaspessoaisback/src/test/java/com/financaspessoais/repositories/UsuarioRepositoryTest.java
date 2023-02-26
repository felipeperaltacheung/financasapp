package com.financaspessoais.repositories;

import com.financaspessoais.model.entities.Usuario;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void deveVerificarAexistenciaDeUmEmail(){

        //cenario
        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
        usuarioRepository.save(usuario);
        
        
        //acao
        boolean result = usuarioRepository.existsByEmail("usuario@email.com");


        //verificacao
        Assertions.assertThat(result).isTrue();

    }

}
