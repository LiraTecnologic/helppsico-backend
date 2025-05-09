package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.documento.SolicitacaoDocumentoDto;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class SolicitacaoDocumentoValidatorJson {
    public static void validaSolicitacaoJson(ResultActions resultado, SolicitacaoDocumentoDto esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo").value(esperado.getPsicologo().toString()))
                .andExpect(jsonPath("$.dado.paciente").value(esperado.getPaciente().toString()))
                .andExpect(jsonPath("$.dado.tipoDocumento").value(esperado.getTipoDocumento()))
                .andExpect(jsonPath("$.erro").doesNotExist());
    }
}
