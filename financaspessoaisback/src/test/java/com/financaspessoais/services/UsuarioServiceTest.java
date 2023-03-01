package com.financaspessoais.services;

import com.financaspessoais.exceptions.ErroAutenticacao;
import com.financaspessoais.exceptions.RegraNegocioException;
import com.financaspessoais.model.entities.Usuario;
import com.financaspessoais.repositories.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

    @Test
    public void deveLancarErroQuandoASenhaNaoBater(){

        String senha = "senha";

        Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).id(1L).build();

        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

        Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticar("email@email.com", "123"));
        Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha invalida");

    }

    @Test
    public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComEmailInformado(){
        //cenario
        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        //acao
        Throwable exception = Assertions.catchThrowable(() -> usuarioService.autenticar("email@email.com", "senha"));
        Assertions.assertThat(exception)
                .isInstanceOf(ErroAutenticacao.class)
                .hasMessage("usuario nao encontrado para o email informado.");

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
