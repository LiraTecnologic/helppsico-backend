package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.EnderecoDto;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class EnderecoValidatorJson {
    public static void validaEnderecoJson(ResultActions resultado, EnderecoDto esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.rua").value(esperado.getRua()))
                .andExpect(jsonPath("$.dado.numero").value(esperado.getNumero()))
                .andExpect(jsonPath("$.dado.cep").value(esperado.getCep()))
                .andExpect(jsonPath("$.dado.cidade").value(esperado.getCidade()))
                .andExpect(jsonPath("$.dado.estado").value(esperado.getEstado()))
                .andExpect(jsonPath("$.erro").doesNotExist());
    }

    public static void validaPageResponse(ResultActions resultado) throws Exception {
        resultado.andExpect(jsonPath("$.dado.length()").value(3))

                .andExpect(jsonPath("$.dado.pageable.pageNumber").value(0))
                .andExpect(jsonPath("$.dado.pageable.pageSize").value(10))

                .andExpect(jsonPath("$.dado.last").value(true))
                .andExpect(jsonPath("$.dado.first").value(true))
                .andExpect(jsonPath("$.dado.totalPages").value(1))
                .andExpect(jsonPath("$.dado.empty").value(false))

                .andExpect(jsonPath("$.dado.sort.sorted").value(true))
                .andExpect(jsonPath("$.dado.sort.unsorted").value(false))
                .andExpect(jsonPath("$.dado.sort.empty").value(false))
                .andExpect(jsonPath("$.dado.sort.orders[0].property").value("nome"))
                .andExpect(jsonPath("$.dado.sort.orders[0].direction").value("ASC"));

        for (int i = 0; i < 3; i++){
            resultado.andExpect(jsonPath("$.dado.content["+i+"].id").exists())
                    .andExpect(jsonPath("$.dado.content["+i+"].rua").value("Rua Teste"))
                    .andExpect(jsonPath("$.dado.content["+i+"].numero").value("123"))
                    .andExpect(jsonPath("$.dado.content["+i+"].cep").value("12345678"))
                    .andExpect(jsonPath("$.dado.content["+i+"].cidade").value("Maringá"))
                    .andExpect(jsonPath("$.dado.content["+i+"].estado").value("Paraná"))
                    .andExpect(jsonPath("$.erro").doesNotExist());
        }
    }
}
