package com.financaspessoais.services;

import com.financaspessoais.exceptions.RegraNegocioException;
import com.financaspessoais.model.entities.Usuario;
import com.financaspessoais.repositories.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test(expected = Test.None.class) //para nao lancar exception
    public void deveValidarEmail(){

        //cenario
        usuarioRepository.deleteAll();

        //acao
        usuarioService.validarEmail("email@email.com");


    }

    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {

        Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
        usuarioRepository.save(usuario);

        usuarioService.validarEmail("email@email.com");
    }

}
