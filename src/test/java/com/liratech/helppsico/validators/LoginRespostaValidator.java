package com.liratech.helppsico.validators;

import com.liratech.helppsico.entrypoint.dto.LoginRespostaDto;
import org.junit.jupiter.api.Assertions;

public class LoginRespostaValidator {
    public static void validaLoginRespostaDto (LoginRespostaDto esperado, LoginRespostaDto resultado){
        Assertions.assertEquals(esperado.getCrp(), resultado.getCrp());
        Assertions.assertEquals(esperado.getEmail(), resultado.getEmail());
        Assertions.assertEquals(esperado.getToken(), resultado.getToken());
    }
}
