package com.liratech.helppsico.infrastructure.security;

import com.liratech.helppsico.application.exceptions.token.TokenExpiradoException;
import com.liratech.helppsico.application.exceptions.token.TokenInvalidoException;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import com.liratech.helppsico.validators.ClaimsValidator;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import static com.liratech.helppsico.infrastructure.security.TokenDataProvider.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TokenDataProviderTest {

    @Mock
    private JwtParserBuilder parserBuilderMock;

    @Mock
    private JwtParser parserMock;

    @Mock
    private JwtBuilder builderMock;

    @Mock
    private Jws<Claims> jwsMock;

    @InjectMocks
    private TokenDataProvider dataProvider;

    private final String CHAVE_SECRETA = "wO4xQjNq3FvJk9pLm2R5sP8uVbY0zXc1D7hG6tQe9iWa4nCf7yZd5vB";

    @BeforeEach
    void inicializar(){
        Mockito.when(parserBuilderMock.setSigningKey(Mockito.any(Key.class))).thenReturn(parserBuilderMock);
        Mockito.when(parserBuilderMock.build()).thenReturn(parserMock);
    }

    @Test
    void testeGerarTokenPaciente(){
        Paciente paciente = PacienteBuilder.criarPaciente();

        Mockito.when(builderMock.setClaims(Mockito.anyMap())).thenReturn(builderMock);
        Mockito.when(builderMock.setSubject(Mockito.anyString())).thenReturn(builderMock);
        Mockito.when(builderMock.setIssuedAt(Mockito.any(Date.class))).thenReturn(builderMock);
        Mockito.when(builderMock.setExpiration(Mockito.any(Date.class))).thenReturn(builderMock);
        Mockito.when(builderMock.signWith(Mockito.any(Key.class), Mockito.any(SignatureAlgorithm.class)))
                .thenReturn(builderMock);
        Mockito.when(builderMock.compact()).thenReturn("token-gerado");

        String token = dataProvider.gerarTokenPaciente(paciente);
        Assertions.assertNotNull(token);

        ArgumentCaptor<Map<String, Object>> claimsCaptor = ArgumentCaptor.forClass(Map.class);
        Mockito.verify(builderMock).setClaims(claimsCaptor.capture());

        Map<String, Object> claims = claimsCaptor.getValue();

        ClaimsValidator.validaClaimPaciente(paciente, claims);
    }

    @Test
    void testeGerarTokenPsicologo(){
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();

        Mockito.when(builderMock.setClaims(Mockito.anyMap())).thenReturn(builderMock);
        Mockito.when(builderMock.setSubject(Mockito.anyString())).thenReturn(builderMock);
        Mockito.when(builderMock.setIssuedAt(Mockito.any(Date.class))).thenReturn(builderMock);
        Mockito.when(builderMock.setExpiration(Mockito.any(Date.class))).thenReturn(builderMock);
        Mockito.when(builderMock.signWith(Mockito.any(Key.class), Mockito.any(SignatureAlgorithm.class)))
                .thenReturn(builderMock);
        Mockito.when(builderMock.compact()).thenReturn("token-gerado");

        String token = dataProvider.gerarTokenPsicologo(psicologo);
        Assertions.assertNotNull(token);

        ArgumentCaptor<Map<String, Object>> claimsCaptor = ArgumentCaptor.forClass(Map.class);
        Mockito.verify(builderMock).setClaims(claimsCaptor.capture());

        Map<String, Object> claims = claimsCaptor.getValue();

        ClaimsValidator.validaClaimPsicologo(psicologo, claims);
    }

    @Test
    void testeValidarToken(){
        Claims claimsMock = mock(Claims.class);

        Mockito.when(claimsMock.getExpiration()).thenReturn(new Date(System.currentTimeMillis() + 10000));
        Mockito.when(claimsMock.get("tipo", String.class)).thenReturn("PACIENTE");
        Mockito.when(parserMock.parseClaimsJws("token-valido")).thenReturn(jwsMock);
        Mockito.when(jwsMock.getBody()).thenReturn(claimsMock);

        String tipoTeste = dataProvider.validarToken("token-valido");

        Assertions.assertEquals("PSICOLOGO", tipoTeste);

    }

    @Test
    void testeTokenInvalidoException(){
        Mockito.when(parserMock.parseClaimsJws("token-invalido")).thenThrow(new MalformedJwtException("Token inválido"));

        TokenInvalidoException exception = Assertions.assertThrows(TokenInvalidoException.class, () -> {
            dataProvider.validarToken("token-invalido");
        });

        Assertions.assertEquals(ERRO_TOKEN_INVALIDO, exception.getMessage());
    }

    @Test
    void testeTokenTipoNaoEncontradoException(){
        Claims claimsMock = mock(Claims.class);

        Mockito.when(claimsMock.getExpiration()).thenReturn(new Date(System.currentTimeMillis() + 10000));
        Mockito.when(claimsMock.get("tipo", String.class)).thenReturn(null);
        Mockito.when(parserMock.parseClaimsJws("token-sem-tipo")).thenReturn(jwsMock);
        Mockito.when(jwsMock.getBody()).thenReturn(claimsMock);

        TokenInvalidoException exception = Assertions.assertThrows(TokenInvalidoException.class, () -> {
            dataProvider.validarToken("token-sem-tipo");
        });

        Assertions.assertEquals(ERRO_TOKEN_TIPO_NAO_ENCONTRADO, exception.getMessage());
    }

    @Test
    void testeTokenExpiradoException(){
        Claims claimsMock = mock(Claims.class);
        Mockito.when(claimsMock.getExpiration()).thenReturn(new Date(System.currentTimeMillis() - 10000));
        Mockito.when(parserMock.parseClaimsJws("token-expirado")).thenReturn(jwsMock);
        Mockito.when(jwsMock.getBody()).thenReturn(claimsMock);

        TokenExpiradoException exception = Assertions.assertThrows(TokenExpiradoException.class, () -> {
            dataProvider.validarToken("token-expirado");
        });

        Assertions.assertEquals(ERRO_TOKEN_EXPIRADO, exception.getMessage());
    }

}