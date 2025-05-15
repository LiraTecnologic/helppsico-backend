package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ValidacaoCrpValidatorJson {
    public static void verificaValidacaoJson(ResultActions resultado, SolicitacaoDocumentoDto esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo").value(esperado.getPsicologo().toString()))
                .andExpect(jsonPath("$.dado.crp").value(esperado.getPaciente().toString()))
                .andExpect(jsonPath("$.dado.motivoReprova").value(esperado.getPaciente().toString()))
                .andExpect(jsonPath("$.erro").doesNotExist());
    }
}
