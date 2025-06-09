package com.liratech.helppsico.application.usecases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class CriptografiaUseCaseTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CriptografiaUseCase criptografiaUseCase;

    private String senhaOriginal;
    private String senhaCriptografada;

    @BeforeEach
    void inicializar() {
        senhaOriginal = "minhaSenhaSegura";
        senhaCriptografada = "$2a$10$EXEMPLODeHASHcriptografado";
    }

    @Test
    void testeCriptografarSenhaComSucesso() {
        Mockito.when(passwordEncoder.encode(senhaOriginal)).thenReturn(senhaCriptografada);

        String resultado = criptografiaUseCase.criptografar(senhaOriginal);

        Assertions.assertEquals(senhaCriptografada, resultado);
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(senhaOriginal);
    }

    @Test
    void testeVerificarSenhasIguais() {
        Mockito.when(passwordEncoder.matches(senhaOriginal, senhaCriptografada)).thenReturn(true);

        boolean resultado = criptografiaUseCase.validarSenha(senhaOriginal, senhaCriptografada);

        Assertions.assertTrue(resultado);
        Mockito.verify(passwordEncoder, Mockito.times(1)).matches(senhaOriginal, senhaCriptografada);
    }

    @Test
    void testeRetornarFalseQuandoSenhasNaoForemIguais() {
        Mockito.when(passwordEncoder.matches(senhaOriginal, senhaCriptografada)).thenReturn(false);

        boolean resultado = criptografiaUseCase.validarSenha(senhaOriginal, senhaCriptografada);

        Assertions.assertFalse(resultado);
    }

    @Test
    void testeLancarExcecaoAoCriptografarSenhaNula() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            criptografiaUseCase.criptografar(null);
        });
    }

    @Test
    void testeLancarExcecaoAoVerificarSenhaNula() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            criptografiaUseCase.validarSenha(null, senhaCriptografada);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            criptografiaUseCase.validarSenha(senhaOriginal, null);
        });
    }
}