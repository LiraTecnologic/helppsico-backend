package com.liratech.helppsico.validators.json;

import com.liratech.helppsico.entrypoint.dto.consulta.ConsultaDto;
import com.liratech.helppsico.infrastructure.repositories.entities.ConsultaEntity;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ConsultaValidatorJson {
    public static void validaConsultaJson(ResultActions resultado, ConsultaDto esperado) throws Exception {
        resultado.andExpect(jsonPath("$.dado.id").value(esperado.getId().toString()))
                .andExpect(jsonPath("$.dado.psicologo").value(esperado.getPsicologo().toString()))
                .andExpect(jsonPath("$.dado.paciente").value(esperado.getPaciente().toString()))
                .andExpect(jsonPath("$.dado.valor").value(esperado.getValor().toString()))
                .andExpect(jsonPath("$.dado.data").value(esperado.getData().toString()))
                .andExpect(jsonPath("$.dado.horario").value(esperado.getHorario().toString()))
                .andExpect(jsonPath("$.dado.endereco").value(esperado.getEndereco().toString()))
                .andExpect(jsonPath("$.dado.finalizada").value(esperado.getFinalizada().toString()))
                .andExpect(jsonPath("$.erro").doesNotExist());
    }

    public static void validaPageConsultas(ResultActions resultado, Page<ConsultaEntity> esperado) throws Exception {
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
            String jsonPathBase = String.format("$.dado.content[%d].", i);

            resultado.andExpect(jsonPath(jsonPathBase + "id").exists())
                    .andExpect(jsonPath(jsonPathBase + "valor").value(esperado.getContent().get(i).getValor()))
                    .andExpect(jsonPath(jsonPathBase + "data").value(esperado.getContent().get(i).getData()))
                    .andExpect(jsonPath(jsonPathBase + "horario").value(esperado.getContent().get(i).getHorario()))
                    .andExpect(jsonPath(jsonPathBase + "finalizada").value(esperado.getContent().get(i).getFinalizada()))
                    .andExpect(jsonPath("$.erro").doesNotExist())
                    .andExpect(jsonPath(jsonPathBase + "psicologo.nome").value(esperado.getContent().get(i).getPsicologo().getNome()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.crp").value(esperado.getContent().get(i).getPsicologo().getCrp()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.cpf").value(esperado.getContent().get(i).getPsicologo().getCpf()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.email").value(esperado.getContent().get(i).getPsicologo().getEmail()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.telefone").value(esperado.getContent().get(i).getPsicologo().getTelefone()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.dataNascimento").value(esperado.getContent().get(i).getPsicologo().getDataNascimento()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.senha").value(esperado.getContent().get(i).getPsicologo().getSenha()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.genero").value(esperado.getContent().get(i).getPsicologo().getGenero()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.endereco.rua").value(esperado.getContent().get(i).getPsicologo().getEnderecoAtendimento().getRua()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.endereco.numero").value(esperado.getContent().get(i).getPsicologo().getEnderecoAtendimento().getNumero()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.endereco.cep").value(esperado.getContent().get(i).getPsicologo().getEnderecoAtendimento().getCep()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.endereco.cidade").value(esperado.getContent().get(i).getPsicologo().getEnderecoAtendimento().getCidade()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.endereco.estado").value(esperado.getContent().get(i).getPsicologo().getEnderecoAtendimento().getEstado()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.fotoUrl").value(esperado.getContent().get(i).getPsicologo().getFotoUrl()))
                    .andExpect(jsonPath(jsonPathBase + "psicologo.biografia").value(esperado.getContent().get(i).getPsicologo().getBiografia()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.nome").value(esperado.getContent().get(i).getPaciente().getNome()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.cpf").value(esperado.getContent().get(i).getPaciente().getCpf()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.email").value(esperado.getContent().get(i).getPaciente().getEmail()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.telefone").value(esperado.getContent().get(i).getPaciente().getTelefone()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.dataNascimento").value(esperado.getContent().get(i).getPaciente().getDataNascimento()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.senha").value(esperado.getContent().get(i).getPaciente().getSenha()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.genero").value(esperado.getContent().get(i).getPaciente().getGenero()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.endereco.rua").value(esperado.getContent().get(i).getEndereco().getRua()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.endereco.numero").value(esperado.getContent().get(i).getEndereco().getNumero()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.endereco.cep").value(esperado.getContent().get(i).getEndereco().getCep()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.endereco.cidade").value(esperado.getContent().get(i).getEndereco().getCidade()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.endereco.estado").value(esperado.getContent().get(i).getEndereco().getEstado()))
                    .andExpect(jsonPath(jsonPathBase + "paciente.fotoUrl").value(esperado.getContent().get(i).getPaciente().getFotoUrl()))
                    .andExpect(jsonPath(jsonPathBase + "endereco.rua").value(esperado.getContent().get(i).getEndereco().getRua()))
                    .andExpect(jsonPath(jsonPathBase + "endereco.numero").value(esperado.getContent().get(i).getEndereco().getNumero()))
                    .andExpect(jsonPath(jsonPathBase + "endereco.cep").value(esperado.getContent().get(i).getEndereco().getCep()))
                    .andExpect(jsonPath(jsonPathBase + "endereco.cidade").value(esperado.getContent().get(i).getEndereco().getCidade()))
                    .andExpect(jsonPath(jsonPathBase + "endereco.estado").value(esperado.getContent().get(i).getEndereco().getEstado()));
        }
    }
}
