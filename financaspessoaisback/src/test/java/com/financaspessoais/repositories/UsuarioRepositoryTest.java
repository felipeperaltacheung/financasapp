package com.financaspessoais.repositories;

import com.financaspessoais.model.entities.Usuario;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void deveVerificarAexistenciaDeUmEmail(){

        //cenario
        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
        testEntityManager.persist(usuario);
        
        
        //acao
        boolean result = usuarioRepository.existsByEmail("usuario@email.com");


        //verificacao
        Assertions.assertThat(result).isTrue();

    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail(){


        usuarioRepository.deleteAll();

        boolean result = usuarioRepository.existsByEmail("usuario@email.com");

        Assertions.assertThat(result).isFalse();

    }

}
