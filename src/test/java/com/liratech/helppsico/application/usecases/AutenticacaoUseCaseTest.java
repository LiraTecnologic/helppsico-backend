package com.liratech.helppsico.application.usecases;

import com.liratech.helppsico.application.exceptions.token.TokenInvalidoException;
import com.liratech.helppsico.application.gateways.TokenGateway;
import com.liratech.helppsico.builders.PacienteBuilder;
import com.liratech.helppsico.builders.PsicologoBuilder;
import com.liratech.helppsico.domain.Paciente;
import com.liratech.helppsico.domain.Psicologo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.liratech.helppsico.application.usecases.AutenticacaoUseCase.MENSAGEM_GERACAO_TOKEN_INVALIDA;
import static com.liratech.helppsico.application.usecases.AutenticacaoUseCase.MENSAGEM_TOKEN_NULO;

@ExtendWith(MockitoExtension.class)
public class AutenticacaoUseCaseTest {
    @Mock
    private TokenGateway gateway;

    @InjectMocks
    private AutenticacaoUseCase useCase;

    @Test
    void testeGerarTokenPaciente(){
        Paciente paciente = PacienteBuilder.criarPaciente();
        String tokenEsperado = "token-gerado";
        Mockito.when(gateway.gerarTokenPaciente(Mockito.any())).thenReturn(tokenEsperado);

        String resultado = useCase.gerarTokenPaciente(paciente);

        Assertions.assertEquals(tokenEsperado, resultado);
    }

    @Test
    void testeGerarTokenPacienteInvalidoException(){
        Paciente paciente = PacienteBuilder.criarPaciente();
        Mockito.when(gateway.gerarTokenPaciente(Mockito.any())).thenReturn(null);

        TokenInvalidoException exception = Assertions.assertThrows(
                TokenInvalidoException.class, () -> useCase.gerarTokenPaciente(paciente)
        );

        Assertions.assertEquals(MENSAGEM_GERACAO_TOKEN_INVALIDA, exception.getMessage());
    }

    @Test
    void testeGerarTokenPsicologo(){
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();
        String tokenEsperado = "token-gerado";
        Mockito.when(gateway.gerarTokenPsicologo(Mockito.any())).thenReturn(tokenEsperado);

        String resultado = useCase.gerarTokenPsicologo(psicologo);

        Assertions.assertEquals(tokenEsperado, resultado);
    }

    @Test
    void testeGerarTokenPsicologoInvalidoException(){
        Psicologo psicologo = PsicologoBuilder.criarPsicologo();
        Mockito.when(gateway.gerarTokenPsicologo(Mockito.any())).thenReturn(null);

        TokenInvalidoException exception = Assertions.assertThrows(
                TokenInvalidoException.class, () -> useCase.gerarTokenPsicologo(psicologo)
        );

        Assertions.assertEquals(MENSAGEM_GERACAO_TOKEN_INVALIDA, exception.getMessage());
    }

    @Test
    void testeValidarToken(){
        String tokenValido = "token-valido";
        String tipoEsperado = "PACIENTE";

        Mockito.when(gateway.validarToken(Mockito.any())).thenReturn(tipoEsperado);

        String tipoRecebido = useCase.validarToken(tokenValido);
        Assertions.assertEquals(tipoEsperado, tipoRecebido);
    }

    @Test
    void testeValidarTokenInvalidoException(){

        Mockito.when(gateway.validarToken(Mockito.any())).thenReturn(null);

        TokenInvalidoException exception = Assertions.assertThrows(
                TokenInvalidoException.class, () -> useCase.validarToken(null)
        );

        Assertions.assertEquals(MENSAGEM_TOKEN_NULO, exception.getMessage());
    }
}
