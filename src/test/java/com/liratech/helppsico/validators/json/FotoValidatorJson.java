package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.FotoDto;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class FotoValidatorJson {
    public static void validaFotoJson(ResultActions resultActions, FotoDto fotoDto) throws Exception {
        resultActions
                .andExpect(jsonPath("$.data.id").value(fotoDto.getId() != null ? fotoDto.getId().toString() : null))
                .andExpect(jsonPath("$.data.fotoUrl").value(fotoDto.getFotoUrl()))
                .andExpect(jsonPath("$.data.paciente.id").value(fotoDto.getPaciente().getId().toString()));
    }
}
