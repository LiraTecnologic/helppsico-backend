package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioDto;
import com.liratech.helppsico.entrypoint.dto.psicologo.HorarioPsicologoDto;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class HorarioPsicologoValidatorJson {
    public static void validaHorariosPsicologoJson(ResultActions resultado, HorarioPsicologoDto esperado) throws Exception{
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo.id").value(esperado.getPsicologo().getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo.nome").value(esperado.getPsicologo().getNome()))
                .andExpect(jsonPath("$.dado.psicologo.crp").value(esperado.getPsicologo().getCrp()))
                .andExpect(jsonPath("$.dado.psicologo.cpf").value(esperado.getPsicologo().getCpf()))
                .andExpect(jsonPath("$.dado.psicologo.email").value(esperado.getPsicologo().getEmail()))
                .andExpect(jsonPath("$.dado.psicologo.telefone").value(esperado.getPsicologo().getTelefone()))
                .andExpect(jsonPath("$.dado.psicologo.dataNascimento").value(esperado.getPsicologo().getDataNascimento().toString()))
                .andExpect(jsonPath("$.dado.psicologo.senha").value(esperado.getPsicologo().getSenha()))
                .andExpect(jsonPath("$.dado.psicologo.genero").value(esperado.getPsicologo().getGenero().toString()))
                .andExpect(jsonPath("$.dado.psicologo.endereco").value(esperado.getPsicologo().getEnderecoAtendimento().toString()))
                .andExpect(jsonPath("$.dado.psicologo.fotoUrl").value(esperado.getPsicologo().getFotoUrl()))
                .andExpect(jsonPath("$.dado.psicologo.biografia").value(esperado.getPsicologo().getBiografia()));

        for (int i = 0; i < esperado.getHorarios().size(); i++) {
            HorarioDto esperadoHorario = esperado.getHorarios().get(i);
            resultado.andExpect(jsonPath("$.dado.horario[" + i + "].diaSemana").value(esperadoHorario.getDiaSemana()))
                    .andExpect(jsonPath("$.dado.horario[" + i + "].inicio").value(esperadoHorario.getDiaSemana()))
                    .andExpect(jsonPath("$.dado.horario[" + i + "].fim").value(esperadoHorario.getFim()))
                    .andExpect(jsonPath("$.dado.horario[" + i + "].disponivel").value(esperadoHorario.getDisponivel()));
        }
    }

    public static void validaPageResponse(ResultActions resultado, HorarioPsicologoDto esperado) throws Exception {
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
            resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.id").value(esperado.getPsicologo().getId().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.nome").value(esperado.getPsicologo().getNome()))
                    .andExpect(jsonPath("$.dado.psicologo.crp").value(esperado.getPsicologo().getCrp()))
                    .andExpect(jsonPath("$.dado.psicologo.cpf").value(esperado.getPsicologo().getCpf()))
                    .andExpect(jsonPath("$.dado.psicologo.email").value(esperado.getPsicologo().getEmail()))
                    .andExpect(jsonPath("$.dado.psicologo.telefone").value(esperado.getPsicologo().getTelefone()))
                    .andExpect(jsonPath("$.dado.psicologo.dataNascimento").value(esperado.getPsicologo().getDataNascimento().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.senha").value(esperado.getPsicologo().getSenha()))
                    .andExpect(jsonPath("$.dado.psicologo.genero").value(esperado.getPsicologo().getGenero().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.endereco").value(esperado.getPsicologo().getEnderecoAtendimento().toString()))
                    .andExpect(jsonPath("$.dado.psicologo.fotoUrl").value(esperado.getPsicologo().getFotoUrl()))
                    .andExpect(jsonPath("$.dado.psicologo.biografia").value(esperado.getPsicologo().getBiografia()));

            for (int j = 0; j < esperado.getHorarios().size(); j++) {
                HorarioDto esperadoHorario = esperado.getHorarios().get(j);
                resultado.andExpect(jsonPath("$.dado.horario[" + j + "].diaSemana").value(esperadoHorario.getDiaSemana()))
                        .andExpect(jsonPath("$.dado.horario[" + j + "].inicio").value(esperadoHorario.getDiaSemana()))
                        .andExpect(jsonPath("$.dado.horario[" + j + "].fim").value(esperadoHorario.getFim()))
                        .andExpect(jsonPath("$.dado.horario[" + j + "].disponivel").value(esperadoHorario.getDisponivel()));
            }
        }
    }
}
