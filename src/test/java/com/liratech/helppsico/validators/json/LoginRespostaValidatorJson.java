package com.liratech.helppsico.validators.json;

import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class LoginRespostaValidatorJson {
    public static void validaLoginRespostaJson(ResultActions resultado, LoginRespostaDto esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.crp").value(esperado.getCrp))
                .andExpect(jsonPath("$.dado.email").value(esperado.getEmail))
                .andExpect(jsonPath("$.dado.email").value(esperado.getToken));
    }
}
