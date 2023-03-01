package com.financaspessoais.services;

import com.financaspessoais.exceptions.RegraNegocioException;
import com.financaspessoais.model.entities.Usuario;
import com.financaspessoais.repositories.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

    UsuarioService usuarioService;
    @MockBean
    UsuarioRepository usuarioRepository;

    @Before
    public void setup(){
        usuarioService = new UsuarioServiceImplementation(usuarioRepository);
    }

    @Test(expected = Test.None.class)
    public void deveAutenticarUsuarioComSucesso(){
        //cenario
        String email = "email@email.com";
        String senha = "senha";

        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1L).build();
        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        //acao
        Usuario result = usuarioService.autenticar(email, senha);

        //verificacao
        Assertions.assertThat(result).isNotNull();

    }

    @Test(expected = Test.None.class) //para nao lancar exception
    public void deveValidarEmail(){

        //cenario
        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        //acao
        usuarioService.validarEmail("email@email.com");
    }

    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {

        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        usuarioService.validarEmail("email@email.com");
    }

}
